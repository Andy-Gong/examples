package elastic.job.example;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.api.strategy.impl.AverageAllocationJobShardingStrategy;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import elastic.job.example.configuration.ZkConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ElasticJobInitialize {

    private ZkConfiguration zkConfiguration;

    public ElasticJobInitialize(@Autowired ZkConfiguration zkConfiguration) {
        this.zkConfiguration = zkConfiguration;
    }

    @PostConstruct
    public void test() {
        for (int i = 0; i < 2; i++) {
            String cron = "0 */1 * * * ?";
            JobCoreConfiguration coreConfig = JobCoreConfiguration.newBuilder("dynamicDemoJob-", cron, 5)
                    .shardingItemParameters("0=a,1=b,2=c,3=d,4=d,5=d")
                    .build();
            SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(coreConfig,
                    ExampleJob.class.getCanonicalName());
            JobScheduler jobScheduler = new JobScheduler(zkConfiguration.getRegistryCenter(),
                    LiteJobConfiguration
                            .newBuilder(simpleJobConfig)
                            .overwrite(true)
                            .jobShardingStrategyClass(AverageAllocationJobShardingStrategy.class.getCanonicalName())
                            .build());
            jobScheduler.init();
        }
    }

}
