package com.apodaca.clean_architecture.data.network.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateSerializer implements JsonSerializer<Date> {
    private static final SimpleDateFormat formatter =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc,
                                 JsonSerializationContext context) {
        return src == null ? null : new JsonPrimitive(formatter.format(src));
    }
}
