
package abiz.ir.demo2.xmlClass;

import org.w3c.dom.Element;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for AbstractResponseType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AbstractResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Ack" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://modernisc.com/api/csd}AckCodeType">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CorrelationId" type="{http://modernisc.com/api/csd}GUID" minOccurs="0"/>
 *         &lt;element name="Errors" type="{http://modernisc.com/api/csd}ErrorType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractResponseType", namespace = "http://modernisc.com/api/csd", propOrder = {
        "ack",
        "correlationId",
        "errors",
        "any"
})
@XmlSeeAlso({
        GetAllocatedSecuritiesResponseType.class})
public abstract class AbstractResponseType {

    @XmlElement(name = "Ack", namespace = "http://modernisc.com/api/csd")
    protected AckCodeType ack;
    @XmlElement(name = "CorrelationId", namespace = "http://modernisc.com/api/csd")
    protected String correlationId;
    @XmlElement(name = "Errors", namespace = "http://modernisc.com/api/csd")
    protected List<ErrorType> errors;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the ack property.
     *
     * @return possible object is
     * {@link AckCodeType }
     */
    public AckCodeType getAck() {
        return ack;
    }

    /**
     * Sets the value of the ack property.
     *
     * @param value allowed object is
     *              {@link AckCodeType }
     */
    public void setAck(AckCodeType value) {
        this.ack = value;
    }

    /**
     * Gets the value of the correlationId property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCorrelationId() {
        return correlationId;
    }

    /**
     * Sets the value of the correlationId property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCorrelationId(String value) {
        this.correlationId = value;
    }

    /**
     * Gets the value of the errors property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errors property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrors().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ErrorType }
     */
    public List<ErrorType> getErrors() {
        if (errors == null) {
            errors = new ArrayList<ErrorType>();
        }
        return this.errors;
    }

    /**
     * Gets the value of the any property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Element }
     * {@link Object }
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }

}
