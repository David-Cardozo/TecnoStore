package VISTA;

import CONTROLADOR.*;
import MODELO.*;
import java.util.List;
import java.util.Scanner;

public class MenuCelulares {

    private Scanner sc = new Scanner(System.in);
    private GestionCelulares gestion = new GestionCelularesImpl();

    public void mostrar() {

        int op;

        do {
            System.out.println("""
                --- Gestión de Celulares ---
                1. Registrar
                2. Actualizar
                3. Eliminar
                4. Listar
                5. Volver
                """);

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 ->
                    registrar();
                case 2 ->
                    actualizar();
                case 3 ->
                    eliminar();
                case 4 ->
                    listar();
            }

        } while (op != 5);
    }

    private void registrar() {

        System.out.print("Marca: ");
        String marcaStr = sc.nextLine();
        claseMarca marca = new claseMarca(0, marcaStr);

        System.out.print("Modelo: ");
        String modelo = sc.nextLine();

        System.out.print("Precio: ");
        double precio = sc.nextDouble();

        System.out.print("Stock: ");
        int stock = sc.nextInt();
        sc.nextLine();

        System.out.print("Sistema Operativo: ");
        String OS = sc.nextLine();

        System.out.print("Gama (Alta/Media/Baja): ");
        String gamaStr = sc.nextLine();
        tipoGama gama = tipoGama.valueOf(gamaStr.toUpperCase());

        claseCelulares celular = new claseCelulares(0, marca, modelo, OS, gama, precio, stock);

        gestion.registrarCelular(celular);
    }

    private void actualizar() {

        System.out.print("ID del celular a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

        claseCelulares existente = gestion.buscarPorId(id);

        if (existente == null) {
            System.out.println("Celular no encontrado.");
            return;
        }

        System.out.print("Nueva Marca: ");
        String marcaStr = sc.nextLine();
        claseMarca marca = new claseMarca(0, marcaStr);

        System.out.print("Nuevo Modelo: ");
        String modelo = sc.nextLine();

        System.out.print("Nuevo Precio: ");
        double precio = sc.nextDouble();

        System.out.print("Nuevo Stock: ");
        int stock = sc.nextInt();
        sc.nextLine();

        System.out.print("Nuevo Sistema Operativo: ");
        String OS = sc.nextLine();

        System.out.print("Nueva Gama: ");
        String gamaStr = sc.nextLine();
        tipoGama gama = tipoGama.valueOf(gamaStr.toUpperCase());

        claseCelulares celular = new claseCelulares(id, marca, modelo, OS, gama, precio, stock);

        gestion.actualizarCelular(celular);
    }

    private void eliminar() {

        System.out.print("ID del celular a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();

        gestion.eliminarCelular(id);
    }

    private void listar() {

        List<claseCelulares> lista = gestion.listarCelulares();

        for (claseCelulares c : lista) {
            System.out.println(c);
        }
    }
}
