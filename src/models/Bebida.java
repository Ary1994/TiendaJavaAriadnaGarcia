package models;

import inter.Comestible;
import inter.Descuento;

public class Bebida extends Producto implements Comestible, Descuento {

    boolean alcoholica;
    int graduacionAlcoholica;
    boolean importado;
    String fechaVencimiento;
    int calorias;


    public Bebida(String descripcion, int cantidad, double precio, double costo,  boolean alcoholica, int graduacionAlcoholica, boolean importado) {
        super(descripcion, cantidad, precio, costo);
        tipoProducto=TipoProducto.BEBIDA;
        this.alcoholica = alcoholica;
        this.graduacionAlcoholica = graduacionAlcoholica;
        this.importado = importado;
        identificador="AC"+getId();
        if(importado)aplicarImpuesto(descripcion);


    }


    //GETTERS
    public boolean isAlcoholica() {
        return alcoholica;
    }
    public int getGraduacionAlcoholica() {
        return graduacionAlcoholica;
    }
    public boolean isImportado() {
        return importado;
    }
    @Override
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }
    @Override
    public int getCalorias() {
        return calorias;
    }
    @Override
    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }
    //SETTERS
    public void setAlcoholica(boolean alcoholica) {
        this.alcoholica = alcoholica;
    }
    public void setGraduacionAlcoholica(int graduacionAlcoholica) {
        this.graduacionAlcoholica = graduacionAlcoholica;
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
        this.calorias = calorias;
    }
    @Override
    public void setPorcentajeDescuento(double porcentaje) {
        if(porcentaje>15){
            this.porcentajeDescuento=15;
        }else {
            this.porcentajeDescuento=porcentaje;
        }
    }
    //PROPIAS
    @Override
    public double obtenerPrecioConDescuento(String ident) {
        double aux=getPrecio()-(getPrecio()*getPorcentajeDescuento()/100);

        if(aux<getCosto()){
            System.out.println("El descuento registrado para el producto "+ident+" no pudo ser aplicado");
            setPorcentajeDescuento(0);
            return getPrecio();


        }else return aux;

    }
    public double porcentajeGanancias(){
        double ganancia= this.precio-this.costo;
        double porcGan=((ganancia/this.costo)/100);
        if(porcGan>20){
            return 20;
        }else return porcGan;
    }
    private void aplicarImpuesto(String des){
        double impuesto=this.precio*0.10;
        this.precio+=impuesto;
        System.out.println("Se le aplico a "+des+" un impuesto por ser un producto importado");
    }
}
