package abiz.ir.demo2.jms;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

public class JmsConfig {

    public JmsConfig() {
        setJmsConnectionFactory();
    }

    private static final String MESSAGE = "can not create mq factory, check mq host, chanel and port";

    private final Logger logger = LogManager.getLogger(JmsConfig.class);

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


    public MQQueueConnectionFactory mqQueueConnectionFactory() {

        MQQueueConnectionFactory factory = new MQQueueConnectionFactory();

        try {
            factory.setHostName("10.40.0.240");
            factory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
            factory.setChannel("SYSTEM.DEF.SVRCONN");
            factory.setPort(3000);
            factory.setQueueManager("CENTRAL");

        } catch (Exception e) {
            logger.error(MESSAGE);
        }

        return factory;
    }

/*
    @Bean
    @Primary
    public MQQueueConnectionFactory mqQueueConnectionFactory() {

        MQQueueConnectionFactory factory = new MQQueueConnectionFactory();

        try {
            factory.setHostName(env.getProperty("surena.mq.host"));
            factory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
            factory.setChannel(env.getProperty("surena.mq.channel"));
            factory.setPort(Integer.parseInt(env.getProperty("surena.mq.port")));
            factory.setQueueManager(env.getProperty("surena.mq.queue-manager"));

        } catch (Exception e) {
            logger.error("can not create mq factory, check mq host, chanel and port");
        }

        return factory;
    }*/

    public CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setSessionCacheSize(50);
        factory.setTargetConnectionFactory(mqQueueConnectionFactory());
        factory.setReconnectOnException(true);
        factory.afterPropertiesSet();
        return factory;
    }

    public JmsTemplate setJmsConnectionFactory() {
        return new JmsTemplate(this.cachingConnectionFactory());
    }

}