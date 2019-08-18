# Examples
## Sharding database
### [sharding-jdbc-sharding-mybatis-example](https://github.com/Andy-Gong/examples/tree/master/sharding-jdbc-sharding-mybatis-example)
### [sharding-jdbc-master-slave-jpa-example](https://github.com/Andy-Gong/examples/tree/master/sharding-jdbc-master-slave-jpa-example)
### [sharding-multi-tenant-datasource-example](https://github.com/Andy-Gong/examples/tree/master/sharding-multi-tenant-datasource-example)
### [sharding-multi-tenant-provider-example](https://github.com/Andy-Gong/examples/tree/master/sharding-multi-tenant-provider-example)
## Scheduled Job
### Elastic Job
#### [scheduled-elastic-job-example](https://github.com/Andy-Gong/examples/tree/master/scheduled-elastic-job-example)
#### [scheduled-elastic-job-spring-example](https://github.com/Andy-Gong/examples/tree/master/scheduled-elastic-job-spring-example)
### Quartz
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
#### [scheduled-quartz-ram-example](https://github.com/Andy-Gong/examples/tree/master/scheduled-quartz-ram-example)
#### [scheduled-quartz-JDBC-example](https://github.com/Andy-Gong/examples/tree/master/scheduled-quartz-JDBC-example)
## Message Middleware
### ActiveMQ
#### Queue
Queues is straightforward—messages are basically stored in first in, first out order (FIFO). See below figure for a depiction of this. One message is dispatched to ONLY a single consumer at a time. Only when that message has been consumed and acknowledged it can be deleted from the broker’s message store. If one consumer fails to ACK the message, it will be consumed by other consumer.

![image](https://github.com/Andy-Gong/examples/blob/master/z-images/AMQ_Queue.png)

#### Topic
Topic, one message can be consumed by multiple consumers. For durable subscribers to a topic, a durable subscriber object in the store maintains a pointer to its next stored message and dispatches a copy of it to its consumer as shown in below figure. The message store is implemented in this manner because each durable subscriber could be consuming messages at different rates or they may not all be running at the same time. Also, because every message can potentially have many consumers, a message can’t be deleted from the store until it’s been successfully delivered to every interested durable subscriber.

![image](https://github.com/Andy-Gong/examples/blob/master/z-images/AMQ_Topic.png)

#### KahaDB
From ActiveMQ5.4, KahaDB is default storage of AMQ. It has three parts: data logs, BTree indexes and cache.
data logs is the messages of broker. The Journal consists of a rolling log of messages and commands stored in data files of a certern length.
cache holds messages for fast retrieval in memory after they have bean written to the journal. It will periodically update the reference store with its current message ids and location of the messsages in the journal.
BTree indexes ONLY uses one index file for all its destinations. All index file updates also recorded in a redo log. This ensures that the indexes can be brought back in a consistent state. 

![image](https://github.com/Andy-Gong/examples/blob/master/z-images/AMQ_KahaDB.png)

The directory structure of KahaDB
 
![image](https://github.com/Andy-Gong/examples/blob/master/z-images/AMQ_KahaDB_directory.png)

#### Consumer
Consumer code mainly has three parts: read message via transport, the memory queue to store pooled messages and dispatcher to dispatch message to consumers which consums the messages.
The queue and topic consumers are same process flow.

![image](https://github.com/Andy-Gong/examples/blob/master/z-images/AMQ_consumer_workflow.png)
## EventBus
### [eventbus-example](https://github.com/Andy-Gong/examples/tree/master/eventbus-example)
## Distributed lock
###
