package models;

import inter.Comestible;
import inter.Descuento;

public class Envasado extends Producto implements Comestible , Descuento {

    TipoEnvase tipoEnvase;
    boolean importado;
    String fechaVencimiento;
    int calorias;
    public Envasado(String descripcion, int cantidad, double precio, double costo,  TipoEnvase tipoEnvase, boolean importado) {
        super(descripcion, cantidad, precio, costo);
        tipoProducto=TipoProducto.ENVASADO;
        this.tipoEnvase = tipoEnvase;
        this.importado = importado;
        identificador="AB"+getId();
        if(importado){

            aplicarImpuesto(descripcion);

        }
    }
    //SETTERS
    public void setTipoEnvase(TipoEnvase tipoEnvase) {
        this.tipoEnvase = tipoEnvase;
    }
    public void setImportado(boolean importado) {
        this.importado = importado;
        if(importado)aplicarImpuesto(descripcion);

    }

    @Override
    public void setFechaVencimiento(String fecha) {
        this.fechaVencimiento = fecha;
    }
    @Override
    public void setCalorias(int calorias) {
        this.calorias=calorias;
    }

    //GETTERS
    public TipoEnvase getTipoEnvase() {
        return tipoEnvase;
    }

    public boolean isImportado() {
        return importado;
    }

    @Override
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }


    public void setPorcentajeDescuento(double porcentaje) {
        if(porcentaje>20){
            this.porcentajeDescuento=20;
        }else {
            this.porcentajeDescuento=porcentaje;
        }
    }

    @Override
    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }


    @Override
    public double obtenerPrecioConDescuento(String ident) {
        double aux=getPrecio()-(getPrecio()*getPorcentajeDescuento()/100);
        if(aux<getCosto()){
            System.out.println("El descuento registrado para el producto "+ident+" no pudo ser aplicado");
            setPorcentajeDescuento(0);
            return getPrecio();

        }else return aux;
    }


    @Override
    public int getCalorias() {
        return calorias;
    }

    //PROPIAS
    private void aplicarImpuesto(String des){
        double impuesto=this.precio*0.10;
        this.precio+=impuesto;
        System.out.println("Se le aplico a "+des+" un impuesto por ser un producto importado");
    }
}
