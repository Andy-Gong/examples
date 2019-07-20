package elastic.job.spring.jobs;

import com.dangdang.ddframe.job.api.ShardingContext;

public class ExceptionJob implements com.dangdang.ddframe.job.api.simple.SimpleJob {

    /**
     * 执行作业.
     *
     * @param shardingContext 分片上下文
     */
    @Override
    public void execute(ShardingContext shardingContext) {
        throw new RuntimeException("this is my exception, hahahaha");
    }
}
