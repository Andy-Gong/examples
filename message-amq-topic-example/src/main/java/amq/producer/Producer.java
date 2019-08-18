package amq.producer;

import amq.config.AMQConfiguration;
import amq.config.SessionFactory;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;
import javax.jms.Topic;

@Component
public class Producer<T> {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private AMQConfiguration amqConfiguration;
    private Map<String, MessageProducer> messageProducers = new HashMap<>();
    private Gson gson = new Gson();

    @PostConstruct
    public void initProducer() {
        amqConfiguration.getTopic().forEach(topicStr -> {
            try {
                Topic topic = sessionFactory.getProducerSession().createTopic(topicStr);
                MessageProducer messageProducer = sessionFactory.getProducerSession().createProducer(topic);
                messageProducers.put(topicStr, messageProducer);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });

    }

    public void send(T message, String jmsType, String topic) throws JMSException {
        TextMessage textMessage = sessionFactory.getProducerSession().createTextMessage(gson.toJson(message));
        textMessage.setJMSType(jmsType);
        messageProducers.get(topic).send(textMessage, DeliveryMode.PERSISTENT, 1, amqConfiguration.getTimeToAlive());
    }

    public void close() {
        messageProducers.values().forEach(messageProducer -> {
            try {
                messageProducer.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
    }

    public Map<String, MessageProducer> getMessageProducers() {
        return messageProducers;
    }
}
