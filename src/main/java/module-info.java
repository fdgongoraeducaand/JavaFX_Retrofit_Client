module com.example.javafx_gestion_bbdd_tarea_2_3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires retrofit2;
    requires gson;
    requires retrofit2.converter.gson;
    requires javafx.base;

    opens com.example.javafx_gestion_bbdd_tarea_2_3 to javafx.fxml , gson;
    exports com.example.javafx_gestion_bbdd_tarea_2_3;

    opens com.example.javafx_gestion_bbdd_tarea_2_3.controladores to javafx.fxml, gson;
    exports com.example.javafx_gestion_bbdd_tarea_2_3.controladores;

    opens com.example.javafx_gestion_bbdd_tarea_2_3.modelos to javafx.fxml, gson;
    exports com.example.javafx_gestion_bbdd_tarea_2_3.modelos;

    opens com.example.javafx_gestion_bbdd_tarea_2_3.interfaces to javafx.fxml, gson;
    exports com.example.javafx_gestion_bbdd_tarea_2_3.interfaces;

    exports com.example.javafx_gestion_bbdd_tarea_2_3.dao;
    opens  com.example.javafx_gestion_bbdd_tarea_2_3.dao to  gson;


}