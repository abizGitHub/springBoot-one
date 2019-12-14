
package abiz.ir.demo2.xmlClass;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SecuritiesFeatureClassType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SecuritiesFeatureClassType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="SELLING_START_DATE"/>
 *     &lt;enumeration value="SELLING_END_DATE"/>
 *     &lt;enumeration value="SECURITIES_DURATION"/>
 *     &lt;enumeration value="INTEREST_PAYMENT_INTERVAL"/>
 *     &lt;enumeration value="COUPON_RATE"/>
 *     &lt;enumeration value="REDEMPTION_RATE"/>
 *     &lt;enumeration value="PAR_VALUE"/>
 *     &lt;enumeration value="NON_REDEEMABLE"/>
 *     &lt;enumeration value="NON_TRANSFERABLE"/>
 *     &lt;enumeration value="FINAL_RATE_OF_RETURN"/>
 *     &lt;enumeration value="BOND_TYPE"/>
 *     &lt;enumeration value="ISSUE_SUBJECT"/>
 *     &lt;enumeration value="GUARANTOR"/>
 *     &lt;enumeration value="TRUSTEE"/>
 *     &lt;enumeration value="OBLIGOR"/>
 *     &lt;enumeration value="ISSUER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SecuritiesFeatureClassType", namespace = "http://modernisc.com/api/csd")
@XmlEnum
public enum SecuritiesFeatureClassType {

    SELLING_START_DATE,
    SELLING_END_DATE,
    SECURITIES_DURATION,
    INTEREST_PAYMENT_INTERVAL,
    COUPON_RATE,
    REDEMPTION_RATE,
    PAR_VALUE,
    NON_REDEEMABLE,
    NON_TRANSFERABLE,
    FINAL_RATE_OF_RETURN,
    BOND_TYPE,
    ISSUE_SUBJECT,
    GUARANTOR,
    TRUSTEE,
    OBLIGOR,
    ISSUER;

    public String value() {
        return name();
    }

    public static SecuritiesFeatureClassType fromValue(String v) {
        return valueOf(v);
    }

}
