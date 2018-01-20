package com.deblox.marvin.unit.test;

import com.deblox.JsonUtils;
import com.deblox.nlu.Response;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by keghol on 2018-01-19.
 */
@RunWith(VertxUnitRunner.class)
public class SimpleResponseTests {
    private static final Logger logger = LoggerFactory.getLogger(SimpleResponseTests.class);

    @Test
    public void testDeserialiseEntity(TestContext test) {
        String json = "{\"intent\": {\"name\": \"restaurant_search\", \"confidence\": 0.7726615901122014}, \"entities\": [{\"start\": 8, \"end\": 15, \"value\": \"chinese\", \"entity\": \"cuisine\", \"extractor\": \"ner_crf\", \"processors\": [\"ner_synonyms\"]}], \"intent_ranking\": [{\"name\": \"restaurant_search\", \"confidence\": 0.7726615901122014}, {\"name\": \"affirm\", \"confidence\": 0.1124034184093516}, {\"name\": \"greet\", \"confidence\": 0.06793227546218214}, {\"name\": \"goodbye\", \"confidence\": 0.04700271601626486}], \"text\": \"show me chinese restaurants\"}";

        Response response = JsonUtils.getGson().fromJson(json, Response.class);
        logger.info(response.getText());
        logger.info(response.getIntent());
        response.getEntities().forEach(entity -> {
            logger.info(entity.getStart());
            logger.info(entity.getEnd());
            logger.info(entity.getValue());
            logger.info(entity.getEntity());
            logger.info(entity.getExtractor());
            logger.info(entity.getProcessors());
        });
    }

}
