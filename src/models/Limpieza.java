package models;
import inter.Descuento;
public class Limpieza extends Producto implements Descuento {
    TipoAplicacion tipoAplicacion;
    double porcentajeDescuento;

    public Limpieza(String descripcion, int cantidad, double precio, double costo, TipoAplicacion tipoAplicacion) {
        super(descripcion, cantidad, precio, costo);
        tipoProducto=TipoProducto.LIMPIEZA;
        this.tipoAplicacion=tipoAplicacion;
        identificador="AZ"+getId();
    }
    //GETTERS
    public TipoAplicacion getTipoAplicacion() {
        return tipoAplicacion;
    }
    //SETTERS
    public void setTipoAplicacion(TipoAplicacion tipoAplicacion) {
        this.tipoAplicacion = tipoAplicacion;
    }

    @Override
    public void setPorcentajeDescuento(double porcentaje) {
        if(porcentaje>25){
            porcentajeDescuento=25;
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
    public double porcentajeGanancias(){
        double ganancia= this.precio-this.costo;
        double porcGan=((ganancia/this.costo)*100);
        if(porcGan<25){
            if(porcGan<10){
                if((this.tipoAplicacion == TipoAplicacion.ROPA) && (this.tipoAplicacion == TipoAplicacion.MULTIUSO)) return porcGan;
                else return 10;

            }else return porcGan;

        }else return 25;



    }


}
