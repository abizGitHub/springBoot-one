
package itsurena.ir.demo2.xmlClass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetAllocatedSecuritiesRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetAllocatedSecuritiesRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://modernisc.com/api/csd}AbstractRequestType">
 *       &lt;sequence>
 *         &lt;element name="SecuritiesCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAllocatedSecuritiesRequestType", namespace = "http://modernisc.com/api/csd", propOrder = {
    "securitiesCode"
})
public class GetAllocatedSecuritiesRequestType
    extends AbstractRequestType
{

    @XmlElement(name = "SecuritiesCode", namespace = "http://modernisc.com/api/csd", required = true)
    protected String securitiesCode;

    /**
     * Gets the value of the securitiesCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecuritiesCode() {
        return securitiesCode;
    }

    /**
     * Sets the value of the securitiesCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecuritiesCode(String value) {
        this.securitiesCode = value;
    }

}
