package com.deblox.servers;

import com.deblox.Sessions;
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
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.util.DiscordException;

/**
 * Consumes question strings on topic "query", formats em to a rasa query and sends them off.
 */
public class QueryConsumer extends AbstractVerticle implements Handler<Message> {
    private static final Logger logger = LoggerFactory.getLogger(QueryConsumer.class);
    public static EventBus eb;
    private WebClient webClient;
    private IDiscordClient discord;
    private Sessions sessions;

    @Override
    public void start(final Future<Void> startFuture) {

        logger.info("Startup with Config: " + config().toString());

        sessions = new Sessions();

        WebClientOptions options = new WebClientOptions()
                .setUserAgent("Marvin/0.0.1");
        options.setKeepAlive(false);
        webClient = WebClient.create(vertx, options);

        eb = vertx.eventBus();

        eb.consumer("query", r -> {
            this.handle(r);
        });

        eb.consumer("ping-address", r -> {
            r.reply("pong!");
        });

        logger.info("Listening on 'query'");

        vertx.setTimer(5000, t-> {
            logger.info("Startup Complete");
            startFuture.complete();
        });

        discord = this.createClient(System.getenv("DISCORD_KEY"), true);
        EventDispatcher dispatcher = discord.getDispatcher();
        dispatcher.registerListener(this);
    }

    @EventSubscriber
    public void onReadyEvent(ReadyEvent event) {
        logger.info("subscribed");
    }

    @EventSubscriber
    public void onMessageReceivedEvent(MessageReceivedEvent event) {
        logger.info("message " + event.getMessage().toString());

        logger.info(discord.getApplicationClientID());
        logger.info(event.getMessage().getAuthor().getStringID());

        if (event.getMessage().getMentions().contains(discord.getOurUser())) {
            eb.send("query", event.getMessage().getContent(), resp -> {
                event.getMessage().reply(resp.result().body().toString());
            });
        }
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

    public IDiscordClient createClient(String token, boolean login) { // Returns a new instance of the Discord client
        ClientBuilder clientBuilder = new ClientBuilder(); // Creates the ClientBuilder instance
        clientBuilder.withToken(token); // Adds the login info to the builder
        try {
            if (login) {
                return clientBuilder.login(); // Creates the client instance and logs the client in
            } else {
                return clientBuilder.build(); // Creates the client instance but it doesn't log the client in yet, you would have to call client.login() yourself
            }
        } catch (DiscordException e) { // This is thrown if there was a problem building the client
            e.printStackTrace();
            return null;
        }
    }

}
