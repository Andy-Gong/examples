package amq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;

@Component
public class SendMessageThread {

    @Autowired
    private Producer<String> producer;


    @PostConstruct
    public void init() {
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    //add JMSType for consumer selector
                    String message = "test:::" + new Date();
                    int jmsType = ThreadLocalRandom.current().nextInt(3);
                    producer.send(message, String.valueOf(jmsType));
                    System.out.println("Producer send message: " + message + ", JMSType: " + jmsType);
                    Thread.sleep(1000);
                } catch (JMSException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}
