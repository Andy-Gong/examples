package amq.producer;

import amq.config.AMQConfiguration;
import amq.config.SessionFactory;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;

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
        Queue queue = sessionFactory.getProducerSession().createQueue(amqConfiguration.getQueue());
        messageProducer = sessionFactory.getProducerSession().createProducer(queue);
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
