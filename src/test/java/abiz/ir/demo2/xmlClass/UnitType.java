
package abiz.ir.demo2.xmlClass;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UnitType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UnitType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="ISLAMIC_REPUBLIC_RIAL"/>
 *     &lt;enumeration value="ANNUAL_PERCENTAGE"/>
 *     &lt;enumeration value="YEAR"/>
 *     &lt;enumeration value="MONTH"/>
 *     &lt;enumeration value="PERSIAN_DATE"/>
 *     &lt;enumeration value="BOOLEAN"/>
 *     &lt;enumeration value="STRING"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UnitType", namespace = "http://modernisc.com/api/csd")
@XmlEnum
public enum UnitType {

    ISLAMIC_REPUBLIC_RIAL,
    ANNUAL_PERCENTAGE,
    YEAR,
    MONTH,
    PERSIAN_DATE,
    BOOLEAN,
    STRING;

    public String value() {
        return name();
    }

    public static UnitType fromValue(String v) {
        return valueOf(v);
    }

}
