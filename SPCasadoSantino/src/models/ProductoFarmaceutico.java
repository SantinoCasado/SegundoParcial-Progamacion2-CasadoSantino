
package models;

import Interfaces.ISerializableCsv;
import java.time.LocalDate;

public abstract class ProductoFarmaceutico implements ISerializableCsv {
    protected String nombreComercial;
    protected String dosis;
    protected LocalDate fechaVencimiento;

    public ProductoFarmaceutico(String nombreComercial, String dosis, LocalDate fechaVencimiento) {
        this.nombreComercial = nombreComercial;
        this.dosis = dosis;
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }


    public String getDosisMedida() {
        return dosis;
    }

    public void setDosisMedida(String dosisMedida) {
        this.dosis = dosisMedida;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre:").append(nombreComercial);
        sb.append(", Dosis: ").append(this.dosis);
        sb.append(", FechaVencimiento:").append(fechaVencimiento);

        return sb.toString();
    }
    
    @Override
    public String toCSV(){
        StringBuilder sb = new StringBuilder();
        

        sb.append(this.nombreComercial).append(",");
        sb.append(this.dosis).append(",");
        sb.append(this.fechaVencimiento).append(",");
        
        return sb.toString();

    }

}
