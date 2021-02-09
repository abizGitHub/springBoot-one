package abiz.ir.crwlr.mb;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.reflect.Type;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RCli {

    public static void main(String[] args) throws RemoteException, NotBoundException, IOException, MalformedObjectNameException, InstanceNotFoundException, MBeanException, AttributeNotFoundException, ReflectionException {
        //rmiClient();
        jmxClient();
    }

    private static void jmxClient() throws IOException, MalformedObjectNameException, InstanceNotFoundException, MBeanException, AttributeNotFoundException, ReflectionException {
        String jmxURL = "service:jmx:rmi:///jndi/rmi://127.0.0.1:8086/jmxrmi";
        JMXServiceURL serviceURL = new JMXServiceURL(jmxURL);
        JMXConnector connector = JMXConnectorFactory.connect(serviceURL);
        MBeanServerConnection mBeanServerConnection = connector.getMBeanServerConnection();
        //System.out.println(mBeanServerConnection.getMBeanCount());
        ObjectName objectName = new ObjectName("com.zzz:type=basic,name=game");
        ObjectInstance objectInstance = mBeanServerConnection.getObjectInstance(objectName);
        mBeanServerConnection.invoke(objectName, "m1", null, null);
        Object getPlayerName = mBeanServerConnection.getAttribute(objectName, "publicName");
        System.out.println(getPlayerName);
    }


    private static void rmiClient() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(1099);
        Remote remote = registry.lookup("gamer");
        for (Class<?> anInterface : remote.getClass().getInterfaces()) {
            System.out.println(anInterface);
        }
        System.out.println(remote.getClass().getSuperclass());
        System.out.println(remote.getClass().getGenericSuperclass());
        for (Type anInterface : remote.getClass().getGenericInterfaces()) {
            System.out.println(anInterface);
        }
    }

}
