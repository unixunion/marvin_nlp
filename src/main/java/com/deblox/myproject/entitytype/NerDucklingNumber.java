package com.deblox.myproject.entitytype;

import com.deblox.JsonUtils;
import com.deblox.myproject.TestEntity;
import com.deblox.myproject.entitytype.value.ValueType;

/**
 * Created by keghol on 2018-01-19.
 */
public class NerDucklingNumber extends TestEntity<Number> {

    public static String match = "{\"extractor\":\"ner_duckling\", \"entity\": \"number\"}";

    private ValueType<Number> additional_info;
    private String text;

    public NerDucklingNumber() {
        this.setType("NerDucklingNumber");
    }

    @Override
    public String get() {
        return JsonUtils.getGson().toJson(this);
    }

    public NerDucklingNumber fromJson(com.google.gson.JsonObject json) {
        return JsonUtils.getGson().fromJson(json, this.getClass());
    }

    public ValueType<Number> getAdditional_info() {
        return additional_info;
    }

    public void setAdditional_info(ValueType<Number> additional_info) {
        this.additional_info = additional_info;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
