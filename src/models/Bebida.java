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
    }

    public boolean isAlcoholica() {
        return alcoholica;
    }

    public void setAlcoholica(boolean alcoholica) {
        this.alcoholica = alcoholica;
    }

    public int getGraduacionAlcoholica() {
        return graduacionAlcoholica;
    }

    public void setGraduacionAlcoholica(int graduacionAlcoholica) {
        this.graduacionAlcoholica = graduacionAlcoholica;
    }

    public boolean isImportado() {
        return importado;
    }

    public void setImportado(boolean importado) {
        this.importado = importado;
    }


    @Override
    public void setFechaVencimiento(String fecha) {
        this.fechaVencimiento = fecha;
    }

    @Override
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    @Override
    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    @Override
    public int getCalorias() {
        return calorias;
    }

    @Override
    public void setPorcentajeDescuento(double porcentaje) {
        if(porcentaje>15){
            this.porcentajeDescuento=15;
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
        double aux=(getPrecio()*(1-getPorcentajeDescuento()));
        if(aux<getCosto()){
            System.out.println("El descuento registrado para el producto"+ident+"no pudo ser aplicado");
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
}
