
package abiz.ir.demo2.xmlClass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for SecuritiesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SecuritiesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SecuritiesCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SecuritiesName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SecuritiesGroupCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SecuritiesGroupDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://modernisc.com/api/csd}SecuritiesStatusType">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Features" type="{http://modernisc.com/api/csd}SecuritiesFeatureType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SecuritiesType", namespace = "http://modernisc.com/api/csd", propOrder = {
    "securitiesCode",
    "securitiesName",
    "securitiesGroupCode",
    "securitiesGroupDescription",
    "status",
    "features"
})
public class SecuritiesType {

    @XmlElement(name = "SecuritiesCode", namespace = "http://modernisc.com/api/csd")
    protected String securitiesCode;
    @XmlElement(name = "SecuritiesName", namespace = "http://modernisc.com/api/csd")
    protected String securitiesName;
    @XmlElement(name = "SecuritiesGroupCode", namespace = "http://modernisc.com/api/csd")
    protected String securitiesGroupCode;
    @XmlElement(name = "SecuritiesGroupDescription", namespace = "http://modernisc.com/api/csd")
    protected String securitiesGroupDescription;
    @XmlElement(name = "Status", namespace = "http://modernisc.com/api/csd")
    protected SecuritiesStatusType status;
    @XmlElement(name = "Features", namespace = "http://modernisc.com/api/csd")
    protected List<SecuritiesFeatureType> features;

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

    /**
     * Gets the value of the securitiesName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecuritiesName() {
        return securitiesName;
    }

    /**
     * Sets the value of the securitiesName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecuritiesName(String value) {
        this.securitiesName = value;
    }

    /**
     * Gets the value of the securitiesGroupCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecuritiesGroupCode() {
        return securitiesGroupCode;
    }

    /**
     * Sets the value of the securitiesGroupCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecuritiesGroupCode(String value) {
        this.securitiesGroupCode = value;
    }

    /**
     * Gets the value of the securitiesGroupDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecuritiesGroupDescription() {
        return securitiesGroupDescription;
    }

    /**
     * Sets the value of the securitiesGroupDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecuritiesGroupDescription(String value) {
        this.securitiesGroupDescription = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link SecuritiesStatusType }
     *     
     */
    public SecuritiesStatusType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecuritiesStatusType }
     *     
     */
    public void setStatus(SecuritiesStatusType value) {
        this.status = value;
    }

    /**
     * Gets the value of the features property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the features property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFeatures().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SecuritiesFeatureType }
     * 
     * 
     */
    public List<SecuritiesFeatureType> getFeatures() {
        if (features == null) {
            features = new ArrayList<SecuritiesFeatureType>();
        }
        return this.features;
    }

}
