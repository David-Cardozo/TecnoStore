package MODELO;

public class itemVenta {

    private claseCelulares celular;
    private int cantidad;
    private double subtotal;

    public itemVenta(claseCelulares celular, int cantidad) {

        if (cantidad <= 0) {
            throw new IllegalArgumentException("Cantidad inválida");
        }

        if (cantidad > celular.getStock()) {
            throw new IllegalArgumentException("Stock insuficiente");
        }

        this.celular = celular;
        this.cantidad = cantidad;
        this.subtotal = celular.getPrecio() * cantidad;
    }

    public claseCelulares getCelular() { return celular; }
    public int getCantidad() { return cantidad; }
    public double getSubtotal() { return subtotal; }
}
