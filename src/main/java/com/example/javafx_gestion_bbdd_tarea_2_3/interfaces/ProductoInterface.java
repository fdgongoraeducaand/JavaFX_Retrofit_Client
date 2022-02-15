package com.example.javafx_gestion_bbdd_tarea_2_3.interfaces;

import com.example.javafx_gestion_bbdd_tarea_2_3.modelos.Producto;
import javafx.collections.ObservableList;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ProductoInterface {
    @GET("productos")
    Call<ObservableList<Producto>> cargarProductos();

    @DELETE("productos/{productCode}")
    Call<Void> borrarProducto(@Path("productCode") String productCode);

    @POST("productos")
    Call<Void> altaProducto(@Body Producto product);
}
