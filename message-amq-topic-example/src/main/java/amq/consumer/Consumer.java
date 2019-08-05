package amq.consumer;

import amq.AMQConfiguration;
import amq.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Topic;

@Component
public class Consumer {

    @Autowired
    private AMQConfiguration amqConfiguration;
    @Autowired
    private SessionFactory sessionFactory;
    private MessageConsumer messageConsumer;

    @PostConstruct
    public void initConsumer() throws JMSException {
        Topic topic = sessionFactory.getSession().createTopic(amqConfiguration.getTopic());
        for (int i = 0; i < 3; i++) {
            //consumer selector is 'JMSType='1''
            messageConsumer = sessionFactory.getSession()
                    .createDurableSubscriber(topic, "test_consumer", "JMSType = '1'", true);
            messageConsumer.setMessageListener(new MyMessageListener("consumer" + i));
        }
    }

    public void close() throws JMSException {
        messageConsumer.close();
    }
}
