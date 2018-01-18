package com.deblox.rasa;

import com.deblox.JsonUtils;
import com.deblox.messaging.SkipDeserialization;

import java.io.Serializable;

/**
 * Created by keghol on 2018-01-17.
 */
public class RasaEntity implements Serializable {
    private int start;
    private int end;
    private String value;
    private String extractor;
    private String[] processors;
    private String entity;

    @SkipDeserialization
    private Object additional_info;

    // -- constructors --//

    public RasaEntity(int start, int end, String value, String extractor, String[] processors, String entity) {
        this.start = start;
        this.end = end;
        this.value = value;
        this.extractor = extractor;
        this.processors = processors;
        this.entity = entity;
    }

    // -- methods -- //

    public String get() {
        return JsonUtils.getGson().toJson(this);
    }

    // -- getters / setters -- //

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getExtractor() {
        return extractor;
    }

    public void setExtractor(String extractor) {
        this.extractor = extractor;
    }

    public String[] getProcessors() {
        return processors;
    }

    public void setProcessors(String[] processors) {
        this.processors = processors;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Object getAdditional_info() {
        return additional_info;
    }

    public void setAdditional_info(Object additional_info) {
        this.additional_info = additional_info;
    }
}
