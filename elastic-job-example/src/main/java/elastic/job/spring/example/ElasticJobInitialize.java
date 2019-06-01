package elastic.job.spring.example;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.api.strategy.impl.AverageAllocationJobShardingStrategy;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import elastic.job.spring.example.configuration.ElasticJobsConfiguration;
import elastic.job.spring.example.configuration.ZkConfiguration;
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
<<<<<<< HEAD:elastic-job-example/src/main/java/elastic/job/spring/example/ElasticJobInitialize.java
=======
                .misfire(true)
>>>>>>> 7de3203ed120e047c1e68cfdcb0eb8e6abd754e0:elastic-job-example/src/main/java/elastic/job/example/ElasticJobInitialize.java
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
