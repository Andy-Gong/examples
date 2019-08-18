package amq.config;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

@Component
public class SessionFactory {

    @Autowired
    private AMQConfiguration amqConfiguration;
    private Session producerSession;
    private Session consumerSession;

    public Session getConsumerSession() throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(this.amqConfiguration.getUser(),
                this.amqConfiguration.getPassword(),
                this.amqConfiguration.getBroker() == null ? ActiveMQConnection.DEFAULT_BROKER_URL
                        : this.amqConfiguration.getBroker());
        connectionFactory.setAlwaysSyncSend(this.amqConfiguration.isSync());
        Connection connection = connectionFactory.createConnection();
        connection.setClientID(UUID.randomUUID().toString());
        connection.start();
        consumerSession = connection.createSession(amqConfiguration.isTransacted(), amqConfiguration.getAutoAck());
        return consumerSession;
    }

    public Session getProducerSession() throws JMSException {
        if (producerSession != null) {
            return producerSession;
        }
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(this.amqConfiguration.getUser(),
                this.amqConfiguration.getPassword(),
                this.amqConfiguration.getBroker() == null ? ActiveMQConnection.DEFAULT_BROKER_URL
                        : this.amqConfiguration.getBroker());
        connectionFactory.setAlwaysSyncSend(this.amqConfiguration.isSync());
        Connection connection = connectionFactory.createConnection();
        connection.setClientID(UUID.randomUUID().toString());
        connection.start();
        producerSession = connection.createSession(amqConfiguration.isTransacted(), amqConfiguration.getAutoAck());
        return producerSession;
    }

}
