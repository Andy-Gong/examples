
package elastic.job.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class Application {

    public static void main(final String[] args) throws InterruptedException {
        new ClassPathXmlApplicationContext("classpath:META-INF/applicationContext.xml");
    }
}
