
package itsurena.ir.demo2.xmlClass;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetAllocatedSecuritiesResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetAllocatedSecuritiesResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://modernisc.com/api/csd}AbstractResponseType">
 *       &lt;sequence>
 *         &lt;element name="PrincipalIban" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InterestIban" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Securities" type="{http://modernisc.com/api/csd}SecuritiesType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAllocatedSecuritiesResponseType", namespace = "http://modernisc.com/api/csd", propOrder = {
    "principalIban",
    "interestIban",
    "securities"
})
public class GetAllocatedSecuritiesResponseType
    extends AbstractResponseType
{

    @XmlElement(name = "PrincipalIban", namespace = "http://modernisc.com/api/csd")
    protected String principalIban;
    @XmlElement(name = "InterestIban", namespace = "http://modernisc.com/api/csd")
    protected String interestIban;
    @XmlElement(name = "Securities", namespace = "http://modernisc.com/api/csd")
    protected SecuritiesType securities;

    /**
     * Gets the value of the principalIban property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrincipalIban() {
        return principalIban;
    }

    /**
     * Sets the value of the principalIban property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrincipalIban(String value) {
        this.principalIban = value;
    }

    /**
     * Gets the value of the interestIban property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterestIban() {
        return interestIban;
    }

    /**
     * Sets the value of the interestIban property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterestIban(String value) {
        this.interestIban = value;
    }

    /**
     * Gets the value of the securities property.
     * 
     * @return
     *     possible object is
     *     {@link SecuritiesType }
     *     
     */
    public SecuritiesType getSecurities() {
        return securities;
    }

    /**
     * Sets the value of the securities property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecuritiesType }
     *     
     */
    public void setSecurities(SecuritiesType value) {
        this.securities = value;
    }

}
