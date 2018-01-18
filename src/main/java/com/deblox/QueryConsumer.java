package com.deblox;

import com.deblox.myproject.Pinger;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;

/**
 * Consumes question strings on topic "query", formats em to a rasa query and sends them off.
 */
public class QueryConsumer extends AbstractVerticle implements Handler<Message> {
    private static final Logger logger = LoggerFactory.getLogger(Pinger.class);
    public static EventBus eb;
    private WebClient webClient;

    @Override
    public void start(final Future<Void> startFuture) {

        logger.info("Startup with Config: " + config().toString());

        WebClientOptions options = new WebClientOptions()
                .setUserAgent("Marvin/0.0.1");
        options.setKeepAlive(false);
        webClient = WebClient.create(vertx, options);

        eb = vertx.eventBus();

        eb.consumer("query", r -> {
            this.handle(r);
        });

//        eb.consumer("query");

        logger.info("Listening on 'query'");

        vertx.setTimer(5000, t-> {
            logger.info("Startup Complete");
            startFuture.complete();
        });

    }

    @Override
    public void handle(Message message) {
        logger.info("Handling message " + message.body().toString());

        JsonObject query = new JsonObject().put("q", message.body());

        webClient.post(5000, "localhost", "/parse")
                .sendBuffer(query.toBuffer(), ar ->{
                    if (ar.succeeded()) {
                        logger.info("sent to rasa");
                        logger.info("response: " + ar.result().body());
                        message.reply(ar.result().body());
                    }
                });
    }
}
