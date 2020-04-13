package hystrix;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class ThreadPoolCommandExample extends HystrixCommand<String> {


    protected ThreadPoolCommandExample(Setter setter) {
        super(setter);
    }

    @Override
    protected String run() throws Exception {
        Thread.sleep(100);
        return "success";
    }

    @Override
    protected String getFallback() {
        return "exception happens";
    }

    public static class Test {

        /**
         * the previous 20 task will execute successfully, and others will fallback
         */
        @org.junit.Test
        public void testThreadPool() {
            List<Future> futureList = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                HystrixCommand<String> threadPoolCommandExample = new ThreadPoolCommandExample(
                    Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("thread pool group"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("thread pool"))
                        .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                            .withCoreSize(10)
                            .withMaximumSize(10)
                            .withMaxQueueSize(15)
                            .withQueueSizeRejectionThreshold(10)));
                Future result = threadPoolCommandExample.queue();
                futureList.add(result);
            }
            futureList.forEach(future -> {
                    try {
                        System.out.println(new Date() + "  " + future.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            );
        }
    }

}
