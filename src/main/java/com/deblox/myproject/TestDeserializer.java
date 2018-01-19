package com.deblox.myproject;

import com.deblox.rasa.EntityValueTypeEnum;
import com.google.gson.*;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by keghol on 2018-01-18.
 */
public class TestDeserializer implements JsonDeserializer<TestEntity> {

    private static final Logger logger = LoggerFactory.getLogger(TestDeserializer.class);


    private String getValueString(JsonObject json) {
        return json.get("value").getAsString();
    }

    private List<String> getValueStringList(JsonObject json) {
        List<String> l = new ArrayList<>();
        json.get("value").getAsJsonArray().forEach(e -> {
            l.add(e.getAsString());
        });
        return l;
    }

    @Override
    public TestEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        logger.info("deserialize: " + json.toString() + " :type: " + typeOfT.toString());

        JsonObject jsonObject = json.getAsJsonObject();

        for (int x=0; x<EntityValueTypeEnum.values().length; x++) {
            try {
                Class<?> cls = Class.forName("com.deblox.myproject.entitytype." + TestEntityValueTypeEnum.values()[x].toString());

                logger.info("checking class: " + cls.getName());

                // get the field
                Field fField = cls.getField("match");

                // get the field class
                Class<?> fType = fField.getType();

                logger.info("field: " + fField.toString() + " type: " + fType);
                logger.info("field contents: " + fField.get(fType));

                // convert to json object
                io.vertx.core.json.JsonObject jsonMatch = new io.vertx.core.json.JsonObject((String)fField.get(fType));
                logger.info("jsonMatch obj: " + jsonMatch.toString());

                io.vertx.core.json.JsonObject jsonFound = new io.vertx.core.json.JsonObject();

                jsonMatch.forEach(je -> {
                    logger.info("required key: " + je.getKey() + " valuev: " + je.getValue());
                    logger.info("checking data: " + jsonObject.get(je.getKey()));
                    if (jsonObject.has(je.getKey())) {
                        if (jsonObject.get(je.getKey()).getAsString().equals(je.getValue().toString())) {
                            logger.info("Matched requirement ");
                            jsonFound.put(je.getKey(), je.getValue());
                        }
                    }

                });

                jsonFound.forEach(found -> {
                    jsonMatch.remove(found.getKey());
                });

                logger.info("match left overs: " + jsonMatch.toString());
                logger.info("match length " + jsonMatch.size());

                if (jsonMatch.size() == 0) {
                    logger.info("returning");
                    TestEntity<?> te = ((TestEntity) cls.newInstance()).fromJson(jsonObject);
                    return te;
                } else {
                    logger.info("Not matched");
                }

            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }

        logger.info("returning null");
        return null;

    }
}
