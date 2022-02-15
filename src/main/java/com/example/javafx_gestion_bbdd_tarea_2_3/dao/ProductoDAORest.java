package com.example.javafx_gestion_bbdd_tarea_2_3.dao;

import com.example.javafx_gestion_bbdd_tarea_2_3.interfaces.ProductoInterface;
import com.example.javafx_gestion_bbdd_tarea_2_3.modelos.Producto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class ProductoDAORest implements Callback<ObservableList<Producto>>  {
    static final String BASE_URL = "http://localhost:8080/";

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .registerTypeAdapter(StringProperty.class,new StringPropertyAdapter())
                .registerTypeAdapter(IntegerProperty.class,new IntegerPropertyAdapter())
                .registerTypeAdapter(DoubleProperty.class,new DoublePropertyAdapter())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ProductoInterface productosAPI = retrofit.create(ProductoInterface.class);

        Call<ObservableList<Producto>> call = productosAPI.listProductos();
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<ObservableList<Producto>> call, Response<ObservableList<Producto>> response) {
        if(response.isSuccessful()) {
            List<Producto> changesList = response.body();
            changesList.forEach(change -> System.out.println(change.getProductName()));
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<ObservableList<Producto>> call, Throwable throwable) {
        throwable.printStackTrace();
    }

}
