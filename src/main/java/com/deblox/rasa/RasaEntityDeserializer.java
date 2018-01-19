package com.deblox.rasa;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.lang.reflect.Type;

/**
 * Created by keghol on 2018-01-18.
 *
 *
 * // duckling
 *
 *
 *
 */
public class RasaEntityDeserializer implements JsonDeserializer<RasaEntity> {

    private static final Logger logger = LoggerFactory.getLogger(RasaEntityDeserializer.class);

    @Override
    public RasaEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        logger.info("deserialize: " + json.toString());

        if (json.getAsJsonObject().get("extractor").toString().equals("ner_crf")) {
            // string
            return new RasaEntity<String>();
        } else {
            return new RasaEntity();
        }

//        RasaEntity<?> = new RasaEntity<String>()
//        for (int x=0; x<EntityValueTypeEnum.values().length; x++) {
//            try {
//                Class<?> cls = Class.forName("com.deblox.rasa.duckling." + EntityValueTypeEnum.values()[x].toString());
//            } catch (Exception e) {
//                logger.warn("crash");
//            }
//
//        RasaEntity<?> re = new RasaEntity<>();

//        return new RasaEntity();

//        RasaEntity<>
    }
}
