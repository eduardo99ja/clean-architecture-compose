package com.apodaca.clean_architecture.data.network.serializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateDeserializer implements JsonDeserializer<Date> {

    private static final SimpleDateFormat dateFormatter =
            new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private static final SimpleDateFormat dateTimeFormatter =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    @Override
    public Date deserialize(JsonElement json, Type typeOfT,
                            JsonDeserializationContext context)
            throws JsonParseException {
        try {
            if (json == null) {
                return null;
            }

            if (json.getAsString().length() > 10) {
                return dateTimeFormatter.parse(json.getAsString());
            } else {
                return dateFormatter.parse(json.getAsString());
            }
        } catch (ParseException e) {
            return null;
        }
    }
}
