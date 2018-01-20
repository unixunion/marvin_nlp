package com.deblox;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;

/**
 * Created by keghol on 2018-01-19.
 */
public class Intent extends AbstractVerticle {

    String name;
    private boolean active = false;

    // -- constructors -- //



    // -- methods -- //

    public void handle(Message event) {
        if (event.body().toString().equals("ping!")) {
            event.reply("pong!");
        }
    }


    // -- getters / setters //

    public boolean isActive() {
        return active;
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
