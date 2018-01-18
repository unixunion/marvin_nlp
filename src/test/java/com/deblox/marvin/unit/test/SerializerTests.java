package com.deblox.marvin.unit.test;

import com.deblox.JsonUtils;
import com.deblox.QueryConsumer;
import com.deblox.rasa.RasaResponse;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

//import io.vertx.core.json.JsonObject;

/**
 * Created by keghol on 2018-01-17.
 */
@RunWith(VertxUnitRunner.class)
public class SerializerTests {
    Vertx vertx;
    EventBus eb;
    private static final Logger logger = LoggerFactory.getLogger(SerializerTests.class);
//    private static Gson gson;
    private String json = "{\n" +
            "    \"intent\": {\n" +
            "        \"name\": \"restaurant_search\",\n" +
            "        \"confidence\": 0.7726615901122014\n" +
            "    },\n" +
            "    \"entities\": [\n" +
            "        {\n" +
            "            \"start\": 8,\n" +
            "            \"end\": 15,\n" +
            "            \"value\": \"chinese\",\n" +
            "            \"entity\": \"cuisine\",\n" +
            "            \"extractor\": \"ner_crf\",\n" +
            "            \"processors\": [\n" +
            "                \"ner_synonyms\"\n" +
            "            ]\n" +
            "        }\n" +
            "    ],\n" +
            "    \"intent_ranking\": [\n" +
            "        {\n" +
            "            \"name\": \"restaurant_search\",\n" +
            "            \"confidence\": 0.7726615901122014\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"affirm\",\n" +
            "            \"confidence\": 0.1124034184093516\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"greet\",\n" +
            "            \"confidence\": 0.06793227546218214\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"goodbye\",\n" +
            "            \"confidence\": 0.04700271601626486\n" +
            "        }\n" +
            "    ],\n" +
            "    \"text\": \"show me chinese restaurants\",\n" +
            "    \"text2\": \"show me chinese restaurants\"\n" +
            "}";

    private String ducklingJson = "{\n" +
            "    \"intent\": {\n" +
            "        \"name\": \"restaurant_search\",\n" +
            "        \"confidence\": 0.7781048834361198\n" +
            "    },\n" +
            "    \"entities\": [\n" +
            "        {\n" +
            "            \"start\": 33,\n" +
            "            \"end\": 34,\n" +
            "            \"text\": \"7\",\n" +
            "            \"value\": 7.0,\n" +
            "            \"additional_info\": {\n" +
            "                \"value\": 7.0\n" +
            "            },\n" +
            "            \"entity\": \"number\",\n" +
            "            \"extractor\": \"ner_duckling\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"start\": 41,\n" +
            "            \"end\": 42,\n" +
            "            \"text\": \"8\",\n" +
            "            \"value\": 8.0,\n" +
            "            \"additional_info\": {\n" +
            "                \"value\": 8.0\n" +
            "            },\n" +
            "            \"entity\": \"number\",\n" +
            "            \"extractor\": \"ner_duckling\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"start\": 33,\n" +
            "            \"end\": 34,\n" +
            "            \"text\": \"7\",\n" +
            "            \"value\": 7.0,\n" +
            "            \"additional_info\": {\n" +
            "                \"value\": 7.0,\n" +
            "                \"unit\": null\n" +
            "            },\n" +
            "            \"entity\": \"distance\",\n" +
            "            \"extractor\": \"ner_duckling\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"start\": 41,\n" +
            "            \"end\": 42,\n" +
            "            \"text\": \"8\",\n" +
            "            \"value\": 8.0,\n" +
            "            \"additional_info\": {\n" +
            "                \"value\": 8.0,\n" +
            "                \"unit\": null\n" +
            "            },\n" +
            "            \"entity\": \"distance\",\n" +
            "            \"extractor\": \"ner_duckling\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"start\": 33,\n" +
            "            \"end\": 34,\n" +
            "            \"text\": \"7\",\n" +
            "            \"value\": 7.0,\n" +
            "            \"additional_info\": {\n" +
            "                \"value\": 7.0,\n" +
            "                \"unit\": null,\n" +
            "                \"latent\": true\n" +
            "            },\n" +
            "            \"entity\": \"volume\",\n" +
            "            \"extractor\": \"ner_duckling\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"start\": 41,\n" +
            "            \"end\": 42,\n" +
            "            \"text\": \"8\",\n" +
            "            \"value\": 8.0,\n" +
            "            \"additional_info\": {\n" +
            "                \"value\": 8.0,\n" +
            "                \"unit\": null,\n" +
            "                \"latent\": true\n" +
            "            },\n" +
            "            \"entity\": \"volume\",\n" +
            "            \"extractor\": \"ner_duckling\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"start\": 33,\n" +
            "            \"end\": 34,\n" +
            "            \"text\": \"7\",\n" +
            "            \"value\": 7.0,\n" +
            "            \"additional_info\": {\n" +
            "                \"value\": 7.0,\n" +
            "                \"unit\": null\n" +
            "            },\n" +
            "            \"entity\": \"temperature\",\n" +
            "            \"extractor\": \"ner_duckling\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"start\": 41,\n" +
            "            \"end\": 42,\n" +
            "            \"text\": \"8\",\n" +
            "            \"value\": 8.0,\n" +
            "            \"additional_info\": {\n" +
            "                \"value\": 8.0,\n" +
            "                \"unit\": null\n" +
            "            },\n" +
            "            \"entity\": \"temperature\",\n" +
            "            \"extractor\": \"ner_duckling\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"start\": 18,\n" +
            "            \"end\": 44,\n" +
            "            \"text\": \"friday between 7pm and 8pm\",\n" +
            "            \"value\": {\n" +
            "                \"to\": \"2018-01-19T21:00:00.000Z\",\n" +
            "                \"from\": \"2018-01-19T19:00:00.000Z\"\n" +
            "            },\n" +
            "            \"additional_info\": {\n" +
            "                \"value\": {\n" +
            "                    \"to\": \"2018-01-19T21:00:00.000Z\",\n" +
            "                    \"from\": \"2018-01-19T19:00:00.000Z\"\n" +
            "                },\n" +
            "                \"others\": [\n" +
            "                    {\n" +
            "                        \"to\": \"2018-01-19T21:00:00.000Z\",\n" +
            "                        \"from\": \"2018-01-19T19:00:00.000Z\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"to\": \"2018-01-26T21:00:00.000Z\",\n" +
            "                        \"from\": \"2018-01-26T19:00:00.000Z\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"to\": \"2018-02-02T21:00:00.000Z\",\n" +
            "                        \"from\": \"2018-02-02T19:00:00.000Z\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            \"entity\": \"time\",\n" +
            "            \"extractor\": \"ner_duckling\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"intent_ranking\": [\n" +
            "        {\n" +
            "            \"name\": \"restaurant_search\",\n" +
            "            \"confidence\": 0.7781048834361198\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"affirm\",\n" +
            "            \"confidence\": 0.10477601742395527\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"goodbye\",\n" +
            "            \"confidence\": 0.06950981820298409\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"greet\",\n" +
            "            \"confidence\": 0.04760928093694081\n" +
            "        }\n" +
            "    ],\n" +
            "    \"text\": \"how about we meet friday between 7pm and 8pm?\"\n" +
            "}";


