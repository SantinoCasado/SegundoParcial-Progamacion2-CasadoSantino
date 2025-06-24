package models;

import Exceptions.ProductFarmaVencidoException;
import Exceptions.ProductoFarmaceuticoRepetidoException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Farmacia {
    private ArrayList<ProductoFarmaceutico> productosFarmaceuticos;

    public Farmacia() {
        this.productosFarmaceuticos = new ArrayList<>();
    }
    
    public void agregarProductoFarmaceutico(ProductoFarmaceutico producto){
        if (this.productosFarmaceuticos.contains(producto)){
           throw new ProductoFarmaceuticoRepetidoException("El medicamento ya se encuentra cargado!");
       }
        if (producto.fechaVencimiento.isBefore(LocalDate.now())){
           throw new ProductFarmaVencidoException("El medicamento esta vencido!");
       }
       this.productosFarmaceuticos.add(producto);
       System.out.println("medicamento Agregado");
    }

    public void eliminarProductoFarmaceutico(ProductoFarmaceutico producto){
        this.productosFarmaceuticos.remove(producto);
    }

    public ArrayList<ProductoFarmaceutico> getProductoFarmaceutico(){
        return this.productosFarmaceuticos;
    }
    
    
}
