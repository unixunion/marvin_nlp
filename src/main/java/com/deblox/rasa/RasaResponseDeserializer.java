package com.deblox.rasa;

import com.deblox.messaging.SkipDeserializationAnnotationExclusionStrategy;
import com.deblox.rasa.duckling.DucklingTimeFromTo;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
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

            // typetokens
            Type stringType = new TypeToken<RasaEntity<String>>() {}.getType();
            Type ducklingType = new TypeToken<RasaEntity<DucklingTimeFromTo>>() {}.getType();

//            EntityValue value = null;

            // deserialize classes from enum
            for (int x=0; x<EntityValueTypeEnum.values().length; x++) {
                try {
                    Class<?> cls = Class.forName("com.deblox.rasa.duckling." + EntityValueTypeEnum.values()[x].toString());
                    EntityValue value = delegateGson.fromJson(entity.getAsJsonObject().remove("value"), (Type) cls);
                    logger.info("deserialized as " + cls.getName());
                    Type entityType = new TypeToken<RasaEntity<?>>() {}.getType();
                    RasaEntity re = delegateGson.fromJson(entity, RasaEntity.class);
                    re.setValue(value);
                    entities.addEntity(re);
                    break;
                } catch (ClassNotFoundException e) {
                    logger.error("class not found: " + e.getMessage());
                } catch (Exception e) {
                    logger.error("could not deserialize as: " + e.getMessage());
                }
            }

//            try {
//                value = delegateGson.fromJson(entity.getAsJsonObject().remove("value"), stringType);
//                logger.info("entity value is String");
//            } catch (JsonSyntaxException e) {
//                logger.info("entity is an object, attmpeting to cast");
//
//                // first lets default to generic object
//                value = delegateGson.fromJson(entity.getAsJsonObject().get("value"), EntityValue.class);
//                logger.info("entity value is Object");
//
//                try {
//                    value = delegateGson.fromJson(entity.getAsJsonObject().remove("value"), DucklingTimeFromTo.class);
//                    logger.info("entity cast to: " + DucklingTimeFromTo.class);
//                } catch (Exception dt) {
//                    logger.error("exception casting: " + dt.getMessage());
//                }
//            }



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
