package inter;

public interface Descuento {
    void setPorcentajeDescuento(double porcentaje);
    double getPorcentajeDescuento();

    double obtenerPrecioConDescuento(String identi);
}
