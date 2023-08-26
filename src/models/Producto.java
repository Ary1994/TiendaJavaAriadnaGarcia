package models;

public class Producto {
    //Atributos
    static int contador=111;
    int id;
    String identificador;
    String descripcion;
    int cantidad;
    double precio;
    double costo;
    boolean disponible=true;
    TipoProducto tipoProducto;

    double porcentajeDescuento;
    //CONTRUCTORES
    public Producto() {

    }

    public Producto( String descripcion, int cantidad, double precio, double costo ) {
        this.id=contador;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.costo = costo;
        incrementarContador();

    }

    //GETTERS
    public String getIdentificador() {
        return identificador;
    }
    public static int getContador() {
        return contador;
    }
    public int getId() {
        return id;
    }
    public int getCantidad() {
        return cantidad;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public double getPrecio() {
        return precio;
    }
    public double getCosto() {
        return costo;
    }
    public boolean isDisponible() {
        return disponible;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    //SETTERS

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void setCosto(double costo) {
        this.costo = costo;
    }


    //FUNCIONES PROPIAS
    private static void incrementarContador() {
        contador++;
    }

    public double porcentajeGanancias(){
        double ganancia= this.precio-this.costo;
        double porcGan=((ganancia/this.costo)/100);
        return porcGan;
    }


}
