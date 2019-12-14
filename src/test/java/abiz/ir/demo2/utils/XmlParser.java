package abiz.ir.demo2.utils;


import abiz.ir.demo2.xmlClass.AbstractRequestType;
import abiz.ir.demo2.xmlClass.AbstractResponseType;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;


@Component
public class XmlParser {

    //from object to xml text
    public String marshall(AbstractRequestType object) {

        QName rootElementName = new QName("http://modernisc.com/api/csd", object.getClass().getSimpleName().replace("Type",""));
        JAXBElement<AbstractRequestType> rootElement = new JAXBElement<>(rootElementName,AbstractRequestType.class,object);

        StringWriter writer = new StringWriter();

        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(rootElement, writer);
        } catch (PropertyException e) {
            e.printStackTrace();//Todo: change to Runtime Exception
        } catch (JAXBException e) {
            e.printStackTrace();//Todo: change to Runtime Exception
        }


        return String.valueOf(writer);
    }

    //from xml text to object
    public <T extends AbstractResponseType> T unMarshall(String message) {

        StringReader reader = new StringReader(message);
        StreamSource source = new StreamSource(reader);

        Class<?> type = getFullClass(message);
        JAXBElement<T> jaxbElement = null;
        try {
            JAXBContext context = JAXBContext.newInstance(type);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            jaxbElement = (JAXBElement<T>) unmarshaller.unmarshal(source, type);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        T responseObj = (T) jaxbElement.getValue();
        return responseObj;
    }

    private Class<?> getFullClass(String message)  {
        Element root = getRootElement(message);
        String path = AbstractResponseType.class.getName().replace(AbstractResponseType.class.getSimpleName(), "");
        Class<?> returnClass = null;
        try {
            returnClass = Class.forName(path + root.getTagName() + "Type");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return returnClass;
    }

    private Element getRootElement(String message) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = dBuilder.parse(new InputSource(new StringReader(message)));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc.getDocumentElement();
    }

}
