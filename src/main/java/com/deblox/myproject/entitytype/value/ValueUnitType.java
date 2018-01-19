package com.deblox.myproject.entitytype.value;

/**
 * Created by keghol on 2018-01-19.
 */
public class ValueUnitType<T> extends ValueType<T> {
    private String unit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