    @Before
    public void before(TestContext test) {
        vertx = Vertx.vertx();
        eb = vertx.eventBus();

        vertx.deployVerticle(QueryConsumer.class.getName(), res -> {
            if (res.succeeded()) {
                test.async().complete();
            }
        });

    }

    @Test
    public void testDeserializeIntent(TestContext test) {
        Async async = test.async();
        RasaResponse rr = JsonUtils.getGson().fromJson(json, RasaResponse.class);
        test.assertEquals(rr.get(), "{\"intent\":{\"name\":\"restaurant_search\",\"confidence\":\"0.7726615901122014\"},\"entities\":{\"entities\":[{\"start\":8,\"end\":15,\"value\":\"chinese\",\"extractor\":\"ner_crf\",\"processors\":[\"ner_synonyms\"],\"entity\":\"cuisine\"}]},\"intent_ranking\":{\"intents\":[{\"name\":\"restaurant_search\",\"confidence\":\"0.7726615901122014\"},{\"name\":\"affirm\",\"confidence\":\"0.1124034184093516\"},{\"name\":\"greet\",\"confidence\":\"0.06793227546218214\"},{\"name\":\"goodbye\",\"confidence\":\"0.04700271601626486\"}]},\"text\":\"show me chinese restaurants\"}");
        logger.info(rr.get());
        logger.info(rr.getIntent().get());
        logger.info(rr.getEntities().get());
        async.complete();
    }

    @Test
    public void testDeserializeIntentWithDucklings(TestContext test) {
        Async async = test.async();
        RasaResponse rr = JsonUtils.getGson().fromJson(ducklingJson, RasaResponse.class);
        logger.info(rr.get());
        logger.info(rr.getIntent().get());
        logger.info(rr.getEntities().get());
        async.complete();
    }

    @Test
    public void testQueryResponsJson(TestContext test) {
        Async async = test.async();
        eb.send("query", "show me chinese restaurants", resp -> {
            if (resp.succeeded()) {
                logger.info(resp.result().body());
                test.assertEquals(resp.result().body().toString(), "{\"intent\": {\"name\": \"restaurant_search\", \"confidence\": 0.7726615901122014}, \"entities\": [{\"start\": 8, \"end\": 15, \"value\": \"chinese\", \"entity\": \"cuisine\", \"extractor\": \"ner_crf\", \"processors\": [\"ner_synonyms\"]}], \"intent_ranking\": [{\"name\": \"restaurant_search\", \"confidence\": 0.7726615901122014}, {\"name\": \"affirm\", \"confidence\": 0.1124034184093516}, {\"name\": \"greet\", \"confidence\": 0.06793227546218214}, {\"name\": \"goodbye\", \"confidence\": 0.04700271601626486}], \"text\": \"show me chinese restaurants\"}");
                async.complete();
            } else {
                logger.info("error sending to query listener");
                test.fail();
            }
        });
    }

    @Test
    public void testQueryDucklingJson(TestContext test) {
        Async async = test.async();
        eb.send("query", "how about we meet friday between 7pm and 8pm?", resp -> {
            if (resp.succeeded()) {
                logger.info(resp.result().body());
                async.complete();
            } else {
                test.fail();
            }
        });
    }

}
