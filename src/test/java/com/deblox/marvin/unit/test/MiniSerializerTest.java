package com.deblox.marvin.unit.test;

import com.deblox.JsonUtils;
import com.deblox.myproject.TestEntity;
import com.deblox.myproject.entitytype.NerCrf;
import com.deblox.myproject.entitytype.NerDucklingNumber;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by keghol on 2018-01-18.
 */

@RunWith(VertxUnitRunner.class)
public class MiniSerializerTest {

    Vertx vertx;
    EventBus eb;

    private static final Logger logger = LoggerFactory.getLogger(MiniSerializerTest.class);


    @Test
    public void testDeserialiseEntity(TestContext test) {
        Async async = test.async();
        JsonObject jo = new JsonObject();
        jo.addProperty("extractor", "ner_crf");
        jo.addProperty("value", "chinese");
        jo.addProperty("entity", "cuisine");
        JsonArray ja = new JsonArray();
        ja.add("a");
        ja.add("b");
        jo.add("processors", ja);
        logger.info("generated json: " + jo.toString());

        NerCrf te = (NerCrf)JsonUtils.fromJson(jo.toString(), TestEntity.class);
        logger.info(te.get());

        test.assertTrue(te.getType().toString().equals("NerCrf"));
        test.assertEquals(te.get(), "{\"processors\":[\"a\",\"b\"],\"type\":\"NerCrf\",\"value\":\"chinese\",\"extractor\":\"ner_crf\",\"entity\":\"cuisine\"}");
        test.assertEquals(te.getExtractor(), "ner_crf");
        test.assertEquals(te.getValue(), "chinese");
        test.assertEquals(te.getEntity(), "cuisine");

        List<String> processorsList = new ArrayList<>();
        processorsList.add("a");
        processorsList.add("b");
        test.assertEquals(te.getProcessors(), processorsList);

        async.complete();
    }

    @Test
    public void testDeserializeDucklingNumber(TestContext test) {
        Async async = test.async();
        String json = "{\n" +
                "            \"start\": 33,\n" +
                "            \"end\": 34,\n" +
                "            \"text\": \"8\",\n" +
                "            \"value\": 8.0,\n" +
                "            \"additional_info\": {\n" +
                "                \"value\": 8.0\n" +
                "            },\n" +
                "            \"entity\": \"number\",\n" +
                "            \"extractor\": \"ner_duckling\"\n" +
                "        }";
        NerDucklingNumber te = (NerDucklingNumber)JsonUtils.fromJson(json, TestEntity.class);
        test.assertTrue(te.getType().toString().equals("NerDucklingNumber"));
        test.assertTrue(te.getStart().equals("33"));
        test.assertTrue(te.getEnd().endsWith("34"));
        test.assertEquals(te.getText(), "8");
        test.assertTrue(te.getValue().doubleValue() == 8.0);
        test.assertEquals(te.getAdditional_info().getValue().doubleValue(), 8.0);
        test.assertEquals(te.getEntity(), "number");
        test.assertEquals(te.getExtractor(), "ner_duckling");
        async.complete();
    }

    @Test
    public void testDeserializeDucklingDistance(TestContext test) {
        String json = "{\n" +
                "            \"start\": 33,\n" +
                "            \"end\": 34,\n" +
                "            \"text\": \"8\",\n" +
                "            \"value\": 8.0,\n" +
                "            \"additional_info\": {\n" +
                "                \"value\": 8.0,\n" +
                "                \"unit\": null\n" +
                "            },\n" +
                "            \"entity\": \"distance\",\n" +
                "            \"extractor\": \"ner_duckling\"\n" +
                "        }";

    }

//    @Test
    public void testDeserializeIntent(TestContext test) {
        Async async = test.async();

        // string
        JsonObject jo = new JsonObject();
        jo.addProperty("entity", "string");
        jo.addProperty("value", "string type value");
        logger.info("generated json: " + jo.toString());
        TestEntity re = JsonUtils.fromJson(jo.toString(), TestEntity.class);
        logger.info(re.get());

        // string array

        JsonElement je = new JsonArray();
        je.getAsJsonArray().add("1");
        je.getAsJsonArray().add("2");

        jo = new JsonObject();
        jo.addProperty("entity", "stringarray");
        jo.add("value", je);

        logger.info("generated json: " + jo.toString());

        re = JsonUtils.fromJson(jo.toString(), TestEntity.class);

        logger.info(re.get());

        async.complete();
    }
}
