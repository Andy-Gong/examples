package elastic.job.spring.jobs;

import com.dangdang.ddframe.job.api.ShardingContext;

import java.util.List;

public class DataflowJob implements com.dangdang.ddframe.job.api.dataflow.DataflowJob
{

    /**
     * 获取待处理数据.
     *
     * @param shardingContext 分片上下文
     * @return 待处理的数据集合
     */
    @Override
    public List fetchData(ShardingContext shardingContext) {
        System.out.println("Dataflow Job starts to execute, and sharding parameter is  "+shardingContext.getShardingParameter());
        return null;
    }

    /**
     * 处理数据.
     *
     * @param shardingContext 分片上下文
     * @param data 待处理数据集合
     */
    @Override
    public void processData(ShardingContext shardingContext, List data) {

    }
}
