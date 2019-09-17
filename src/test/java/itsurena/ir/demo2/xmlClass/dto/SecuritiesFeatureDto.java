package itsurena.ir.demo2.xmlClass.dto;


import itsurena.ir.demo2.xmlClass.SecuritiesFeatureClassType;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class SecuritiesFeatureDto {
    private SecuritiesFeatureClassType featureType;
    private QuantityTypeDto quantity;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    public SecuritiesFeatureClassType getFeatureType() {
        return featureType;
    }

    public void setFeatureType(SecuritiesFeatureClassType featureType) {
        this.featureType = featureType;
    }
    public SecuritiesFeatureDto featureType(SecuritiesFeatureClassType featureType) {
        this.featureType = featureType;
        return this;
    }

    public QuantityTypeDto getQuantity() {
        return quantity;
    }

    public void setQuantity(QuantityTypeDto quantity) {
        this.quantity = quantity;
    }

    public SecuritiesFeatureDto quantityValue(QuantityTypeDto quantityValue) {
        this.quantity = quantityValue;
        return this;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(XMLGregorianCalendar fromDate) {
        Date utilDate = fromDate.toGregorianCalendar().getTime();
        this.fromDate = LocalDateTime.ofInstant( utilDate.toInstant(),
                                            ZoneId.systemDefault() );
    }

    public SecuritiesFeatureDto fromDate(XMLGregorianCalendar fromDate) {
        Date utilDate = fromDate.toGregorianCalendar().getTime();
        this.fromDate = LocalDateTime.ofInstant( utilDate.toInstant(),
                ZoneId.systemDefault() );
        return this;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(XMLGregorianCalendar toDate) {
        Date utilDate = toDate.toGregorianCalendar().getTime();
        this.toDate = LocalDateTime.ofInstant( utilDate.toInstant(),
                ZoneId.systemDefault() );
    }

    public SecuritiesFeatureDto toDate(XMLGregorianCalendar toDate) {
        Date utilDate = toDate.toGregorianCalendar().getTime();
        this.toDate = LocalDateTime.ofInstant( utilDate.toInstant(),
                ZoneId.systemDefault() );
        return this;
    }
}
