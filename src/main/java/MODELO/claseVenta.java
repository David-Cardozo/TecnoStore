package MODELO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class claseVenta {

    private int id;
    private claseClientes cliente;
    private LocalDate fecha;
    private List<itemVenta> items;
    private claseEmpleado empleado;
    private double total;

    private static final double IVA = 0.19;

    public claseVenta(int id, claseClientes cliente, claseEmpleado empleado) {
        this.id = id;
        this.cliente = cliente;
        this.empleado = empleado;
        this.fecha = LocalDate.now();
        this.items = new ArrayList<>();
    }

    public claseVenta() {
        this.items = new ArrayList<>();
    }

    public void agregarItem(itemVenta item) {
        items.add(item);
        recalcularTotal();
    }

    private void recalcularTotal() {
        double subtotal = items.stream()
                .mapToDouble(itemVenta::getSubtotal)
                .sum();

        total = subtotal + (subtotal * IVA);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public claseClientes getCliente() {
        return cliente;
    }

    public void setCliente(claseClientes cliente) {
        this.cliente = cliente;
    }

    public List<itemVenta> getItems() {
        return items;
    }

    public List<itemVenta> getListaItems() {
        return items;
    }

    public claseEmpleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(claseEmpleado empleado) {
        this.empleado = empleado;
    }

}
