package elastic.job.example;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.api.strategy.impl.AverageAllocationJobShardingStrategy;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import elastic.job.example.configuration.ElasticJobsConfiguration;
import elastic.job.example.configuration.ZkConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Component;

@Component
public class ElasticJobInitialize {

    private ZkConfiguration zkConfiguration;
    private ElasticJobsConfiguration elasticJobsConfiguration;
    private JobEventConfiguration jobEventConfiguration;

    public ElasticJobInitialize(@Autowired ZkConfiguration zkConfiguration,
        @Autowired ElasticJobsConfiguration elasticJobsConfiguration, @Autowired DataSourceProperties dataSourceProperties) {
        this.zkConfiguration = zkConfiguration;
        this.elasticJobsConfiguration = elasticJobsConfiguration;
        this.jobEventConfiguration = new JobEventRdbConfiguration(dataSourceProperties.initializeDataSourceBuilder().build());
    }

    @PostConstruct
    public void test() {
        this.elasticJobsConfiguration.getJobs().forEach(elasticJob -> {
            JobCoreConfiguration coreConfig = JobCoreConfiguration
                .newBuilder(elasticJob.getJobName(), elasticJob.getCron(), elasticJob.getShardingTotalCount())
                .shardingItemParameters(elasticJob.getShardingItemParameters())
                .failover(true)
                .misfire(true)
                .build();
            SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(coreConfig,
                elasticJob.getJobClass());
            JobScheduler jobScheduler = new JobScheduler(
                zkConfiguration.getRegistryCenter(),
                LiteJobConfiguration
                    .newBuilder(simpleJobConfig)
                    .overwrite(true)
                    .jobShardingStrategyClass(AverageAllocationJobShardingStrategy.class.getCanonicalName())
                    .build(),
                this.jobEventConfiguration);
            jobScheduler.init();
        });
    }

}
