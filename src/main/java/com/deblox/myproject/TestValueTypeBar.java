package com.deblox.myproject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by keghol on 2018-01-18.
 */
public class TestValueTypeBar implements Serializable {

    private String type = "TestValueTypeBar";

    List<String> value;

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
}
