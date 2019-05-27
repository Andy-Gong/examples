package eventbus.guava.examples;

import com.google.common.eventbus.EventBus;
import eventbus.guava.ChangeEvent;
import eventbus.guava.ChangeEventListener;

public class EventBusTest {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new ChangeEventListener());
        eventBus.post(new ChangeEvent("test-key", "test-value"));
        eventBus.post(new ChangeEvent("test-key", "test-value"));
    }
}
