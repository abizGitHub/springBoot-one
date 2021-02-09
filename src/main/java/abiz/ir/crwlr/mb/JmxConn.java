package abiz.ir.crwlr.mb;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import java.util.Map;

public class JmxConn {

    public static void main(String[] args) throws Exception {
        // simpleJmxService();
        secondJmxService();
    }

    private static void secondJmxService() throws Exception {
        LocateRegistry.createRegistry(8086);
        String jmxURL = "service:jmx:rmi:///jndi/rmi://localhost:8086/jmxrmi";
        JMXServiceURL serviceURL = new JMXServiceURL(jmxURL);
        ObjectName objectName = new ObjectName("com.zzz:type=basic,name=game");
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        server.registerMBean(new Game(), objectName);
        Map<String, Object> envMap = new HashMap<String, Object>();
        //RMIServerSocketFactory serverSocketFactory = port -> new ServerSocket(port, 0, InetAddress.getLocalHost());
        //envMap.put(RMIConnectorServer.RMI_SERVER_SOCKET_FACTORY_ATTRIBUTE, serverSocketFactory);
        JMXConnectorServer jmxConnectorServer = JMXConnectorServerFactory.newJMXConnectorServer(serviceURL, envMap, server);
        jmxConnectorServer.start();
    }

    private static void simpleJmxService() throws Exception {
        ObjectName objectName = new ObjectName("com.zzz:type=basic,name=game");
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        server.registerMBean(new Game(), objectName);
        for (; ; ) {
        }
    }

}
