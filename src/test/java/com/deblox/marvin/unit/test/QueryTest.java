package com.deblox.marvin.unit.test;

import com.deblox.Boot;
import com.deblox.JsonUtils;
import com.deblox.Util;
import com.deblox.nlu.Response;
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
 * Created by keghol on 2018-01-19.
 */
@RunWith(VertxUnitRunner.class)
public class QueryTest {

    Vertx vertx;
    EventBus eb;
    JsonObject config;
    private static final Logger logger = LoggerFactory.getLogger(BootVerticleTest.class);

    @Rule
    public RunTestOnContext rule = new RunTestOnContext();

    @Before
    public void before(TestContext context) {
        logger.info("@Before");
        vertx = rule.vertx();
        eb = vertx.eventBus();

        try {
            config = Util.loadConfig(BootVerticleTest.class, "/conf.json");
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
    public void testQuery(TestContext test) {
        Async async = test.async();
        logger.info("sending query");
        eb.send("query", "show me chinese restaurants", reply -> {
            if (reply.succeeded()) {
                logger.info(reply.result().body());

                Response response = JsonUtils.getGson().fromJson(reply.result().body().toString(), Response.class);

                logger.info(response);

                async.complete();
            } else {
                test.fail();
            }
        });
    }

    @Test
    public void testQuery2(TestContext test) {
        Async async = test.async();
        logger.info("sending query");
        eb.send("query", "show me chinese restaurants in the south", reply -> {
            if (reply.succeeded()) {
                logger.info(reply.result().body());

                Response response = JsonUtils.getGson().fromJson(reply.result().body().toString(), Response.class);

                logger.info(response);

                async.complete();
            } else {
                test.fail();
            }
        });
    }

}
