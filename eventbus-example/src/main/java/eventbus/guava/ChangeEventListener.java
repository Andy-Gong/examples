package eventbus.guava;

import com.google.common.eventbus.Subscribe;

public class ChangeEventListener {

    @Subscribe
    public void consume1(ChangeEvent changeEvent) {
        System.out.println("Consumer1 consumes the event, key: " + changeEvent.getKey()
                + ", value: " + changeEvent.getValue());
    }

    @Subscribe
    public void consume2(ChangeEvent changeEvent) {
        System.out.println("Consumer2 consumes the event, key: " + changeEvent.getKey()
                + ", value: " + changeEvent.getValue());
    }
}
