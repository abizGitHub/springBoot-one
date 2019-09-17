
package itsurena.ir.demo2.xmlClass;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AckCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AckCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="FAILURE"/>
 *     &lt;enumeration value="WARNING"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AckCodeType", namespace = "http://modernisc.com/api/csd")
@XmlEnum
public enum AckCodeType {

    SUCCESS,
    FAILURE,
    WARNING;

    public String value() {
        return name();
    }

    public static AckCodeType fromValue(String v) {
        return valueOf(v);
    }

}
