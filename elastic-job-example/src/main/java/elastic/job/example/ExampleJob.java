package elastic.job.example;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ExampleJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("hello world!!" + shardingContext.getJobName() + "   "
                + shardingContext.getShardingParameter() + "  "
                + shardingContext.getShardingItem() + "  "
                + shardingContext.getTaskId() + "  "
                + new Timestamp(System.currentTimeMillis()));
    }
}
