package amq.consumer;

import amq.config.AMQConfiguration;
import amq.config.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.Topic;

@Component
public class Consumer {

    @Autowired
    private AMQConfiguration amqConfiguration;
    @Autowired
    private SessionFactory sessionFactory;

    @PostConstruct
    public void initConsumer() {
        amqConfiguration.getTopic().forEach(topicStr -> {
            try {
                Topic topic = sessionFactory.getConsumerSession().createTopic(topicStr);
                for (int i = 0; i < amqConfiguration.getConsumerCount(); i++) {
                    sessionFactory.getConsumerSession()
                            .createDurableSubscriber(topic, "consumer_topic", "JMSType = '1'", true)
                            .setMessageListener(new MyMessageListener(i + " consumer, and consumed topic: " + topicStr));

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
