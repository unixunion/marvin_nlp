package com.deblox.servers;

import com.deblox.Sessions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.util.DiscordException;

/**
 * Created by keghol on 2018-01-19.
 */
public class DiscordBot extends AbstractVerticle {

    private static final Logger logger = LoggerFactory.getLogger(DiscordBot.class);
    public static EventBus eb;
    private IDiscordClient discord;
    private Sessions sessions;

    @Override
    public void start(final Future<Void> startFuture) {
        logger.info("Startup with Config: " + config().toString());
        sessions = new Sessions();
        eb = vertx.eventBus();

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
