package hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Assert;

public class CacheCommandExample extends HystrixCommand<String> {

    private int value;

    protected CacheCommandExample(Setter setter, int value) {
        super(setter);
        this.value = value;
    }

    @Override
    protected String run() throws Exception {
        return "success";
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(value);
    }

    public static class Test {

        /**
         * command1 is first execution, it will not use cache.
         * command2 is second execution, it will use cache and return.
         */
        @org.junit.Test
        public void testCache() {
            HystrixRequestContext context = HystrixRequestContext.initializeContext();
            try {
                HystrixCommand<String> command1 = new CacheCommandExample(
                    Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("request cache"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("request cache"))
                        .andCommandPropertiesDefaults(
                            HystrixCommandProperties.defaultSetter()
                                .withRequestCacheEnabled(true)), 1);
                command1.execute();
                Assert.assertFalse(command1.isResponseFromCache());
                HystrixCommand<String> command2 = new CacheCommandExample(
                    Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("request cache"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("request cache"))
                        .andCommandPropertiesDefaults(
                            HystrixCommandProperties.defaultSetter()
                                .withRequestCacheEnabled(true)), 1);
                command2.execute();
                Assert.assertTrue(command2.isResponseFromCache());
            } finally {
                context.shutdown();
            }

        }
    }

}
