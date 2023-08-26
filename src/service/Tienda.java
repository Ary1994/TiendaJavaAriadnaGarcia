package service;
import models.*;

import java.util.*;
public class Tienda {
     String nombre;
     int maxStock;
     double saldoCaja;
     int stock;
     HashMap<String, Bebida>stockBebidas=new HashMap<String,Bebida>();
     HashMap<String, Envasado>stockEnvasado=new HashMap<String,Envasado>();
     HashMap<String, Limpieza>stockLimpieza= new HashMap<String, Limpieza>();
    //CONSTRUCTORES
    public Tienda() {
    }
    public Tienda(String nombre, int maxStock, double saldoCaja) {
          this.nombre = nombre;
          this.maxStock = maxStock;
          this.saldoCaja = saldoCaja;
     }
    //GETTERS
    public String getNombre() {
        return nombre;
    }
    public int getMaxStock() {
        return maxStock;
    }
    public double getSaldoCaja() {
        return saldoCaja;
    }

    //SETTERS
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setMaxStock(int maxStock) {
        this.maxStock = maxStock;
    }
    public void setSaldoCaja(double saldoCaja) {
        this.saldoCaja = saldoCaja;
    }
    //PROPIAS PARA LA FUNCION COMPRA
    public void comprarProducto(Producto prod){
          double saldo=prod.getCosto()* prod.getCantidad();
          if(disponibleSaldoCaja(saldo)&&diponibleStock(prod.getCantidad())){

                    agregarProducto(prod);
                    saldoCaja-=saldo;
               System.out.print("se agrego el producto ");
                mostrarProducto(prod);
          }else {
                    System.out.println("no se puede comprar el producto");
          }


     }
     private boolean disponibleSaldoCaja(double saldo){
          return (saldo <= saldoCaja);
     }
     private boolean diponibleStock(int cant){
          return((this.stock+cant)<=maxStock);
     }

     public void agregarProducto(Producto env){


          if(env.getTipoProducto()== TipoProducto.BEBIDA){
               stockBebidas.put(env.getIdentificador(),(Bebida) env);
          }
          else if(env.getTipoProducto()==TipoProducto.ENVASADO){
               stockEnvasado.put(env.getIdentificador(), (Envasado) env);

          }else{
               stockLimpieza.put(env.getIdentificador(),(Limpieza) env);

          }
          stock+= env.getCantidad();
     }
    //PROPIAS PARA LA FUNCION VENTA
     private void venderProducto(Producto prod){
        if(stockBebidas.containsKey(prod.getIdentificador())){
            ventaIntermedia(prod,stockBebidas);
        }
        else if(stockEnvasado.containsKey(prod.getIdentificador())) {
            ventaIntermedia(prod,stockEnvasado);

        } else if (stockLimpieza.containsKey(prod.getIdentificador())) {
            ventaIntermedia(prod,stockLimpieza);
        }else {
            System.out.println("No disponemos con ese producto en la tienda ");
        }

     }

    private void ventaIntermedia(Producto prod,HashMap maps) {
        if(prod.isDisponible()){
            if(disponibilidadStock(prod.getIdentificador(),prod.getCantidad())){
                aumentarCaja(prod);
                sacarProducto(prod);
                mostrarProducto(prod);
            }else {
                prod.setCantidad(obtenerCantidad(prod.getIdentificador()));
                aumentarCaja(prod);
                maps.remove(prod.getIdentificador());
                mostrarProducto(prod);
                System.out.println("Hay productos con stock disponible menor al solicitado");
            }
        }else{
            mensajeNoseEncuntraDisponibloe(prod);
        }
    }

    private int obtenerCantidad(String ident){
        int cant=0;
        if(stockBebidas.containsKey(ident)) {
            Bebida aux=stockBebidas.get(ident);
            cant=aux.getCantidad();
        } else if (stockEnvasado.containsKey(ident)) {
            Envasado aux=stockEnvasado.get(ident);
            cant=aux.getCantidad();

        }else{
            Limpieza aux=stockLimpieza.get(ident);
            cant=aux.getCantidad();
        }

         return cant;
     }

