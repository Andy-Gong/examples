# Elastic job example
In this example, it defines elastic jobs in application.yml. And we will register elastic jobs when Spring initialize the ElasticJobInitialize class.

application.yml
```yaml
elastic-jobs:
  jobs:
    - jobName: test1
      shardingItemParameters: 0=a,1=b,2=c,3=d,4=d,5=d
      cron: 0 */10 * * * ?
      jobClass: ExampleJob
      shardingTotalCount: 5
    -
      jobName: test2
      shardingItemParameters: 0=a,1=b,2=c,3=d,4=d,5=d
      cron: 0 */1 * * * ?
      jobClass: ExampleJob
      shardingTotalCount: 5
```

ElasticJob initialize
```java
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
    public void initJobs() {
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
```

Job execution class - ExampleJob
```java
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
```
