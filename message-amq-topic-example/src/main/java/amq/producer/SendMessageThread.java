package amq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;

@Component
public class SendMessageThread {

    @Autowired
    private Producer<String> producer;


    @PostConstruct
    public void init() {
        Executor executor = Executors.newFixedThreadPool(producer.getMessageProducers().size());
        producer.getMessageProducers().entrySet().forEach(stringMessageProducerEntry -> {
            executor.execute(() -> {
                while (true) {
                    try {
                        //add JMSType for consumer selector
                        String message = "test:::" + new Date();
                        int jmsType = ThreadLocalRandom.current().nextInt(3);
                        producer.send(message, String.valueOf(jmsType), stringMessageProducerEntry.getKey());
                        System.out.println("Producer send message: " + message + ", JMSType: " + jmsType);
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        });
    }
}
