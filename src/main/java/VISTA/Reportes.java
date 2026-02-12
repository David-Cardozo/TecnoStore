package VISTA;

import CONTROLADOR.*;
import MODELO.*;
import java.util.List;
import java.util.Scanner;

public class Reportes {

    private GestionReportes r = new GestionReportesImpl();

    public void mostrar() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        System.out.println("""
            --- Reportes ---
             1. Celulares con stock bajo
            
             2. Top 3 Mas Vendidos
            
             3. Ventas Totales Por Mes
            """);

        System.out.print("Seleccione una opción: ");
        opcion = sc.nextInt();

        switch (opcion) {

            case 1:
                System.out.println("Mostrando celulares con stock bajo...");
                r.celularesStockBajo();
                break;

            case 2:
                System.out.println("Mostrando Top 3 más vendidos...");
                r.top3MasVendidos();
                break;

            case 3:
                System.out.println("Mostrando ventas totales por mes...");
                r.ventasTotalesPorMes();
                break;

            case 4:
                System.out.println("Saliendo del módulo Reportes...");
                break;

            default:
                System.out.println("Opción inválida");
        }
    }
}
