package amq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;


@Component
public class Producer<T> {

    private static Logger log = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Queue queue;

    public void send(String myMessage) {
        jmsTemplate.convertAndSend(queue, myMessage);
    }
}
