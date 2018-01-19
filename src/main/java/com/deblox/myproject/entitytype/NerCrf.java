package com.deblox.myproject.entitytype;

import com.deblox.JsonUtils;
import com.deblox.myproject.TestEntity;

import java.util.List;

/**
 * Created by keghol on 2018-01-19.
 */
public class NerCrf extends TestEntity<String> {

    public static String match = "{\"extractor\":\"ner_crf\"}";

    private List<String> processors;

    public NerCrf() {
        this.setType("NerCrf");
    }

    @Override
    public String get() {
        return JsonUtils.getGson().toJson(this);
    }

    public NerCrf fromJson(com.google.gson.JsonObject json) {
        return JsonUtils.getGson().fromJson(json, this.getClass());
    }

    public List<String> getProcessors() {
        return processors;
    }

    public void setProcessors(List<String> processors) {
        this.processors = processors;
    }
}
