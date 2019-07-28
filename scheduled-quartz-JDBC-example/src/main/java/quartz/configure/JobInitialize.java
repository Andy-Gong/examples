package quartz.configure;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import lombok.Getter;
import lombok.Setter;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

import javax.annotation.PostConstruct;

@ConfigurationProperties(prefix = "job")
@Configuration
@Getter
@Setter
public class JobInitialize {

    private String key;
    private String clazz;
    private String cron;
    @Autowired
    private SchedulerInitialize schedulerInitialize;

    @PostConstruct
    public void initJob() throws ClassNotFoundException, SchedulerException {
        JobDetail job = newJob()
                .withIdentity(this.key + UUID.randomUUID().toString())
                .ofType((Class<? extends Job>) Class.forName(this.clazz))
                .build();

        Trigger trigger = newTrigger()
                .forJob(job.getKey())
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(this.cron))
                .build();
        schedulerInitialize.getScheduler().scheduleJob(job, trigger);
    }

}
