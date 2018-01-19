package com.deblox.rasa.duckling;

import com.deblox.rasa.EntityValue;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.io.Serializable;

/**
 * Created by keghol on 2018-01-18.
 */
public class RasaString extends EntityValue implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(RasaString.class);


    private String type = "RasaString";
    private String value;

    public RasaString() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
