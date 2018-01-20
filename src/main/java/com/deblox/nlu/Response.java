package com.deblox.nlu;

import java.util.List;

/**
 * Created by keghol on 2018-01-19.
 */
public class Response {
    String text;
    Intent intent;
    List<Entity> entities;
    List<Intent> intent_rankings;

    // -- getters / setters -- //

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
}
