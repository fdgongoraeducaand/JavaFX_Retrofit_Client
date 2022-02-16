package com.example.javafx_gestion_bbdd_tarea_2_3.controladores;

import com.example.javafx_gestion_bbdd_tarea_2_3.dao.APIClient;
import com.example.javafx_gestion_bbdd_tarea_2_3.dao.ProductoDAO;
import com.example.javafx_gestion_bbdd_tarea_2_3.interfaces.ProductoInterface;
import com.example.javafx_gestion_bbdd_tarea_2_3.modelos.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.NumberStringConverter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class ProductosController {

    private ProductoDAO productDAO = new ProductoDAO();
    private Producto productoAux;
    private ObservableList <Producto> datos;

    @javafx.fxml.FXML
    private TableColumn tcProductVendor;
    @javafx.fxml.FXML
    private Button btnBorrar;
    @javafx.fxml.FXML
    private TextField txtVendedor;
    @javafx.fxml.FXML
    private TextField txtStock;
    @javafx.fxml.FXML
    private TableColumn tcBuyPrice;
    @javafx.fxml.FXML
    private TableView<Producto> tvProductos;
    @javafx.fxml.FXML
    private TableColumn tcQuantityInStock;
    @javafx.fxml.FXML
    private TextField txtID;
    @javafx.fxml.FXML
    private TextField txtNombre;
    @javafx.fxml.FXML
    private TextField txtEscala;
    @javafx.fxml.FXML
    private TableColumn tcProductDescription;
    @javafx.fxml.FXML
    private TextField txtPrecioCompra;
    @javafx.fxml.FXML
    private TableColumn tcProductScale;
    @javafx.fxml.FXML
    private Button btnAlta;
    @javafx.fxml.FXML
    private TableColumn tcMSRP;
    @javafx.fxml.FXML
    private TextField txtPrecioVenta;
    @javafx.fxml.FXML
    private TextField txtLinea;
    @javafx.fxml.FXML
    private TableColumn tcProductName;
    @javafx.fxml.FXML
    private TableColumn tcProductLine;
    @javafx.fxml.FXML
    private TextField txtDescripcion;
    @javafx.fxml.FXML
    private Button btnActualizar;
    @javafx.fxml.FXML
    private TableColumn tcProductCode;

    public void initialize()  {
        obtenerDatosRest();
        productoAux = new Producto("","","",
                "","","",0,0d,0d);
        realizarBindingsProductoAux(productoAux);
        cargarGestorDobleCLick();
    }

    private void obtenerDatosRest() {
        ProductoInterface apiService = APIClient.getClient().create(ProductoInterface.class);

        Call<ObservableList<Producto>> call = apiService.cargarProductos();
        call.enqueue(new Callback<ObservableList<Producto>>() {

            @Override
            public void onResponse(Call<ObservableList<Producto>> call, Response<ObservableList<Producto>> response) {
                if(response.isSuccessful()) {
                    List<Producto> datosaux = response.body();
                    datos = FXCollections.observableList(datosaux);
                   // datosaux.forEach(prod -> datos.add(prod));
                    cargarDatosTabla();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "No se ha podido conectar con el servidor. Inténtelo más tarde. ", ButtonType.OK );
                    alert.showAndWait();
                }
            }

            @Override
            public void onFailure(Call<ObservableList<Producto>> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });

    }

    private void borrarProductoRest(String codProducto) {
        ProductoInterface apiService = APIClient.getClient().create(ProductoInterface.class);

        Call<Void> call = apiService.borrarProducto(codProducto);
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                   obtenerDatosRest();
                } else {
                  //  Alert alert = new Alert(Alert.AlertType.ERROR, "No se ha podido borrar el producto. ", ButtonType.OK );
                  //  alert.showAndWait();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });

    }

    private void anadirProductoRest(Producto pro) {
        ProductoInterface apiService = APIClient.getClient().create(ProductoInterface.class);

        Call<Void> call = apiService.altaProducto(productoAux);
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                    obtenerDatosRest();
                } else {
                    //  Alert alert = new Alert(Alert.AlertType.ERROR, "No se ha podido borrar el producto. ", ButtonType.OK );
                    //  alert.showAndWait();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });

    }

    private void actualizarProductoRest(Producto pro) {
        ProductoInterface apiService = APIClient.getClient().create(ProductoInterface.class);

        Call<Void> call = apiService.actualizarProducto( productoAux.getProductCode() , productoAux);
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                    obtenerDatosRest();
                } else {
                    //  Alert alert = new Alert(Alert.AlertType.ERROR, "No se ha podido borrar el producto. ", ButtonType.OK );
                    //  alert.showAndWait();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });

    }

    private void cargarDatosTabla () {

        tcProductCode.setCellValueFactory(new PropertyValueFactory<Producto, String>("productCode"));
        tcProductDescription.setCellValueFactory(new PropertyValueFactory<Producto, String>("productDescription"));
        tcProductLine.setCellValueFactory(new PropertyValueFactory<Producto, String>("productLine"));
        tcProductName.setCellValueFactory(new PropertyValueFactory<Producto, String>("productName"));
        tcProductScale.setCellValueFactory(new PropertyValueFactory<Producto, String>("productScale"));
        tcProductVendor.setCellValueFactory(new PropertyValueFactory<Producto, String>("productVendor"));

        tcBuyPrice.setCellValueFactory(new PropertyValueFactory<Producto, Double>("buyPrice"));
        tcMSRP.setCellValueFactory(new PropertyValueFactory<Producto, Double>("MSRP"));
        tcQuantityInStock.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("quantityInStock"));

        if (datos != null) {
            tvProductos.setItems(datos);
        }
    }

    private void realizarBindingsProductoAux ( Producto producto) {

        txtEscala.textProperty().bindBidirectional(productoAux.productScaleProperty());
        txtDescripcion.textProperty().bindBidirectional(producto.productDescriptionProperty());
        txtID.textProperty().bindBidirectional(producto.productCodeProperty());
        txtLinea.textProperty().bindBidirectional(producto.productLineProperty());
        txtNombre.textProperty().bindBidirectional(producto.productNameProperty());
        txtPrecioCompra.textProperty().bindBidirectional(producto.buyPriceProperty(),new NumberStringConverter());
        txtPrecioVenta.textProperty().bindBidirectional(producto.MSRPProperty(), new NumberStringConverter() );
        txtStock.textProperty().bindBidirectional(producto.quantityInStockProperty(), new NumberStringConverter() );
        txtVendedor.textProperty().bindBidirectional(producto.productVendorProperty());
    }

    // Detectar el doble click en la tabla y cargar los datos en los campos de edición
    private void cargarGestorDobleCLick () {
        tvProductos.setRowFactory(tv -> {
            TableRow<Producto> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    productoAux.setProductCode(row.getItem().getProductCode());
                    productoAux.setProductLine(row.getItem().getProductLine());
                    productoAux.setProductName(row.getItem().getProductName());
                    productoAux.setProductScale(row.getItem().getProductScale());
                    productoAux.setProductVendor(row.getItem().getProductVendor());
                    productoAux.setProductDescription(row.getItem().getProductDescription());
                    productoAux.setBuyPrice(row.getItem().getBuyPrice());
                    productoAux.setMSRP(row.getItem().getMSRP());
                    productoAux.setQuantityInStock(row.getItem().getQuantityInStock());
                }
            });
            return row;
        });
    }

    @javafx.fxml.FXML
    public void onActualizarClicked(ActionEvent actionEvent) {

        if ( ! productoAux.getProductCode().trim().equals("")) {
            actualizarProductoRest(productoAux);
          /*  } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "No se ha encontrado un producto con el código '"
                         + productoAux.getProductCode() + "' .", ButtonType.OK );
                alert.showAndWait();
            }
            */

        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Debe indicar el código del producto a actualizar.", ButtonType.OK );
            alert.showAndWait();
        }



    }

    @javafx.fxml.FXML
    public void onAltaClicked(ActionEvent actionEvent) {
        if ( ! productoAux.getProductCode().trim().equals("")) {
            anadirProductoRest(productoAux);

        }
        else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Debe introducir un código de producto.", ButtonType.OK );
                alert.showAndWait();
            }


    }

    @javafx.fxml.FXML
    public void onBorrarClicked(ActionEvent actionEvent) {

        Alert alert;

        if ( ! productoAux.getProductCode().trim().equals("")) {

            alert = new Alert(Alert.AlertType.CONFIRMATION, "¿ Desea borrar el producto con el código '"
                    + productoAux.getProductCode() + "' ?.", ButtonType.YES, ButtonType.NO );

            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {

                borrarProductoRest(productoAux.getProductCode());
            }
        }
        else {
            alert = new Alert(Alert.AlertType.INFORMATION, "Debe indicar el código del producto a borrar.", ButtonType.OK );
            alert.showAndWait();
        }
    }
}
