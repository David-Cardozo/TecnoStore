package VISTA;

import CONTROLADOR.*;
import MODELO.*;
import java.util.List;
import java.util.Scanner;

public class MenuClientes {

    private Scanner sc = new Scanner(System.in);
    private GestionClientes gestion = new GestionClientesImpl();

    public void mostrar() {

        int op;

        do {
            System.out.println("""
                --- Gestión de Clientes ---
                1. Registrar Cliente
                2. Listar Clientes
                3. Volver
                """);

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> {

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Identificación: ");
                    String identificacion = sc.nextLine();

                    System.out.print("Correo: ");
                    String correo = sc.nextLine();

                    System.out.print("Teléfono: ");
                    String telefono = sc.nextLine();

                    claseClientes cliente = new claseClientes(0, nombre, identificacion, correo, telefono);
                    gestion.registrarCliente(cliente);

                }

                case 2 -> {
                    List<claseClientes> lista = gestion.listarClientes();
                    lista.forEach(System.out::println);
                }

                case 3 ->
                    System.out.println("Volviendo...");

                default ->
                    System.out.println("Opción inválida.");
            }

        } while (op != 3);
    }
}
