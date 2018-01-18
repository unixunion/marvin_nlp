package com.deblox.rasa;


import com.deblox.JsonUtils;

import java.io.Serializable;

/**
 * Created by keghol on 2018-01-17.
 */
public class RasaResponse implements Serializable {

    private RasaIntent intent;
    private RasaEntities entities;
    private RasaIntentRanking intent_ranking;
    private String text;

    // -- constructor --//

    public RasaResponse(RasaIntent intent, RasaEntities entities, RasaIntentRanking intent_ranking, String text) {
        this.intent = intent;
        this.entities = entities;
        this.intent_ranking = intent_ranking;
        this.text = text;
    }

    public RasaResponse() {

    }

    // -- methods -- //

    public String get() {
        return JsonUtils.getGson().toJson(this);
    }

    // -- getters / setters -- //

    public RasaIntent getIntent() {
        return intent;
    }

    public void setIntent(RasaIntent intent) {
        this.intent = intent;
    }

    public RasaEntities getEntities() {
        return entities;
    }

    public void setEntities(RasaEntities entities) {
        this.entities = entities;
    }

    public RasaIntentRanking getIntent_ranking() {
        return intent_ranking;
    }

    public void setIntent_ranking(RasaIntentRanking intent_ranking) {
        this.intent_ranking = intent_ranking;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
