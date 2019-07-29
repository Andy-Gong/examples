# Quartz with RAM JobStore
In RAM JobStore, all jobs and triggers will be stored in memory.

# Example 
In the example, it will initialize the scheduler and register the jobs. And the job will execute every 5 seconds.
## application.properties
It defines the scheduler properties and job properties.
```properties
# quartz configure
quartz.scheduler.instanceName=MyScheduler
quartz.scheduler.threadCount=3
quartz.scheduler.jobStoreClass=org.quartz.simpl.RAMJobStore

# quartz job initialize
job.key=QuartzRAMTest
job.clazz=quartz.job.JobExample
# run every 5 seconds
job.cron=0/5 * * * * ?
```
## SchedulerInitialize
Initialize the Scheduler, which is the core of quartz.
```java
public class SchedulerInitialize {
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
}
```
## JobInitialize
Register Jobs into scheduler.
```java
public class JobInitialize {
    @PostConstruct
    public void initJob() throws ClassNotFoundException, SchedulerException {
        JobDetail job = newJob()
                .withIdentity(this.key)
                .ofType((Class<? extends Job>) Class.forName(this.clazz))
                .build();

        Trigger trigger = newTrigger()
                .withIdentity(this.key)
                .forJob(job.getKey())
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(this.cron))
                .build();
        schedulerInitialize.getScheduler().scheduleJob(job, trigger);
    }
}
```