package com.example.javafx_gestion_bbdd_tarea_2_3.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    static final String BASE_URL = "http://localhost:8080/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .registerTypeAdapter(StringProperty.class, new StringPropertyAdapter())
                    .registerTypeAdapter(IntegerProperty.class, new IntegerPropertyAdapter())
                    .registerTypeAdapter(DoubleProperty.class, new DoublePropertyAdapter())
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        }
        return retrofit;
    }
}
