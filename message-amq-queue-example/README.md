# AMQ Queue example
The example is for ActiveMQ Queue test. In example, it defines a queue named queue_test in application.properties.
There are a threads sending the message to queue periodically in SendMessageThread.class and there are three consumers consumed the queue which init in Consumer.class.
And we can see one message will be consumed by one of the threee consumers.
 
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
