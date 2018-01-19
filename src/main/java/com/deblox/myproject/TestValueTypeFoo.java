package com.deblox.myproject;

import java.io.Serializable;

/**
 * Created by keghol on 2018-01-18.
 */
public class TestValueTypeFoo implements Serializable{

    private String type = "TestValueTypeFoo";

    String value;

    public TestValueTypeFoo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
