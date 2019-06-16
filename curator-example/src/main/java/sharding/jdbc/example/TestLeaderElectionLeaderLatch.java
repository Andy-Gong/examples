package sharding.jdbc.example;

import sharding.configuration.ZkConfiguration;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

@Component
public class TestLeaderElectionLeaderLatch {

    private ZkConfiguration zkConfiguration;
    private static final String LEADER_PATH = "/curator_leader_latch";

    public TestLeaderElectionLeaderLatch(@Autowired ZkConfiguration zkConfiguration) {
        this.zkConfiguration = zkConfiguration;
    }

    @PostConstruct
    public void test() throws Exception {
        //start 10 threads to compete a leader
        int thread_count = 10;
        List<LeaderLatch> leaderLatches = new ArrayList<LeaderLatch>();
        for (int i = 0; i < thread_count; i++) {
            CuratorFramework curatorFramework = getZkClient();
            final LeaderLatch leaderLatch = new LeaderLatch(curatorFramework, LEADER_PATH, String.valueOf(i));
            leaderLatch.addListener(new LeaderLatchListener() {
                @Override
                public void isLeader() {
                    System.out.println("this is leader and id is " + leaderLatch.getId());
                }

                @Override
                public void notLeader() {
                    try {
                        System.out.println("this isn't leader and id is " + leaderLatch.getId()
                                + ", the leader is " + leaderLatch.getLeader().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            leaderLatch.start();
            leaderLatches.add(leaderLatch);
        }

        boolean hasLeader = false;
        while (!hasLeader) {
            for (LeaderLatch leaderLatch : leaderLatches) {
                if (leaderLatch.hasLeadership()) {
                    hasLeader = true;
                    System.out.println("Current leader id is " + leaderLatch.getId()
                            + " and leader id is " + leaderLatch.getLeader().getId());
                }
            }
            if (hasLeader == false) {
                Thread.sleep(1000);
            }
        }
        final StringBuilder participants = new StringBuilder();
        leaderLatches.get(0).getParticipants()
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
