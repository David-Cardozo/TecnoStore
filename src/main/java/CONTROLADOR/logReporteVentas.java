package CONTROLADOR;

import MODELO.claseVenta;
import MODELO.itemVenta;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class logReporteVentas {

    public static void generarReporte(List<claseVenta> ventas) {

        try {

            String rutaDocumentos = System.getProperty("user.home") + "/Documents";

            Path carpeta = Paths.get(rutaDocumentos, "TecnoStoreReportes");

            Path archivo = carpeta.resolve("reporte_ventas.txt");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo.toFile()))) {

                writer.write("===== REPORTE GENERAL DE VENTAS =====\n\n");

                for (claseVenta venta : ventas) {

                    writer.write("Venta ID: " + venta.getId() + "\n");
                    writer.write("Fecha: " + venta.getFecha() + "\n");
                    writer.write("Cliente: " + venta.getCliente().getNombre() + "\n");

                    writer.write("Detalle:\n");

                    for (itemVenta item : venta.getListaItems()) {

                        writer.write("   - Celular: " + item.getCelular().getModelo()
                                + " | Cantidad: " + item.getCantidad()
                                + " | Subtotal: $" + item.getSubtotal() + "\n");
                    }

                    writer.write("Total Venta (con IVA): $" + venta.getTotal() + "\n");
                    writer.write("--------------------------------------\n\n");
                }

                writer.write("===== FIN DEL REPORTE =====");

                System.out.println("Reporte generado en: " + archivo.toAbsolutePath());
            }

        } catch (IOException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
        }
    }
}
