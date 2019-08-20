package amq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

	@JmsListener(destination = "test", containerFactory = "queueListenerFactory")
	public void receiveQueue(String text) {
		System.out.println("Message Received: "+text);
	}
}
