package amq.consumer;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Topic;

import amq.AMQConfiguration;
import amq.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    private AMQConfiguration amqConfiguration;
    @Autowired
    private SessionFactory sessionFactory;
    //    private MessageConsumer messageConsumer;

    @PostConstruct
    public void initConsumer() throws JMSException {
        Topic topic = sessionFactory.getConsumerSession().createTopic(amqConfiguration.getTopic());
        for (int i = 0; i < 3; i++) {
            //consumer selector is 'JMSType='1''
            MessageConsumer messageConsumer = sessionFactory.getConsumerSession()
                .createDurableSubscriber(topic, "consumer" + i, "JMSType = '1'", true);
            messageConsumer.setMessageListener(new MyMessageListener("consumer" + i));
        }
    }
}
