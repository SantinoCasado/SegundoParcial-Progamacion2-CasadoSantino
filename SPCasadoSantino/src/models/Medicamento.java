package models;

import Interfaces.ISerializableCsv;
import java.time.LocalDate;


public class Medicamento extends ProductoFarmaceutico implements ISerializableCsv {
    private boolean requerimientoRecetaMediica;
    
    public Medicamento(boolean requerimientoRecetaMediica, String nombreComercial, String dosis, LocalDate fechaVencimiento) {
        super(nombreComercial, dosis,fechaVencimiento);
        this.requerimientoRecetaMediica = requerimientoRecetaMediica;
    }
    
    public boolean isRequerimientoRecetaMediica() {
        return requerimientoRecetaMediica;
    }

    public void setRequerimientoRecetaMediica(boolean requerimientoRecetaMediica) {
        this.requerimientoRecetaMediica = requerimientoRecetaMediica;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Medicamento [");
        sb.append(super.toString());
        sb.append(", requerimientoRecetaMediica:").append(requerimientoRecetaMediica);
        sb.append("]");
        return sb.toString();
    }

    public String toCSV(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toCSV()).append(",Medicamento");    

  
        sb.append(",").append(this.requerimientoRecetaMediica);
        
        return sb.toString();
    }
    
    @Override
    public Medicamento fromCSV(String line) {
        // Divide la línea CSV en partes usando split(",").         
        String[] result = line.split(",");
 
        // Extrae los valores en orden y los convierte a sus tipos correspondientes.        
        String nombre = result[0];
        String dosis = result[1];
        LocalDate fecha = LocalDate.parse(result[2]); // si el CSV tiene formato ISO (yyyy-MM-dd)
        String tipo = result[3];
        boolean requerimientoRecetaMedica = Boolean.parseBoolean(result[4]);
        
        // Crea un nuevo objeto Moto con esos valores.
        Medicamento medicamento = new Medicamento(requerimientoRecetaMedica, nombre, dosis, fecha);
        
        return medicamento;
    }

}
