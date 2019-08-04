package amq.producer;

import amq.AMQConfiguration;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;
import javax.jms.Topic;

@Component
public class Producer<T> {

    @Autowired
    private AMQConfiguration amqConfiguration;
    private MessageProducer messageProducer;
    private Gson gson = new Gson();

    @PostConstruct
    public void initProducer() throws JMSException {
        Topic topic = amqConfiguration.getSession().createTopic(amqConfiguration.getTopic());
        messageProducer = amqConfiguration.getSession().createProducer(topic);
    }

    public void send(T message, String jmsType) throws JMSException {
        TextMessage textMessage = amqConfiguration.getSession().createTextMessage(gson.toJson(message));
        textMessage.setJMSType(jmsType);
        messageProducer.send(textMessage, DeliveryMode.PERSISTENT, 1, amqConfiguration.getTimeToAlive());
    }

    public void close() throws JMSException {
        messageProducer.close();
    }
}
