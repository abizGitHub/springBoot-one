
package abiz.ir.demo2.xmlClass;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SecuritiesFeatureType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SecuritiesFeatureType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FeatureType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://modernisc.com/api/csd}SecuritiesFeatureClassType">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Quantity" type="{http://modernisc.com/api/csd}QuantityType" minOccurs="0"/>
 *         &lt;element name="FromDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="ToDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SecuritiesFeatureType", namespace = "http://modernisc.com/api/csd", propOrder = {
    "featureType",
    "quantity",
    "fromDate",
    "toDate"
})
public class SecuritiesFeatureType {

    @XmlElement(name = "FeatureType", namespace = "http://modernisc.com/api/csd")
    protected SecuritiesFeatureClassType featureType;
    @XmlElement(name = "Quantity", namespace = "http://modernisc.com/api/csd")
    protected QuantityType quantity;
    @XmlElement(name = "FromDate", namespace = "http://modernisc.com/api/csd")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fromDate;
    @XmlElement(name = "ToDate", namespace = "http://modernisc.com/api/csd")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar toDate;

    /**
     * Gets the value of the featureType property.
     * 
     * @return
     *     possible object is
     *     {@link SecuritiesFeatureClassType }
     *     
     */
    public SecuritiesFeatureClassType getFeatureType() {
        return featureType;
    }

    /**
     * Sets the value of the featureType property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecuritiesFeatureClassType }
     *     
     */
    public void setFeatureType(SecuritiesFeatureClassType value) {
        this.featureType = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link QuantityType }
     *     
     */
    public QuantityType getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link QuantityType }
     *     
     */
    public void setQuantity(QuantityType value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the fromDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFromDate() {
        return fromDate;
    }

    /**
     * Sets the value of the fromDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFromDate(XMLGregorianCalendar value) {
        this.fromDate = value;
    }

    /**
     * Gets the value of the toDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getToDate() {
        return toDate;
    }

    /**
     * Sets the value of the toDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setToDate(XMLGregorianCalendar value) {
        this.toDate = value;
    }

}
