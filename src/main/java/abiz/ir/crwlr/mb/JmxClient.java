package abiz.ir.crwlr.mb;

import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JmxClient {

    public static void main(String[] args) throws Exception {
        String jmxURL = "service:jmx:rmi:///jndi/rmi://127.0.0.1:8086/jmxrmi";
        JMXServiceURL serviceURL = new JMXServiceURL(jmxURL);
        JMXConnector connector = JMXConnectorFactory.connect(serviceURL);
        MBeanServerConnection mBeanServerConnection = connector.getMBeanServerConnection();
        //System.out.println(mBeanServerConnection.getMBeanCount());
        ObjectName objectName = new ObjectName("com.zzz:type=basic,name=game");
        ObjectInstance objectInstance = mBeanServerConnection.getObjectInstance(objectName);
        mBeanServerConnection.invoke(objectName,"m1",null,null);
        Object getPlayerName = mBeanServerConnection.getAttribute(objectName, "publicName");
        System.out.println(getPlayerName);
    }

}
