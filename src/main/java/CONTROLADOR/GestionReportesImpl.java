package CONTROLADOR;

import MODELO.*;
import java.sql.*;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class GestionReportesImpl implements GestionReportes {

    Conexion c = new Conexion();

    // 1️⃣ CELULARES CON STOCK BAJO
    @Override
    public void celularesStockBajo() {

        ArrayList<claseCelulares> lista = new ArrayList<>();

        try (Connection con = c.conectar()) {

            String sql = "SELECT c.id, m.id as id_marca, m.nombre, c.modelo, c.sistema_operativo, c.gama, c.precio, c.stock "
                       + "FROM celulares c INNER JOIN marca m ON c.id_marca = m.id";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                claseMarca marca = new claseMarca(
                        rs.getInt("id_marca"),
                        rs.getString("nombre")
                );

                claseCelulares cel = new claseCelulares(
                        rs.getInt("id"),
                        marca,
                        rs.getString("modelo"),
                        rs.getString("sistema_operativo"),
                        tipoGama.valueOf(rs.getString("gama")),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );

                lista.add(cel);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("=== CELULARES CON STOCK BAJO (<5) ===");

        lista.stream()
                .filter(cel -> cel.getStock() < 5)
                .forEach(System.out::println);
    }

    // 2️⃣ TOP 3 CELULARES MÁS VENDIDOS
    @Override
    public void top3MasVendidos() {

        Map<String, Integer> ventasPorCelular = new HashMap<>();

        try (Connection con = c.conectar()) {

            String sql = "SELECT c.modelo, SUM(d.cantidad) as total_vendido "
                       + "FROM detalle_ventas d "
                       + "INNER JOIN celulares c ON d.id_celular = c.id "
                       + "GROUP BY c.modelo";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                ventasPorCelular.put(
                        rs.getString("modelo"),
                        rs.getInt("total_vendido")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("=== TOP 3 MAS VENDIDOS ===");

        ventasPorCelular.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .forEach(entry ->
                        System.out.println(entry.getKey() + " | Vendidos: " + entry.getValue())
                );
    }

    // 3️⃣ VENTAS TOTALES POR MES
    @Override
    public void ventasTotalesPorMes() {

        ArrayList<claseVenta> ventas = new ArrayList<>();

        try (Connection con = c.conectar()) {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ventas");

            while (rs.next()) {

                claseVenta v = new claseVenta();
                v.setId(rs.getInt("id"));
                v.setFecha(rs.getDate("fecha").toLocalDate());
                v.setTotal(rs.getDouble("total"));

                ventas.add(v);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("=== VENTAS TOTALES POR MES ===");

        Map<Month, Double> ventasPorMes = ventas.stream()
                .collect(Collectors.groupingBy(
                        v -> v.getFecha().getMonth(),
                        Collectors.summingDouble(claseVenta::getTotal)
                ));

        ventasPorMes.forEach((mes, total) ->
                System.out.println(mes + " -> $" + total)
        );
    }
}
