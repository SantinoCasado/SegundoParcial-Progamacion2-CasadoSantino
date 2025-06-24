package Controllers;

import Exceptions.DatoErroneoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import models.Medicamento;
import models.ProductoFarmaceutico;
import models.Suplemento;


public class FormularioController implements Initializable {
    //Botones
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnCancelar;
    @FXML
    private ChoiceBox<String> cbTipoProd;
    
    //Label
    @FXML
    private Label lblDatoExtra;
    
    //Date
    @FXML
    private DatePicker dpFechaVencimiento;
    
    //TxT
    @FXML
    private TextField txtDatoExtra;

    @FXML
    private TextField txtDosis;

    @FXML
    private TextField txtNombre;
    
    private ProductoFarmaceutico producto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Ininicalizo los valores predeterminados del choiseBox
        this.cbTipoProd.getItems().addAll("MEDICAMENTO", "SUPLEMENTO");
        //Le doy un valor inicial
        this.cbTipoProd.setValue("MEDICAMENTO");
    }    
    
    @FXML
    void cambiadoTipo(ActionEvent event) {
        //Dependiento del valor del choiseBox seteo un valor al label del datoExtra
        switch(cbTipoProd.getValue()){
            
            case "MEDICAMENTO" -> lblDatoExtra.setText("Requiere receta Médica? [Si | No]");
            
            case "SUPLEMENTO" -> lblDatoExtra.setText("Objetivo");
        
        }
    }   
    
    //Metodos
    @FXML
    void aceptar(ActionEvent event) {
                
        // Obtiene los datos del formulario
        String tipo = cbTipoProd.getValue();
        String nombre = txtNombre.getText();
        String dosis = txtDosis.getText();
        LocalDate fechaVencimiento = dpFechaVencimiento.getValue();
        String datoExtra = txtDatoExtra.getText().toUpperCase();
        
        // Si vehiculo != null, significa que está editando un vehículo existente:
        if (producto != null) {
            
            //Actualiza sus atributos comunes.
            producto.setNombreComercial(nombre);
            producto.setDosisMedida(dosis);
            producto.setFechaVencimiento(fechaVencimiento);
            
            //Según el tipo, castea el objeto y actualiza el atributo específico (cantPuertas, cilindrada, capacidadCarga).
            switch(tipo){
                case "MEDICAMENTO":
                    if (!datoExtra.equalsIgnoreCase("SI") && !datoExtra.equalsIgnoreCase("NO")) {
                        throw new DatoErroneoException("SOLO SE ACEPTA COMO RESPUESTA [Si | No]");
                    }

                        if (datoExtra.equalsIgnoreCase("SI")){
                        ((Medicamento)producto).setRequerimientoRecetaMediica(true);
                    } else {
                        ((Medicamento)producto).setRequerimientoRecetaMediica(false);
                    }

                case "SUPLEMENTO":
                        ((Suplemento)producto).setObjetivo(datoExtra);
            }
        }
        // Si vehiculo == null, entonces está creando un nuevo vehículo:
        else{
            
            //Usa el switch(tipo) para instanciar la subclase correspondiente (Auto, Moto, Camioneta) con todos los datos.
            switch(tipo){
            
                case "MEDICAMENTO":
                    if (datoExtra.equalsIgnoreCase("SI")){
                         this.producto = new Medicamento(true, nombre, dosis, fechaVencimiento);
                    } else {
                         this.producto = new Medicamento(false, nombre, dosis, fechaVencimiento);
                    }
            
                case "SUPLEMENTO":
                    this.producto = new Suplemento(datoExtra, nombre, dosis, fechaVencimiento);                  
            
            }
        }
        this.cerrar();
    }
    

    @FXML
    void cancelar(ActionEvent event) {
        this.cerrar();
    }
    
    public void setProducto(ProductoFarmaceutico producto) {
        this.producto = producto; 
        if(producto != null){
            this.txtNombre.setText(producto.getNombreComercial());
            this.txtDosis.setText(producto.getDosisMedida());
            this.dpFechaVencimiento.setValue(producto.getFechaVencimiento());
            
            if( producto instanceof Medicamento medicamento) {
                if (medicamento.isRequerimientoRecetaMediica()) {
                    this.txtDatoExtra.setText("Si");
                } else {
                    this.txtDatoExtra.setText("No");
                }
                cbTipoProd.setValue("Medicamento");
            }
            if (producto instanceof Suplemento suplemento) {
                this.txtDatoExtra.setText(String.valueOf(suplemento.getObjetivo()));
                cbTipoProd.setValue("Suplemento");
            }

        }
    }
    
    public ProductoFarmaceutico getProductoFarmaceutico(){
        return this.producto;
    }
    
    //Metodo que cierra el formulario
    @FXML
     private void cerrar(){
        Stage stage = (Stage)btnCancelar.getScene().getWindow();
        stage.close();
    }
}
