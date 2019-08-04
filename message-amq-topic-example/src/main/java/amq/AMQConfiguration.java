package amq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

@Configuration
@ConfigurationProperties(prefix = "amq")
public class AMQConfiguration {

    private String broker;
    private int autoAck;
    private String topic;
    private long timeToAlive;
    private boolean sync;
    private boolean transacted;
    private String user;
    private String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTransacted() {
        return transacted;
    }

    public void setTransacted(boolean transacted) {
        this.transacted = transacted;
    }

    private Session session;

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public long getTimeToAlive() {
        return timeToAlive;
    }

    public void setTimeToAlive(long timeToAlive) {
        this.timeToAlive = timeToAlive;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public int getAutoAck() {
        return autoAck;
    }

    public void setAutoAck(int autoAck) {
        this.autoAck = autoAck;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public synchronized Session getSession() throws JMSException {
//        if (session == null) {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(this.user, this.password,
                    this.broker == null ? ActiveMQConnection.DEFAULT_BROKER_URL : this.broker);
            connectionFactory.setAlwaysSyncSend(this.sync);
            Connection connection = connectionFactory.createConnection();
            connection.setClientID(UUID.randomUUID().toString());
            connection.start();
            session = connection.createSession(this.transacted, this.autoAck);
            return session;
//        } else {
//            return this.session;
//        }
    }
}
