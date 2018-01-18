package com.deblox.rasa;

import com.deblox.JsonUtils;

import java.io.Serializable;

/**
 * Created by keghol on 2018-01-17.
 */
public class RasaIntent implements Serializable {

    private String name;
    private String confidence;

    // -- constructor --//

    public RasaIntent(String name, String confidence) {
        this.name = name;
        this.confidence = confidence;
    }

    // -- methods -- //

    public String get() {
        return JsonUtils.getGson().toJson(this);
    }

    // -- getters / setters -- //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }
}
