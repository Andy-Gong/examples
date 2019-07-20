package elastic.job.spring.jobs;

import com.dangdang.ddframe.job.api.ShardingContext;

public class SimpleJob implements com.dangdang.ddframe.job.api.simple.SimpleJob {

    /**
     * 执行作业.
     *
     * @param shardingContext 分片上下文
     */
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("Simple Job starts to execute, and shardingstrategy parameter is  " + shardingContext
            .getShardingParameter());
    }
}
