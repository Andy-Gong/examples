package elastic.job.spring.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyJobExceptionHandler implements com.dangdang.ddframe.job.executor.handler.JobExceptionHandler {

    Logger LOGGER = LoggerFactory.getLogger(MyJobExceptionHandler.class);

    /**
     * 处理作业异常.
     *
     * @param jobName 作业名称
     * @param cause 异常原因
     */
    @Override
    public void handleException(String jobName, Throwable cause) {
        LOGGER.error("exception happens when processing jobName: " + jobName, cause);
        System.out.println("JobName: " + jobName + ", " + cause.getMessage());
    }
}
