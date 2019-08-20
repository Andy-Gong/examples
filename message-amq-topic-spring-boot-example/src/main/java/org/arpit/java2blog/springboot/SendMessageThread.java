//package amq.producer;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//import javax.annotation.PostConstruct;
//
//@Component
//public class SendMessageThread {
//
//    @Autowired
//    private Producer<String> producer;
//
//
//    @PostConstruct
//    public void init() {
//        while (true) {
//            try {
//                //add JMSType for consumer selector
//                String message = "test:::" + new Date();
//                producer.send(message);
//                Thread.sleep(500);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
