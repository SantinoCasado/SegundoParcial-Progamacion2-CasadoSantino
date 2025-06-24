package Controllers;

import Exceptions.ProductoFarmaceuticoRepetidoException;
import Interfaces.ISerializableCsv;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.stage.Modality;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.CsvUtilisGeneric;
import models.Farmacia;
import models.ProductoFarmaceutico;

public class ViewController implements Initializable {

    @FXML
    private ListView<ProductoFarmaceutico> ListViewProductosFarmaceuticos;
    
    
    private Farmacia farmacia;
    
    private CsvUtilisGeneric<ISerializableCsv> herramientaCsv;
    
    //Botones
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnFiltrar;
    
    //ChoiceBox

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
         farmacia = new Farmacia();
         herramientaCsv = new CsvUtilisGeneric<>();
    }    
    
    //Metodos
    @FXML
    void agregar(ActionEvent event) {
        this.AbrirFormulario(null);
    }
    
    public static void main (String[] args){
            Application.launch();
    }
    

    @FXML
    void eliminar(ActionEvent event) {

    }

    @FXML
    void modificar(ActionEvent event) {
         ProductoFarmaceutico producto = ListViewProductosFarmaceuticos.getSelectionModel().getSelectedItem();

         if(producto != null) {
             this.AbrirFormulario(producto);
         }
    }
    
    @FXML
    void filtrar(ActionEvent event) {

    }
    
    public void refrescarVista(){
        this.ListViewProductosFarmaceuticos.getItems().clear();
        this.ListViewProductosFarmaceuticos.getItems().addAll(farmacia.getProductoFarmaceutico());
    }
    
     private void AbrirFormulario(ProductoFarmaceutico producto) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Formulario.fxml"));
    
            Scene scene = new Scene(loader.load());
            FormularioController controlador = loader.getController();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);               
            controlador.setProducto(producto);      
            stage.showAndWait();
            ProductoFarmaceutico resultado = controlador.getProductoFarmaceutico();    
   

                if(resultado != null) {                        
                    if(producto == null){       
                        if (!farmacia.getProductoFarmaceutico().contains(resultado)){       
                            this.farmacia.agregarProductoFarmaceutico(resultado);
                        }
                    }
                }
                this.refrescarVista();
        }
        catch(IOException | ProductoFarmaceuticoRepetidoException e){
            System.out.println("Error:" + e.getMessage());
        }
    }
  }
    

