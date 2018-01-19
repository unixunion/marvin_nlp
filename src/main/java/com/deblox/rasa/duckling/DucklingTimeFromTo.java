package com.deblox.rasa.duckling;

import com.deblox.rasa.RasaEntity;

import java.io.Serializable;

/**
 * Created by keghol on 2018-01-18.
 */
public class DucklingTimeFromTo extends RasaEntity implements Serializable {

    private String type = "DucklingTimeFromTo";

    private String from;
    private String to;

    // -- getters / setters --//

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }


}
