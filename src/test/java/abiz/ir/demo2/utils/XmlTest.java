package abiz.ir.demo2.utils;

import abiz.ir.demo2.xmlClass.GetAllocatedSecuritiesRequestType;
import abiz.ir.demo2.xmlClass.PrincipalType;
import org.junit.Test;

public class XmlTest {


    @Test
    public void classToXml() {
        GetAllocatedSecuritiesRequestType requestType = new GetAllocatedSecuritiesRequestType();
        requestType.setSecuritiesCode("AAA");
        requestType.setMessageId("msgId");
        requestType.setVersion("verr");
        PrincipalType principalType = new PrincipalType();
        principalType.setBranchCode("brCode");
        principalType.setPassword("pass");
        principalType.setUsername("usr");
        requestType.setPrincipal(principalType);
        XmlParser parser =  new XmlParser();
        String marshall = parser.marshall(requestType);
        System.out.println(marshall);

    }

}
