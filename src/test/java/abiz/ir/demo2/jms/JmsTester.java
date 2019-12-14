package abiz.ir.demo2.jms;

import javax.jms.JMSException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JmsTester {


    public static void main(String[] args) {
        msgReceiver();
    }

    public static void msgReceiver() {
        ExecutorService service = Executors.newFixedThreadPool(1);
        try {
            JmsConfig jmsConfig = new JmsConfig();
            service.submit(new MessageReceiver(jmsConfig.mqQueueConnectionFactory()));
        } catch (JMSException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1033);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            msgSender();
        }
    }

    //@Test
    public static void msgSender() {
        JmsConfig jmsConfig = new JmsConfig();
        MessageSender sender = new MessageSender(jmsConfig.mqQueueConnectionFactory());
        sender.send("hey", "joe");
    }

}
