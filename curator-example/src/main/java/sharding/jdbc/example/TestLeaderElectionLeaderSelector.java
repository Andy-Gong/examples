package sharding.jdbc.example;

import sharding.configuration.ZkConfiguration;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

@Component
public class TestLeaderElectionLeaderSelector {

    private ZkConfiguration zkConfiguration;
    private static final String LEADER_PATH = "/curator_leader_selector";

    public TestLeaderElectionLeaderSelector(@Autowired ZkConfiguration zkConfiguration) {
        this.zkConfiguration = zkConfiguration;
    }

    @PostConstruct
    public void test() throws Exception {
        //start 10 threads to compete a leader
        int thread_count = 10;
        List<LeaderSelector> leaderSelectors = new ArrayList<>();
        for (int i = 0; i < thread_count; i++) {
            CuratorFramework curatorFramework = getZkClient();
            final LeaderSelector leaderSelector = new LeaderSelector(curatorFramework, LEADER_PATH, new LeaderSelectorListener() {

                /**
                 * Called when there is a state change in the connection
                 *
                 * @param client the client
                 * @param newState the new state
                 */
                @Override
                public void stateChanged(CuratorFramework client, ConnectionState newState) {
                    System.out.println("New state is " + newState.name());
                }

                /**
                 * Called when your instance has been granted leadership. This method should not return until you wish to
                 * release leadership
                 *
                 * @param client the client
                 * @throws Exception any errors
                 */
                @Override
                public void takeLeadership(CuratorFramework client) throws Exception {
                    Thread.sleep(10000);
                    System.out.println(client.getChildren().forPath(LEADER_PATH));
                }
            });
            leaderSelector.setId(String.valueOf(i));
            leaderSelector.start();
            leaderSelectors.add(leaderSelector);
        }

        boolean hasLeader = false;
        while (!hasLeader) {
            for (LeaderSelector leaderSelector : leaderSelectors) {
                if (leaderSelector.hasLeadership()) {
                    hasLeader = true;
                    System.out.println("Current leader id is " + leaderSelector.getId()
                            + " and leader id is " + leaderSelector.getLeader().getId());
                    break;
                }
            }
            if (hasLeader == false) {
                Thread.sleep(1000);
            }
        }
        final StringBuilder participants = new StringBuilder();
        leaderSelectors.get(0).getParticipants()
                .forEach(participant ->
                        participants.append("id: ")
                                .append(participant.getId())
                                .append(",")
                                .append("  isLeader: ")
                                .append(participant.isLeader())
                                .append("\n"));
        System.out.println(participants.toString());
    }

    private CuratorFramework getZkClient() {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(this.zkConfiguration.getServerLists(),
                this.zkConfiguration.getSessionTimeoutMilliseconds(),
                this.zkConfiguration.getConnectionTimeoutMilliseconds(),
                new ExponentialBackoffRetry(1000, 3));
        curatorFramework.start();
        return curatorFramework;
    }
}
