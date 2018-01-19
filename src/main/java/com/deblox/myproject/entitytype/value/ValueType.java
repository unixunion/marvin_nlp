package com.deblox.myproject.entitytype.value;

import com.deblox.JsonUtils;

/**
 * Created by keghol on 2018-01-19.
 */
public class ValueType<T>  {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String get() {
        return JsonUtils.getGson().toJson(this.value);
    }
}
