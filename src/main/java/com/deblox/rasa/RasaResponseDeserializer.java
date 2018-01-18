package com.deblox.rasa;

import com.deblox.messaging.SkipDeserializationAnnotationExclusionStrategy;
import com.google.gson.*;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.lang.reflect.Type;


public class RasaResponseDeserializer implements JsonDeserializer<RasaResponse> {

    private static Gson delegateGson;
    private static final Logger logger = LoggerFactory.getLogger(RasaResponseDeserializer.class);

    /**
     * Instantiates a new AttachmentDeserializer.
     */
    public RasaResponseDeserializer() {
        GsonBuilder builder = new GsonBuilder();
        builder.addDeserializationExclusionStrategy(new SkipDeserializationAnnotationExclusionStrategy());
        delegateGson = builder.create();
    }

    // deserialize entity
    public RasaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        // text
        String text = delegateGson.fromJson(json.getAsJsonObject().remove("text"), String.class);

        // intent
        RasaIntent intent = delegateGson.fromJson(json.getAsJsonObject().remove("intent"), RasaIntent.class);

        // entities
        RasaEntities entities = new RasaEntities();
        json.getAsJsonObject().getAsJsonArray("entities").forEach(entity -> {
            logger.info("deserializing: " + entity.toString());
            RasaEntity re = delegateGson.fromJson(entity, RasaEntity.class);
            entities.addEntity(re);
        });
        json.getAsJsonObject().remove("entities");

        // intent rankings
        RasaIntentRanking intentRanking = new RasaIntentRanking();
        json.getAsJsonObject().getAsJsonArray("intent_ranking").forEach(entity -> {
            RasaIntent re = delegateGson.fromJson(entity, RasaIntent.class);
            intentRanking.addIntentRanking(re);
        });
        json.getAsJsonObject().remove("intent_ranking");

        // warn if leftovers
        json.getAsJsonObject().entrySet().forEach(e -> {
            logger.warn("json still has keys: " + e.getKey());
        });


//          RasaEntities re =  delegateGson.fromJson(json.getAsJsonObject().get("entities"), RasaEntities.class);
//        RasaIntentRanking intent_ranking;
//
//        RasaResponse entity = delegateGson.fromJson(json, RasaResponse.class);
//
//        JsonElement value = json.getAsJsonObject().get("value");
//        RasaEntityValue rs;
//        rs = new StringEntityValue();
//        ((StringEntityValue)rs).setStringValue(value.toString());
//
////        entity.setValue(rs);
        RasaResponse rr = new RasaResponse();
        rr.setIntent(intent);
        rr.setEntities(entities);
        rr.setIntent_ranking(intentRanking);
        rr.setText(text);
        return rr;
    }
}