     public void vtaProducto(Producto prod){
        if(prod.getCantidad()<=10){
            venderProducto(prod);
            System.out.println("TOTAL VENTA "+(prod.getPrecio()*prod.getCantidad()));
        }else System.out.println("No se puede vender mas de 10 productos del mismo tipo");


     }
     public void vtaProducto(Producto prod,Producto prod2){
        if(prod.getCantidad()<=10&&prod2.getCantidad()<=10){
            venderProducto(prod);
            venderProducto(prod2);
            double total=prod.getPrecio()*prod.getCantidad()+prod2.getPrecio()*prod2.getCantidad();
            System.out.println(" TOTAL VENTA : "+total);
        }else System.out.println("No se puede vender mas de 10 productos del mismo tipo");


    }

     public void vtaProducto(Producto prod,Producto prod2,Producto prod3){
         if(prod.getCantidad()<=10&&prod2.getCantidad()<=10&&prod3.getCantidad()<=10){
             venderProducto(prod);
             venderProducto(prod2);
             venderProducto(prod3);
             double total=prod.getPrecio()*prod.getCantidad()+prod2.getPrecio()*prod2.getCantidad()+prod3.getCantidad()*prod3.getPrecio();
             System.out.println(" TOTAL VENTA : "+total);
         }else System.out.println("No se puede vender mas de 10 productos del mismo tipo");

    }
     private void aumentarCaja(Producto prod){
        saldoCaja+= prod.getPrecio()*prod.getCantidad();
     }
     private boolean disponibilidadStock(String identificador,int cant){
        if(stockBebidas.containsKey(identificador)){
            Bebida aux=stockBebidas.get(identificador);
            return aux.getCantidad() >= cant;
        }
        else if(stockLimpieza.containsKey(identificador)){
            Limpieza aux=stockLimpieza.get(identificador);
            return aux.getCantidad() >= cant;
        }
        else{
            Envasado aux =stockEnvasado.get(identificador);
            return aux.getCantidad() >= cant;

        }

     }
     private void sacarProducto(Producto prod){
        if(prod.getTipoProducto()==TipoProducto.BEBIDA){
            Bebida aux =stockBebidas.get(prod.getIdentificador());
            aux.setCantidad(aux.getCantidad()-prod.getCantidad());
            stockBebidas.remove(prod.getIdentificador());
            stockBebidas.put(aux.getIdentificador(),aux);
        } else if (prod.getTipoProducto()==TipoProducto.ENVASADO) {
            Envasado aux=stockEnvasado.get(prod.getIdentificador());
            aux.setCantidad(aux.getCantidad()-prod.getCantidad());
            stockEnvasado.remove(prod.getIdentificador());
            stockEnvasado.put(aux.getIdentificador(),aux);
        }else{
            Limpieza aux=stockLimpieza.get(prod.getIdentificador());
            aux.setCantidad(aux.getCantidad()-prod.getCantidad());
            stockLimpieza.remove(prod.getIdentificador());
            stockLimpieza.put(aux.getIdentificador(),aux);
        }


     }

    //MOSTRAR COSAS
    private void mensajeNoseEncuntraDisponibloe(Producto prod){
         System.out.println("El producto "+prod.getIdentificador()+" "+prod.getDescripcion()+" no se encuentra disponible" );

     }
    //PROPIAS GENERALES
    public void mostrarProducto(Producto prod){
        System.out.println(prod.getIdentificador() +" "+prod.getDescripcion()+" "+prod.getCantidad()+" x "+prod.getPrecio());
    }

    public void mostrarStock(){
        System.out.println("Productos de bebidas en stock");
        mostrarBebidas();
        System.out.println("Productos de envasado en stock");
        mostrarEnvasado();
        System.out.println("Productos de limpieza en stock");
        mostrarLimpieza();
    }
    private void mostrarBebidas() {
    for(Bebida value:stockBebidas.values()){
        mostrarProducto(value);
    }
    }
    private  void mostrarEnvasado(){
        for(Envasado value:stockEnvasado.values()){
            mostrarProducto(value);
        }
    }
    private void mostrarLimpieza(){
        for(Limpieza value:stockLimpieza.values()){
            mostrarProducto(value);
        }
    }
}
