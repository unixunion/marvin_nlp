package com.deblox.nlu;

/**
 * Created by keghol on 2018-01-19.
 */
public class Intent {
    private String name;
    private float confidence;

    // -- getters / setters -- //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getConfidence() {
        return confidence;
    }

    public void setConfidence(float confidence) {
        this.confidence = confidence;
    }
}
