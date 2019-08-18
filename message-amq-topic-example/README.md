# AMQ TOPIC example
The example is for ActiveMQ topic test. In example, it defines two topic(topic_test, topic_another_test) in application.properties. There are two threads sending the message to the two topics periodically in SendMessageThread.class and each topic will be consumed by three consumers in Consumer.class.
And we can see each consumer will receive the whole messages of its subscribed topic.
 
## Build Project
### Prerequisites
1. Clone this repository
2. Java 8
3. Maven 3.3+

```
$ mvn clean install
```

## RUN this example
### Prerequisites
* install ActiveMQ, download link: http://activemq.apache.org/components/classic/download/

* Run Application.java
