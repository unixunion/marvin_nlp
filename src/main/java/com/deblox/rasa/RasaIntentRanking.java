package com.deblox.rasa;

import com.deblox.JsonUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by keghol on 2018-01-17.
 */
public class RasaIntentRanking implements Serializable {

    private List<RasaIntent> intents;

    // -- constructor --//

    public RasaIntentRanking(List<RasaIntent> intents) {
        this.intents = intents;
    }

    public RasaIntentRanking() {
        this.intents = new ArrayList<>();
    }

    // -- methods -- //

    public String get() {
        return JsonUtils.getGson().toJson(this);
    }

    public void addIntentRanking(RasaIntent re) {
        this.intents.add(re);
    }

    // -- getter / setters -- //

    public List<RasaIntent> getIntents() {
        return intents;
    }

    public void setIntents(List<RasaIntent> intents) {
        this.intents = intents;
    }


}
