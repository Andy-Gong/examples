package amq.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.jms.Session;

@Configuration
@ConfigurationProperties(prefix = "amq")
@Getter
@Setter
public class AMQConfiguration {

    private String broker;
    private int autoAck;
    private String queue;
    private long timeToAlive;
    private boolean sync;
    private boolean transacted;
    private String user;
    private String password;
    private Session session;
}
