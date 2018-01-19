package com.deblox.marvin.unit.test;

import com.deblox.JsonUtils;
import com.deblox.rasa.RasaEntity;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by keghol on 2018-01-18.
 */

@RunWith(VertxUnitRunner.class)
public class SerializerClassTests {

    Vertx vertx;
    EventBus eb;

    private static final Logger logger = LoggerFactory.getLogger(SerializerClassTests.class);

    private String jsonEntity1 = "{\n" +
            "      \"start\": 8,\n" +
            "      \"end\": 15,\n" +
            "      \"value\": \"chinese\",\n" +
            "      \"entity\": \"cuisine\",\n" +
            "      \"extractor\": \"ner_crf\",\n" +
            "      \"processors\": [\n" +
            "        \"ner_synonyms\"\n" +
            "      ]\n" +
            "    }";

    private String jsonEntity2 = "{\n" +
            "      \"start\": 31,\n" +
            "      \"end\": 37,\n" +
            "      \"value\": \"centre\",\n" +
            "      \"entity\": \"location\",\n" +
            "      \"extractor\": \"ner_crf\"\n" +
            "    }";

    private String jsonDuckling1 = "{\n" +
            "      \"start\": 33,\n" +
            "      \"end\": 34,\n" +
            "      \"text\": \"7\",\n" +
            "      \"value\": 7,\n" +
            "      \"additional_info\": {\n" +
            "        \"value\": 7\n" +
            "      },\n" +
            "      \"entity\": \"number\",\n" +
            "      \"extractor\": \"ner_duckling\"\n" +
            "    }";

    private String jsonDuckling2 = "{\n" +
            "      \"start\": 33,\n" +
            "      \"end\": 34,\n" +
            "      \"text\": \"7\",\n" +
            "      \"value\": 7,\n" +
            "      \"additional_info\": {\n" +
            "        \"value\": 7,\n" +
            "        \"unit\": null\n" +
            "      },\n" +
            "      \"entity\": \"distance\",\n" +
            "      \"extractor\": \"ner_duckling\"\n" +
            "    }";

    private String jsonDuckling3 = "{\n" +
            "      \"start\": 33,\n" +
            "      \"end\": 34,\n" +
            "      \"text\": \"7\",\n" +
            "      \"value\": 7,\n" +
            "      \"additional_info\": {\n" +
            "        \"value\": 7,\n" +
            "        \"unit\": null,\n" +
            "        \"latent\": true\n" +
            "      },\n" +
            "      \"entity\": \"volume\",\n" +
            "      \"extractor\": \"ner_duckling\"\n" +
            "    }";

    private String getJsonDuckling4 = "{\n" +
            "      \"start\": 18,\n" +
            "      \"end\": 44,\n" +
            "      \"text\": \"friday between 7pm and 8pm\",\n" +
            "      \"value\": {\n" +
            "        \"to\": \"2018-01-19T21:00:00.000Z\",\n" +
            "        \"from\": \"2018-01-19T19:00:00.000Z\"\n" +
            "      },\n" +
            "      \"additional_info\": {\n" +
            "        \"value\": {\n" +
            "          \"to\": \"2018-01-19T21:00:00.000Z\",\n" +
            "          \"from\": \"2018-01-19T19:00:00.000Z\"\n" +
            "        },\n" +
            "        \"others\": [\n" +
            "          {\n" +
            "            \"to\": \"2018-01-19T21:00:00.000Z\",\n" +
            "            \"from\": \"2018-01-19T19:00:00.000Z\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"to\": \"2018-01-26T21:00:00.000Z\",\n" +
            "            \"from\": \"2018-01-26T19:00:00.000Z\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"to\": \"2018-02-02T21:00:00.000Z\",\n" +
            "            \"from\": \"2018-02-02T19:00:00.000Z\"\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"entity\": \"time\",\n" +
            "      \"extractor\": \"ner_duckling\"\n" +
            "    }";

    @Test
    public void testDeserializeIntent(TestContext test) {
        Async async = test.async();

        RasaEntity<String> re = JsonUtils.fromJson(jsonEntity1, RasaEntity.class);

        logger.info(re.get());

        async.complete();
    }
}
