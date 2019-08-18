package amq.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public class MyMessageListener implements javax.jms.MessageListener {

    private String consumerName;

    public MyMessageListener(String consumerName) {
        this.consumerName = consumerName;
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println(this.consumerName + "::::::::" + ((TextMessage) message).getText());
            message.acknowledge();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
