package amq.consumer;

import amq.config.AMQConfiguration;
import amq.config.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Queue;

@Component
public class Consumer {

    @Autowired
    private AMQConfiguration amqConfiguration;
    @Autowired
    private SessionFactory sessionFactory;

    @PostConstruct
    public void initConsumer() throws JMSException {
        Queue queue = sessionFactory.getConsumerSession().createQueue(amqConfiguration.getQueue());
        for (int i = 0; i < 3; i++) {
            sessionFactory.getConsumerSession()
                    .createConsumer(queue)
                    .setMessageListener(new MyMessageListener(i + " consumer, queue name: " + amqConfiguration.getQueue()));
        }
    }
}
