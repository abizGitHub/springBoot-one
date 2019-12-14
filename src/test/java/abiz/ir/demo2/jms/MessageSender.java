package abiz.ir.demo2.jms;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.mq.jms.MQQueueSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jms.*;

public class MessageSender {
    public static final String MESSAGE0 = "trying to send message with message-Id '%s' to the central bank...";
    public static final String MESSAGE1 = "message with message-Id '%s' send to central bank!";
    public static final String MESSAGE2 = "message send and rollback both failed";
    public static final String MESSAGE3 = "throws exception on sending message...";
    public static final String MESSAGE4 = "connection is open, trying to close it...";
    public static final String MESSAGE5 = "connection is closed!";
    public static final String MESSAGE6 = "session is open, trying to close it...";
    public static final String MESSAGE7 = "session is closed!";
    public static final String MESSAGE8 = "producer is open, trying to close it...";
    public static final String MESSAGE9 = "producer is closed!";
    public static final String MESSAGE10 = "!!!! can not close jms connection !!!";
    public static final String MESSAGE11 = "!!!! connection or session or producer is open check what happened !!!";
    private final Logger logger = LogManager.getLogger(MessageSender.class);
    private final MQQueueConnectionFactory factory;
    private MQQueueSession session = null;
    private MessageProducer producer = null;
    private QueueConnection connection = null;

    public MessageSender(MQQueueConnectionFactory factory) {
        this.factory = factory;
    }

    public void send(String message, String messageId) {

        try {
            logger.info(String.format(MESSAGE0, messageId));
            initConnection();
            Message msg = session.createTextMessage(message);
            producer.send(msg);
            insertSimaRequestLog(message, messageId);
            session.commit();
            logger.info(String.format(MessageSender.MESSAGE1, messageId));
        } catch (JMSException e) {
            if (session != null)
                try {
                    session.rollback();
                } catch (JMSException e1) {
                    logger.error(MESSAGE2, e1);
                }
            e.printStackTrace();
            closeConnection();
        }
    }

    private void insertSimaRequestLog(String message, String messageId) {
        System.out.println(message + "\n " + messageId);
    }

    /*

surena.mq.host=10.40.0.240
surena.mq.port=3000
surena.mq.queue-manager=CENTRAL
surena.mq.channel=SYSTEM.DEF.SVRCONN
surena.mq.userName=mqm
surena.mq.password=surenamqm
surena.receiver.queue=receiver
surena.sender.queue=sender

     */
    private void initConnection() throws JMSException {

        if (connection == null || session == null || producer == null) {
            String userName = "mqm";
            String password = "surenamqm";
            //connection = factory.createQueueConnection();
            connection = factory.createQueueConnection(userName, password);
            session = (MQQueueSession) connection.createQueueSession(true, Session.AUTO_ACKNOWLEDGE);

            String senderQueueName = "sender";
            Queue producerQueue = session.createQueue(senderQueueName);
            producer = session.createProducer(producerQueue);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        }
    }

    private void closeConnection() {

        logger.error(MESSAGE3);
        try {

            if (connection != null) {
                logger.error(MESSAGE4);
                connection.close();
                connection = null;
                logger.error(MESSAGE5);
            }

            if (session != null) {
                logger.error(MESSAGE6);
                session.rollback();
                session.close();
                session = null;
                logger.error(MESSAGE7);
            }

            if (producer != null) {
                logger.error(MESSAGE8);
                producer.close();
                producer = null;
                logger.error(MESSAGE9);
            }

        } catch (JMSException e1) {

            logger.error(MESSAGE10);
            logger.error(MESSAGE11);

        }

    }
}
