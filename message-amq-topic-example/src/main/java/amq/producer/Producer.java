package amq.producer;

import javax.annotation.PostConstruct;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;
import javax.jms.Topic;

import amq.AMQConfiguration;
import amq.SessionFactory;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer<T> {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private AMQConfiguration amqConfiguration;
    private MessageProducer messageProducer;
    private Gson gson = new Gson();

    @PostConstruct
    public void initProducer() throws JMSException {
        Topic topic = sessionFactory.getProducerSession().createTopic(amqConfiguration.getTopic());
        messageProducer = sessionFactory.getProducerSession().createProducer(topic);
    }

    public void send(T message, String jmsType) throws JMSException {
        TextMessage textMessage = sessionFactory.getProducerSession().createTextMessage(gson.toJson(message));
        textMessage.setJMSType(jmsType);
        messageProducer.send(textMessage, DeliveryMode.PERSISTENT, 1, amqConfiguration.getTimeToAlive());
    }

    public void close() throws JMSException {
        messageProducer.close();
    }
}
