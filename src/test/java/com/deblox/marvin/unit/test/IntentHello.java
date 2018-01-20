package com.deblox.marvin.unit.test;

import com.deblox.Boot;
import com.deblox.Util;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by keghol on 2018-01-20.
 */

@RunWith(VertxUnitRunner.class)
public class IntentHello {
    Vertx vertx;
    EventBus eb;
    JsonObject config;
    private static final Logger logger = LoggerFactory.getLogger(IntentHello.class);

    @Rule
    public RunTestOnContext rule = new RunTestOnContext();

    @Before
    public void before(TestContext context) {
        logger.info("@Before");
        vertx = rule.vertx();
        eb = vertx.eventBus();

        try {
            config = Util.loadConfig(IntentHello.class, "/conf.json");
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        Async async = context.async();
        vertx.deployVerticle(Boot.class.getName(), new DeploymentOptions(new JsonObject().put("config", config)), res -> {
            if (res.succeeded()) {
                async.complete();
            } else {
                context.fail();
            }
        });
    }

    @Test
    public void testPing(TestContext test) {
        Async async = test.async();
        logger.info("sending ping");
        eb.send("hellointent", "ping!", reply -> {
            if (reply.succeeded()) {
                async.complete();
            } else {
                test.fail();
            }
        });
    }

    @Test
    public void testHello(TestContext test) {
        Async async = test.async();

        logger.info("first lets generate a query");
        eb.send("query", "good morning marvin", resp -> {
            if (resp.succeeded()) {
                logger.info("sending to hellointent");
                eb.send("hellointent", resp.result().body(), reply -> {
                    if (reply.succeeded()) {
                        async.complete();
                    } else {
                        test.fail();
                    }
                });
            } else {
                test.fail("unable to reach query service");
            }
        });

        logger.info("sending hello");



    }

}
