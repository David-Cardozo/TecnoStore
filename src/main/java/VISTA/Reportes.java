package VISTA;

import CONTROLADOR.*;
import MODELO.*;
import java.util.List;

public class Reportes {

    private GestionCelulares gestionCelulares = new GestionCelularesImpl();

    public void mostrar() {

        System.out.println("""
            --- Reportes ---
             1. Celulares con stock bajo
            
             2. Top 3 Mas Vendidos
            
             3. Ventas Totales Por Mes
            """);

        List<claseCelulares> lista = gestionCelulares.listarCelulares();

        for (claseCelulares c : lista) {
            System.out.println(c);
        }
    }
}
