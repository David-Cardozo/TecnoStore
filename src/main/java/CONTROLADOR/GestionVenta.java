package CONTROLADOR;

import MODELO.claseVenta;

public interface GestionVenta {

    void registrarVenta(claseVenta venta);

    double calcularTotalConIVA(double subtotal);
}
