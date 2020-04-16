package hystrix;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Assert;

public class QueueCommandExample extends HystrixCommand<String> {


    protected QueueCommandExample(Setter setter) {
        super(setter);
    }

    @Override
    protected String run() {
        return "success";
    }


    public static class Test {

        /**
         *
         */
        @org.junit.Test
        public void testQueue() throws ExecutionException, InterruptedException {
            HystrixRequestContext context = HystrixRequestContext.initializeContext();
            try {
                HystrixCommand<String> command1 = new QueueCommandExample(
                    Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("queue"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("queue")));
                Future<String> future = command1.queue();
                Assert.assertEquals("success", future.get());
            } finally {
                context.shutdown();
            }

        }
    }

}
