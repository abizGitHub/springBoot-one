package itsurena.ir.demo2.xmlClass.dto;


import itsurena.ir.demo2.xmlClass.UnitType;

public class QuantityTypeDto {

    private String value;
    private UnitType unit;

    public QuantityTypeDto value(String value) {
        this.value = value;
        return this;
    }

    public QuantityTypeDto unit(UnitType unit) {
        this.unit = unit;
        return this;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public UnitType getUnit() {
        return unit;
    }

    public void setUnit(UnitType unit) {
        this.unit = unit;
    }
}
