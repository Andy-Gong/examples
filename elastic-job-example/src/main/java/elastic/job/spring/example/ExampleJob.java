package elastic.job.spring.example;

import java.sql.Timestamp;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.springframework.stereotype.Component;

@Component
public class ExampleJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("hello world!!" + shardingContext.getJobName() + "   "
<<<<<<< HEAD:elastic-job-example/src/main/java/elastic/job/spring/example/ExampleJob.java
            + shardingContext.getShardingParameter() + "  "
            + shardingContext.getShardingItem() + "  "
            + shardingContext.getTaskId() + "  "
            + new Timestamp(System.currentTimeMillis()));
        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
=======
                + shardingContext.getShardingParameter() + "  "
                + shardingContext.getShardingItem() + "  "
                + shardingContext.getTaskId() + "  "
                + new Timestamp(System.currentTimeMillis()));
        try {
            Thread.sleep(180000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finish execute the shardings");
>>>>>>> 7de3203ed120e047c1e68cfdcb0eb8e6abd754e0:elastic-job-example/src/main/java/elastic/job/example/ExampleJob.java
    }
}
