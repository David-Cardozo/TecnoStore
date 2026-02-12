package VISTA;

import java.util.Scanner;

public class Menu {

    private Scanner sc = new Scanner(System.in);

    public void mostrar() {

        int opcion;

        do {
            System.out.println("""
                --------------------------------------------
                Bienvenido a Tecno Store
                --------------------------------------------
                1. Gestionar Clientes
                2. Gestionar Celulares
                3. Gestionar Ventas
                4. Reportes
                5. Salir
                --------------------------------------------
                """);

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> new MenuClientes().mostrar();
                case 2 -> new MenuCelulares().mostrar();
                case 3 -> new MenuVentas().mostrar();
                case 4 -> new Reportes().mostrar();
                case 5 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 5);
    }
}
