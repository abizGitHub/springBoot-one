
package itsurena.ir.demo2.xmlClass;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SecuritiesStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SecuritiesStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="ACTIVE"/>
 *     &lt;enumeration value="INACTIVE"/>
 *     &lt;enumeration value="MATURED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SecuritiesStatusType", namespace = "http://modernisc.com/api/csd")
@XmlEnum
public enum SecuritiesStatusType {

    ACTIVE,
    INACTIVE,
    MATURED;

    public String value() {
        return name();
    }

    public static SecuritiesStatusType fromValue(String v) {
        return valueOf(v);
    }

}
