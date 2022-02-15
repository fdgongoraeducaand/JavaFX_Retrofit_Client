package com.example.javafx_gestion_bbdd_tarea_2_3.dao;

import com.google.gson.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.lang.reflect.Type;

public class DoublePropertyAdapter implements
        JsonSerializer<DoubleProperty>,
        JsonDeserializer<DoubleProperty> {
    @Override
    public JsonElement serialize(
            DoubleProperty property,
            Type type,
            JsonSerializationContext jsonSerializationContext
    ) {
        return new JsonPrimitive(
                property.getValue()
        );
    }

    @Override
    public DoubleProperty deserialize(
            JsonElement json,
            Type type,
            JsonDeserializationContext jsonDeserializationContext
    ) throws JsonParseException {
        return new SimpleDoubleProperty(
                json.getAsJsonPrimitive().getAsDouble()
        );
    }
}
