# Elastic Job Spring example
In this example, it initializes and registers the elastic job via spring.

applicationContext.xml create elastic job beans and starts the elastic jobs.
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:reg="http://www.dangdang.com/schema/ddframe/reg" xmlns:job="http://www.dangdang.com/schema/ddframe/job"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                        http://www.springframework.org/schema/beans/spring-beans.xsd
                        http://www.springframework.org/schema/context
                        http://www.springframework.org/schema/context/spring-context.xsd
                        http://www.dangdang.com/schema/ddframe/job
                        http://www.dangdang.com/schema/ddframe/job/job.xsd
                        http://www.dangdang.com/schema/ddframe/reg
                        http://www.dangdang.com/schema/ddframe/reg/reg.xsd
          ">

    <context:component-scan base-package="elastic.job.spring"/>
    <context:property-placeholder location="classpath:conf/*.properties"/>

    <!--  define datasource of the elastic job execute events trace  -->
    <bean id="elasticJobLog" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
        <property name="driverClassName" value="${event.rdb.driver}"/>
        <property name="url" value="${event.rdb.url}"/>
        <property name="username" value="${event.rdb.username}"/>
        <property name="password" value="${event.rdb.password}"/>
    </bean>

    <reg:zookeeper id="regCenter" server-lists="${serverLists}" namespace="${namespace}"/>

    <job:simple id="${simple.id}" class="${simple.class}" registry-center-ref="regCenter"
                sharding-total-count="${simple.shardingTotalCount}" cron="${simple.cron}"
                sharding-item-parameters="${simple.shardingItemParameters}"
                monitor-execution="${simple.monitorExecution}" monitor-port="${simple.monitorPort}"
                failover="${simple.failover}" description="${simple.description}" disabled="${simple.disabled}"
                overwrite="${simple.overwrite}" event-trace-rdb-data-source="elasticJobLog"/>

    <job:simple id="${exception.id}" class="${exception.class}" registry-center-ref="regCenter"
                sharding-total-count="${exception.shardingTotalCount}" cron="${exception.cron}"
                sharding-item-parameters="${exception.shardingItemParameters}"
                monitor-execution="${exception.monitorExecution}" monitor-port="${exception.monitorPort}"
                failover="${exception.failover}" description="${exception.description}" disabled="${exception.disabled}"
                overwrite="${exception.overwrite}"
                job-exception-handler="${exception.handler}"
                event-trace-rdb-data-source="elasticJobLog"/>

    <job:dataflow id="${dataflow.id}" class="${dataflow.class}" registry-center-ref="regCenter"
                  sharding-total-count="${dataflow.shardingTotalCount}" cron="${dataflow.cron}"
                  sharding-item-parameters="${dataflow.shardingItemParameters}"
                  monitor-execution="${dataflow.monitorExecution}" failover="${dataflow.failover}"
                  max-time-diff-seconds="${dataflow.maxTimeDiffSeconds}"
                  streaming-process="${dataflow.streamingProcess}" description="${dataflow.description}"
                  disabled="${dataflow.disabled}" overwrite="${dataflow.overwrite}" event-trace-rdb-data-source="elasticJobLog"/>

</beans>
```

job.properties defines the properties of elastic job
```properties
event.rdb.driver=com.mysql.cj.jdbc.Driver
event.rdb.url=jdbc:mysql://localhost:3306/elastic_job?useSSL=false
event.rdb.username=root
event.rdb.password=

simple.id=springSimpleJob
simple.class=elastic.job.spring.jobs.SimpleJob
simple.cron=0/5 * * * * ?
simple.shardingTotalCount=3
simple.shardingItemParameters=0=Beijing,1=Shanghai,2=Guangzhou
simple.monitorExecution=false
simple.failover=true
simple.description=\u53EA\u8FD0\u884C\u4E00\u6B21\u7684\u4F5C\u4E1A\u793A\u4F8B
simple.disabled=false
simple.overwrite=true
simple.monitorPort=9888
```

reg.properties defines the zookeeper properties configuration.
```properties
serverLists=localhost:2181
namespace=test
baseSleepTimeMilliseconds=1000
maxSleepTimeMilliseconds=3000
maxRetries=3
```


Job execution class - SimpleJob and DataflowJob
```java
public class SimpleJob implements com.dangdang.ddframe.job.api.simple.SimpleJob {

    /**
     * 执行作业.
     *
     * @param shardingContext 分片上下文
     */
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("Simple Job starts to execute, and shardingstrategy parameter is  " + shardingContext
            .getShardingParameter());
    }
}
```

```java
public class DataflowJob implements com.dangdang.ddframe.job.api.dataflow.DataflowJob {

    /**
     * 获取待处理数据.
     *
     * @param shardingContext 分片上下文
     * @return 待处理的数据集合
     */
    @Override
    public List fetchData(ShardingContext shardingContext) {
        System.out.println("Dataflow Job starts to execute, and shardingstrategy parameter is  " + shardingContext
            .getShardingParameter());
        return null;
    }

    /**
     * 处理数据.
     *
     * @param shardingContext 分片上下文
     * @param data 待处理数据集合
     */
    @Override
    public void processData(ShardingContext shardingContext, List data) {

    }
}
```