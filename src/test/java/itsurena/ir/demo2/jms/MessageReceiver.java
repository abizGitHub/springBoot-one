package itsurena.ir.demo2.jms;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.mq.jms.MQQueueSession;
import itsurena.ir.demo2.utils.XmlParser;
import itsurena.ir.demo2.xmlClass.AbstractResponseType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jms.*;

public class MessageReceiver implements Runnable {

    private static final Logger logger = LogManager.getLogger(MessageReceiver.class);
    private final MQQueueConnectionFactory factory;
    private final XmlParser xmlParser;
    private final RequestObjectPool objectPool;
    private MessageConsumer consumer = null;
    private QueueConnection connection = null;
    private AbstractResponseType abstractResponseType;

    private static final String MESSAGE = "receive correlationId %s from central bank!";
    private static final String MESSAGE2 = "trying to logged received message into data base...";
    private static final String MESSAGE3 = "received message logged into data base!";
    private static final String MESSAGE4 = "!jms exception!";
    private static final String MESSAGE5 = "!throws exception on received message!";
    private final String MESSAGE6 = "!connection is open, trying to close it!";
    public static final String MESSAGE7 = "!connection is closed!";
    public static final String MESSAGE9 = "!consumer is closed!";
    public static final String MESSAGE8 = "!can not close jms connection!";
    public static final String MESSAGE10 = "!!!!connection or consumer is open check what happened!!!";


    public MessageReceiver(MQQueueConnectionFactory factory) throws JMSException {
        this.objectPool = new RequestObjectPool();
        this.factory = factory;
        this.xmlParser = new XmlParser();
        System.out.println("rec ...");
        initConnection();
    }

    public void run() {

        String logSeperator = "==================================";
        while (true) {
            String correlationId = null;
            try {
                initConnection();
                synchronized (this) {
                    Message message = consumer.receive();

                    if (message != null) {
                        String text = getMessageText(message);
                        AbstractResponseType responseType = xmlParser.unMarshall(text);
                        correlationId = responseType.getCorrelationId();
                        logger.info(String.format(MESSAGE, correlationId));
                        logger.info(MESSAGE2);
                        insertSimaResponseLog(text, correlationId);
                        logger.info(MESSAGE3);
                        consumer.close();
                        connection.close();
                        this.abstractResponseType = responseType;
                    }

                }
            } catch (JMSException e) {
                logger.error(logSeperator);
                logger.error(MESSAGE4);
                logger.error(e.getMessage());

                logger.error(MESSAGE5);
                try {
                    if (connection != null) {
                        logger.error(MESSAGE6);
                        connection.close();
                        connection = null;
                        logger.error(MESSAGE7);
                    }

                    if (consumer != null) {
                        logger.error(MESSAGE6);
                        consumer.close();
                        consumer = null;
                        logger.error(MESSAGE9);
                    }
                } catch (JMSException exc) {
                    logger.error(MESSAGE8);
                    logger.error(MESSAGE10);
                }
            } finally {
                notifyService(correlationId);
            }
        }
    }

    private void insertSimaResponseLog(String text, String correlationId) {
        System.out.println(text + "\n" + correlationId);
    }

    private String getMessageText(Message message) {
        TextMessage textMessage = null;
        BytesMessage bytesMessage = null;
        String text = null;

        try {
            if (message instanceof BytesMessage) {
                bytesMessage = (BytesMessage) message;
                byte[] data = new byte[(int) bytesMessage.getBodyLength()];
                bytesMessage.readBytes(data);
                text = new String(data);
                logger.info("Message Byte received \n {}", text);
                bytesMessage.acknowledge();

            } else if (message instanceof TextMessage) {
                textMessage = (TextMessage) message;
                text = textMessage.getText();
                logger.info("Message Text received \n {}", text);
                textMessage.acknowledge();
            }
        } catch (JMSException e) {
            logger.error(MESSAGE5);
            logger.error(MESSAGE4);
        }
        return text;
    }

    private void notifyService(String messageId) {
        if (messageId != null && messageId.length() > 0) {
            ObjectPoolType objectPoolType = objectPool.retrieve(messageId);
            Object obj = objectPoolType.getObj();
            if (obj != null) {
                objectPoolType.setAbstractResponseType(abstractResponseType);
                synchronized (obj) {
                    obj.notifyAll();
                }
            }
        }
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

        if (connection == null || consumer == null) {
            String userName = "mqm";
            String password = "surenamqm";
            String receiverQueueName = "receiver";

            connection = factory.createQueueConnection(userName, password);
            MQQueueSession session = (MQQueueSession)
                    connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(receiverQueueName);
            consumer = session.createConsumer(queue);
            connection.start();
        }
    }

}
