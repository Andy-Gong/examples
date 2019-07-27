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
    private Scheduler scheduler;

    @PostConstruct
    public void scheduler() throws SchedulerException {
        Properties properties = new Properties();
        properties.setProperty(StdSchedulerFactory.PROP_SCHED_INSTANCE_NAME, this.instanceName);
        properties.setProperty("org.quartz.threadPool.threadCount", String.valueOf(this.threadCount));
        properties.setProperty(StdSchedulerFactory.PROP_JOB_STORE_CLASS, this.jobStoreClass);
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory(properties);
        scheduler = stdSchedulerFactory.getScheduler();
        scheduler.start();
    }

    public Scheduler getScheduler() {
        return scheduler;
    }
}
