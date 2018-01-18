package com.deblox.rasa;

import com.deblox.JsonUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by keghol on 2018-01-17.
 */
public class RasaEntities implements Serializable{

    private List<RasaEntity> entities;

    // -- constructors --//

    public RasaEntities() {
        this.entities = new ArrayList<>();
    }

    public RasaEntities(List<RasaEntity> entities) {
        this.entities = entities;
    }

    // -- methods -- //

    public String get() {
        return JsonUtils.getGson().toJson(this);
    }

    // -- getters / setters -- //

    public List<RasaEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<RasaEntity> entities) {
        this.entities = entities;
    }

    public void addEntity(RasaEntity entity) {
        this.entities.add(entity);
    }

}
