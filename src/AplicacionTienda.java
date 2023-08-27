import models.*;
import service.*;

import java.util.List;

public class AplicacionTienda {
    public static void main(String[] args) {
        //ejemplos de bebidas

        Bebida cocacola=new Bebida("Cocacola",8,800,550,false,0,false);
        Bebida fanta=new Bebida("fanta",7,750,500,false,0,false);
        Bebida vodka=new Bebida("vodka importado",3,1000,900,true,17,true);
        Bebida soda=new Bebida("soda",20,350,200,false,0,false);
        Bebida monster=new Bebida("mosnter",10,900,750,false,0,false);
        //ejemplo de envasados
        Envasado pollo=new Envasado("bandeja de pollo cortado",10,1500,1000, TipoEnvase.PLASTICO,false);
        Envasado miel=new Envasado("miel",4,800,500,TipoEnvase.VIDRIO,false);
        Envasado carne=new Envasado("carne cortada ",10,2000,1500,TipoEnvase.PLASTICO,false);
        Envasado atun=new Envasado("atun en trozo",15,900,750,TipoEnvase.LATA,false);
        Envasado nutela=new Envasado("nutela",4,1500,900,TipoEnvase.PLASTICO,true);
        //ejemplos Limpieza
        Limpieza detergente=new Limpieza("detergente ",20,900,600, TipoAplicacion.COCINA);
        Limpieza remeras=new Limpieza("remera multicolor",7,3000,1500,TipoAplicacion.ROPA);
        Limpieza cera=new Limpieza("cera piso madera",150,1300,900,TipoAplicacion.PISOS);
        Limpieza trapo=new Limpieza("trapo rejilla",20,500,350,TipoAplicacion.MULTIUSO);
        Limpieza aspiradora=new Limpieza("aspiradora electrica inteligente",2,15000,12000,TipoAplicacion.MULTIUSO);

        Tienda tiendaAry=new Tienda("Tienda Ary",200,150000);
        //cargar varios productos a la tienda
        tiendaAry.agregarProducto(cocacola);
        tiendaAry.agregarProducto((fanta));
        tiendaAry.agregarProducto(vodka);
        tiendaAry.agregarProducto(pollo);
        tiendaAry.agregarProducto(miel);
        tiendaAry.agregarProducto(carne);
        tiendaAry.agregarProducto(atun);
        tiendaAry.agregarProducto(nutela);
        tiendaAry.agregarProducto(detergente);
        tiendaAry.agregarProducto(remeras);
        tiendaAry.agregarProducto(trapo);
        tiendaAry.agregarProducto(aspiradora);
        //mostrar el stock actual de la tienda
        tiendaAry.mostrarStock();

        //ejemplo de una compra
        //impimo el saldo actual de caja para mostar q dismunuye con la compra y el stock
        System.out.println("----------------------TEST COMPRA------------------------");
        System.out.println("El saldo de la caja es :"+tiendaAry.getSaldoCaja());
        System.out.println("El saldo de stock es:"+tiendaAry.getStock());
        tiendaAry.comprarProducto(soda);
        System.out.println("El saldo de la caja es :"+tiendaAry.getSaldoCaja());
        System.out.println("El saldo de stock es:"+tiendaAry.getStock());
        tiendaAry.mostrarStock();
        System.out.println("----------------------TEST COMPRA/SIN SALDO CAJA------------------------");
        tiendaAry.setSaldoCaja(500);
        System.out.println("El saldo de la caja es :"+tiendaAry.getSaldoCaja());
        tiendaAry.comprarProducto(monster);
        System.out.println("El saldo de la caja es :"+tiendaAry.getSaldoCaja());
        System.out.println("----------------------TEST COMPRA/SIN STOCK------------------------");
        tiendaAry.comprarProducto(cera);
        System.out.println("----------------------TEST VENTA------------------------");
        Bebida VTAcocacola=new Bebida("Cocacola",1,800,550,false,0,false);
        VTAcocacola.setIdentificador(cocacola.getIdentificador());
        tiendaAry.vtaProducto(VTAcocacola);
        System.out.println("El saldo de la caja es :"+tiendaAry.getSaldoCaja());
        tiendaAry.mostrarStock();
        System.out.println("----------------------TEST VENTA 3 PRODUCTOS------------------------");
        Limpieza VTAdetergente=new Limpieza("detergente ",2,900,600, TipoAplicacion.COCINA);
        Envasado VTAnutela=new Envasado("nutela",1,1500,900,TipoEnvase.PLASTICO,false);
        VTAnutela.setIdentificador(nutela.getIdentificador());
        VTAdetergente.setIdentificador(detergente.getIdentificador());
        tiendaAry.vtaProducto(VTAcocacola,VTAdetergente,VTAnutela);
        System.out.println("El saldo de la caja es :"+tiendaAry.getSaldoCaja());
        tiendaAry.mostrarStock();
        System.out.println("----------------------TEST VENTA /Vender menos de 10 productos------------------------");
        VTAcocacola.setCantidad(100);
        tiendaAry.vtaProducto(VTAcocacola);
        System.out.println("----------------------TEST VENTA /Superar la cantidad actual------------------------");
        VTAcocacola.setCantidad(8);
        tiendaAry.vtaProducto(VTAcocacola);
        tiendaAry.mostrarStock();
        System.out.println("----------------------DESCUENTO------------------------");
        fanta.setPorcentajeDescuento(3);
        System.out.println("La "+fanta.getDescripcion()+" tiene un descuento del "+fanta.getPorcentajeDescuento()+" % quedo con un precio de "+fanta.obtenerPrecioConDescuento(fanta.getIdentificador()));
        System.out.println("intento aplicar un descuento del mas del 15% a una bebida");
        fanta.setPorcentajeDescuento(25);
        System.out.println("La "+fanta.getDescripcion()+" tiene un descuento del "+fanta.getPorcentajeDescuento() +" % quedo con un precio de "+fanta.obtenerPrecioConDescuento(fanta.getIdentificador()));
        System.out.println("intento aplicar un descuento del mas del 20% a una Envasado");
        pollo.setPorcentajeDescuento(25);
        System.out.println("El "+pollo.getDescripcion()+" tiene un descuento del "+pollo.getPorcentajeDescuento()+"% quedo con precio de "+pollo.obtenerPrecioConDescuento(pollo.getIdentificador()));
        System.out.println("intento aplicar un descuento del mas del 25% a una articulo Limpieza");
        aspiradora.setPorcentajeDescuento(30);
        aspiradora.obtenerPrecioConDescuento(aspiradora.getDescripcion());
        System.out.println("La "+aspiradora.getDescripcion()+" tiene un descuento del "+aspiradora.getPorcentajeDescuento()+"% quedo con precio de "+aspiradora.obtenerPrecioConDescuento(pollo.getDescripcion()));
        System.out.println("----------------------REQUERIMIENTO OPCIONAL------------------------");
        tiendaAry.obtenerComestiblesConMenorDescuento(7);
        tiendaAry.listarProductosConUtilidadesInferiores(17);



    }


}

