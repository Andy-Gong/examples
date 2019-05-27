package eventbus.guava;

import com.google.common.eventbus.Subscribe;

public class ChangeEventListener {

    @Subscribe
    public void consume1(ChangeEvent changeEvent) throws InterruptedException {
        System.out.println("Thread id is " + Thread.currentThread().getId() + ". Consumer1 consumes the event, key: " + changeEvent.getKey()
                + ", value: " + changeEvent.getValue());
        Thread.sleep(1000);
    }

    @Subscribe
    public void consume2(ChangeEvent changeEvent) throws InterruptedException {
        System.out.println("Thread id is " + Thread.currentThread().getId() + ". Consumer2 consumes the event, key: " + changeEvent.getKey()
                + ", value: " + changeEvent.getValue());
        Thread.sleep(1000);
    }
}
