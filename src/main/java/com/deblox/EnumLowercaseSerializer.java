package com.deblox;

/**
 * Created by keghol on 2018-01-19.
 */
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;


/**
 * The Class EnumLowercaseSerializer.
 */
public class EnumLowercaseSerializer implements JsonSerializer<Enum<?>> {

    /* (non-Javadoc)
     * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
     */
    public JsonElement serialize(Enum<?> src, Type typeOfSrc, JsonSerializationContext context) {
        //lower case and convert "_" into '-';
        String source = src.name().replace('_', '-');
        return context.serialize(source.toLowerCase());
    }

}