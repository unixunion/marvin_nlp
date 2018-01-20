package com.deblox.intents;

import com.deblox.Intent;
import com.deblox.JsonUtils;
import com.deblox.nlu.Response;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * Created by keghol on 2018-01-20.
 */
public class HelloIntent extends Intent {

    private static final Logger logger = LoggerFactory.getLogger(HelloIntent.class);

    @Override
    public void start(final Future<Void> startFuture) {
        logger.info("starting up");
        setName("hellointent");
        vertx.eventBus().consumer(this.getName(), event -> {
            handle(event);
        });
    }

    public void handle(Message event) {
        logger.info("handle: " + event.body().toString());

        // check if super wants to deal with this request
        super.handle(event);

        logger.info("examining nlu object");
        Response nluResponse = JsonUtils.getGson().fromJson(event.body().toString(), Response.class);

        logger.info("designated intent: " + nluResponse.getIntent().getName());


    }
}
