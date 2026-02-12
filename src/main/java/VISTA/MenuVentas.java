package VISTA;

import CONTROLADOR.*;
import MODELO.*;
import java.util.*;

public class MenuVentas {

    private Scanner sc = new Scanner(System.in);
    private GestionVenta gestionVenta = new GestionVentaImpl();
    private GestionClientes gestionClientes = new GestionClientesImpl();
    private GestionCelulares gestionCelulares = new GestionCelularesImpl();

    public void mostrar() {

        int op;

        do {
            System.out.println("""
                --- Gestión de Ventas ---
                1. Registrar Venta
                2. Volver
                """);

            op = sc.nextInt();
            sc.nextLine();

            if (op == 1) {
                registrarVenta();
            }

        } while (op != 2);
    }

    private void registrarVenta() {

        System.out.print("Ingrese identificación del cliente: ");
        String identificacion = sc.nextLine();

        claseClientes cliente = gestionClientes.buscarPorIdentificacion(identificacion);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        List<itemVenta> items = new ArrayList<>();
        String resp = "";

        do {
            System.out.print("ID del celular: ");
            int idCelular = sc.nextInt();

            claseCelulares celular = gestionCelulares.buscarPorId(idCelular);

            if (celular == null) {
                System.out.println("Celular no encontrado.");
                sc.nextLine();
                continue;
            }

            System.out.print("Cantidad: ");
            int cantidad = sc.nextInt();
            sc.nextLine();

            if (cantidad > celular.getStock()) {
                System.out.println("Stock insuficiente.");
                continue;
            }

            items.add(new itemVenta(celular, cantidad));

            System.out.print("¿Agregar otro celular? (s/n): ");
            resp = sc.nextLine().trim();

        } while (resp.equalsIgnoreCase("s"));

        claseVenta venta = new claseVenta(0, cliente, null);

        for (itemVenta it : items) {
            venta.agregarItem(it);
        }

        gestionVenta.registrarVenta(venta);
    }

}
