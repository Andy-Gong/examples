package hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.junit.Assert;

public class FailureCommandExample extends HystrixCommand<String> {


    /**
     * Construct a {@link HystrixCommand} with defined {@link HystrixCommandGroupKey}.
     * <p>
     * The {@link HystrixCommandKey} will be derived from the implementing class name.
     *
     * @param group {@link HystrixCommandGroupKey} used to group together multiple {@link HystrixCommand} objects.
     * <p>
     * The {@link HystrixCommandGroupKey} is used to represent a common relationship between commands. For example, a library or team name, the system all related commands interact with,
     * common business purpose etc.
     */
    protected FailureCommandExample(HystrixCommandGroupKey group) {
        super(group);
    }

    /**
     * Implement this method with code to be executed when {@link #execute()} or {@link #queue()} are invoked.
     *
     * @return R response type
     * @throws Exception if command execution fails
     */
    @Override
    protected String run() throws Exception {
        throw new RuntimeException();
    }

    @Override
    protected String getFallback() {
        return "exception happens";
    }

    public static class Test {

        @org.junit.Test
        public void testFallback() {
            HystrixCommand<String> fallback = new FailureCommandExample(
                HystrixCommandGroupKey.Factory.asKey("fallback"));
            String result = fallback.execute();
            Assert.assertEquals("exception happens", result);
        }
    }

}
