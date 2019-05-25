package eventbus.guava.examples;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import eventbus.guava.ChangeEvent;
import eventbus.guava.ChangeEventListener;

import java.util.concurrent.Executors;

public class AsyncEventBusTest {

    public static void main(String[] args) throws InterruptedException {
        EventBus eventBus = new AsyncEventBus(Executors.newCachedThreadPool());
        eventBus.register(new ChangeEventListener());
        eventBus.post(new ChangeEvent("test-key", "test-value"));
        Thread.sleep(1000);
    }
}
