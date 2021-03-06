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
    }
}
