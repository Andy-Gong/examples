package hystrix;

import java.util.Date;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class CircuitBreakerCommandExample extends HystrixCommand<String> {

    private int index;


    protected CircuitBreakerCommandExample(Setter setter, int index) {
        super(setter);
        this.index = index;
    }

    /**
     * if index is even, sleep 10s which will throw timeout exception.
     * if index is odd, sleep 100ms which will success,
     * @return
     * @throws Exception
     */
    @Override
    protected String run() throws Exception {
        if (index % 2 == 0) {
            Thread.sleep(10000);
        } else {
            Thread.sleep(100);
        }
        return "success";
    }

    @Override
    protected String getFallback() {
        return "exception happens";
    }

    public static class Test {

        /**
         * enable timeout (timeout is 1s), circuit breaker.
         * threshold percentage is 10%.
         * so when i = 10, it will trigger circuit break
         */
        @org.junit.Test
        public void testCircuitBeaker() {
            for (int i = 0; i < 50; i++) {
                HystrixCommand<String> circuitBreaker = new CircuitBreakerCommandExample(
                    Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("circuit breaker group"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("circuit breaker"))
                        .andCommandPropertiesDefaults(
                            HystrixCommandProperties.defaultSetter()
                                .withCircuitBreakerEnabled(true)
                                .withCircuitBreakerRequestVolumeThreshold(10)
                                .withExecutionTimeoutEnabled(true)
                                .withExecutionTimeoutInMilliseconds(1000)
                                .withCircuitBreakerErrorThresholdPercentage(10)), i);
                String result = circuitBreaker.execute();
                System.out.println(new Date() + "  " + result);
            }
        }
    }

}
