import models.*;
import service.Tienda;

public class Main {
    public static void main(String[] args) {



        Envasado envasado=new Envasado("pollo",5,1000,750,TipoEnvase.PLASTICO,true);

        Tienda tienda=new Tienda("tienda ary",100,15000);
        Bebida coca =new Bebida("cocacola gasificada",20,800,500,true,0,false);

        tienda.comprarProducto(coca);
        Bebida ventacoca=new Bebida("cocacola gasificada",25,800,500,true,0,false);
        ventacoca.setIdentificador(coca.getIdentificador());
        tienda.vtaProducto(ventacoca);
        tienda.mostrarStock();


    }


}