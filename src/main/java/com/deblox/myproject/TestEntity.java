package com.deblox.myproject;

import com.deblox.JsonUtils;
import com.deblox.messaging.SkipDeserialization;

import java.io.Serializable;

/**
 * Created by keghol on 2018-01-18.
 */
public abstract class TestEntity<T> implements Serializable{

    String type = "TestEntity";

    String start;
    String end;

    @SkipDeserialization
    T value;

    String extractor;
    String entity;

    // -- methods -- //

    public String get() {
        return JsonUtils.getGson().toJson(this);
    }

    // -- getters / setters -- //


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getExtractor() {
        return extractor;
    }

    public void setExtractor(String extractor) {
        this.extractor = extractor;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public TestEntity<T> fromJson(com.google.gson.JsonObject json) {
        return JsonUtils.getGson().fromJson(json, this.getClass());
    }

//    public T get(String name) throws NoSuchFieldException, IllegalAccessException {
//        Field f = this.getClass().getField(name);
//        Class<?> fType = f.getType();
//        return (T) f.get(fType);
//    }
}
