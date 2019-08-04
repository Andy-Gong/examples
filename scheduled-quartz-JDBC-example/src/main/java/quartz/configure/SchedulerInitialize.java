package quartz.configure;


import lombok.Getter;
import lombok.Setter;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

import javax.annotation.PostConstruct;

@Configuration
@ConfigurationProperties(prefix = "quartz.scheduler")
@Setter
@Getter
public class SchedulerInitialize {

    private String instanceName;
    private int threadCount;
    private String jobStoreClass;
    private String tablePrefix;
    private String dataSourceUrl;
    private String dataSourceDriver;
    private String dataSourceUser;
    private String dataSourcePassword;
    private boolean isClustered;

    private Scheduler scheduler;

    @PostConstruct
    public void scheduler() throws SchedulerException {
        Properties properties = new Properties();
        /**
         * If you are using the clustering features, you must use the same name for every instance in the cluster that is ‘logically’ the same Scheduler.
         */
        properties.put(StdSchedulerFactory.PROP_SCHED_INSTANCE_NAME, this.instanceName);
        properties.put(StdSchedulerFactory.PROP_JOB_STORE_CLASS, this.jobStoreClass);
        properties.put(StdSchedulerFactory.PROP_TABLE_PREFIX, this.tablePrefix);
        properties.put("org.quartz.threadPool.threadCount", String.valueOf(this.threadCount));
        properties.put("org.quartz.dataSource.quartzDataSource.URL", this.dataSourceUrl);
        properties.put("org.quartz.dataSource.quartzDataSource.user", this.dataSourceUser);
        properties.put("org.quartz.dataSource.quartzDataSource.password", this.dataSourcePassword);
        properties.put("org.quartz.dataSource.quartzDataSource.driver", this.dataSourceDriver);
        properties.put("org.quartz.jobStore.dataSource", "quartzDataSource");
        properties.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        properties.put("org.quartz.jobStore.isClustered", "true");
        properties.put("org.quartz.scheduler.instanceId", "AUTO");
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory(properties);
        scheduler = stdSchedulerFactory.getScheduler();
        scheduler.start();
    }

    public Scheduler getScheduler() {
        return scheduler;
    }
}
