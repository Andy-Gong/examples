package amq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


@Component
public class Producer<T> {

    private static Logger log = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String myMessage) {
        log.info("sending with convertAndSend() to queue <" + myMessage + ">");
        jmsTemplate.convertAndSend("topic", myMessage);
    }
}
