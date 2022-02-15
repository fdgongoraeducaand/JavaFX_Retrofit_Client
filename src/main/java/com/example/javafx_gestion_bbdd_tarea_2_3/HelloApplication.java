package com.example.javafx_gestion_bbdd_tarea_2_3;

import com.example.javafx_gestion_bbdd_tarea_2_3.dao.ProductoDAORest;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
     //   ProductoDAORest control = new ProductoDAORest();
     //   control.start();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("productos-vista.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 1000);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}