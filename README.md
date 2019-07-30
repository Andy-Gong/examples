# Examples
## Sharding database
### [sharding-jdbc-sharding-mybatis-example](https://github.com/Andy-Gong/examples/tree/master/sharding-jdbc-sharding-mybatis-example)
### [sharding-jdbc-master-slave-jpa-example](https://github.com/Andy-Gong/examples/tree/master/sharding-jdbc-master-slave-jpa-example)
### [sharding-multi-tenant-datasource-example](https://github.com/Andy-Gong/examples/tree/master/sharding-multi-tenant-datasource-example)
### [sharding-multi-tenant-provider-example](https://github.com/Andy-Gong/examples/tree/master/sharding-multi-tenant-provider-example)
## Scheduled Job
### [scheduled-elastic-job-example](https://github.com/Andy-Gong/examples/tree/master/scheduled-elastic-job-example)
### [scheduled-elastic-job-spring-example](https://github.com/Andy-Gong/examples/tree/master/scheduled-elastic-job-spring-example)
### [scheduled-quartz-ram-example](https://github.com/Andy-Gong/examples/tree/master/scheduled-quartz-ram-example)
#### Quartz Architecture (Single scheduler)
![image](https://github.com/Andy-Gong/examples/blob/master/z-images/quartz_architecture.png)
#### Analyze source code.
##### Create a scheduler
![image](https://github.com/Andy-Gong/examples/blob/master/z-images/new_scheduler_workflow.png)
##### Start a scheduler
![image](https://github.com/Andy-Gong/examples/blob/master/z-images/start_scheduler.png)
##### Register JobDetail and Trigger
![image](https://github.com/Andy-Gong/examples/blob/master/z-images/register_jobs_workflow.png)
##### Run QuartzSchedulerThread that is the heart of Quartz
![image](https://github.com/Andy-Gong/examples/blob/master/z-images/QuartzSchedulerThread_workflow.png)
![image](https://github.com/Andy-Gong/examples/blob/master/z-images/run_QuartzSchedulerThread.png)
#### Quartz Architecture (Cluster mode)
![image](https://github.com/Andy-Gong/examples/blob/master/z-images/quartz_architecture_cluster.png)
Clustering currently only works with JDBC-Jobstore, and each node of the cluster share the same database.
Load-balancing occurs automatically, each node of the cluster firing jobs as quickly as it can. When a trigger's firing time occurs, the first node to acquire it with a lock on it. For example, if the job has a repeating trigger every 10 seconds, at 1:00:00 one node runs the job, and at 1:00:10, one node runs the job again, the second node maybe same with first one or not.
Fail-over occurs when one of the nodes fails, the other nodes detect the condition and identify the jobs from the database that were in progress within the failed node and fire them.
##### Failover
![image](https://github.com/Andy-Gong/examples/blob/master/z-images/failover.png)
ClusterManager is responsible for managing the cluster failover. Each instance will send health check to table: SCHEDULER_STATE per specify interval. If one instance doesn't send health check, other instances will acquire the lock:STATE_ACCESS firstly, then recover the failed triggers of failed instance.
## EventBus
### [eventbus-example](https://github.com/Andy-Gong/examples/tree/master/eventbus-example)
## Distributed lock
###
