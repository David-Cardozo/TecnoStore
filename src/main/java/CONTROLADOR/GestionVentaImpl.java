package CONTROLADOR;

import MODELO.*;
import java.sql.*;

public class GestionVentaImpl implements GestionVenta {

    Conexion c = new Conexion();

    @Override
    public double calcularTotalConIVA(double subtotal) {
        return subtotal * 1.19;
    }

    @Override
    public void registrarVenta(claseVenta venta) {

        String sqlVenta = "INSERT INTO ventas (id_cliente, fecha, total) VALUES (?, ?, 0)";
        String sqlDetalle = "INSERT INTO detalle_ventas (id_venta, id_celular, cantidad) VALUES (?, ?, ?)";

        try (Connection con = c.conectar()) {

            con.setAutoCommit(false);

            PreparedStatement psVenta = con.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS);
            psVenta.setInt(1, venta.getCliente().getId());
            psVenta.setDate(2, Date.valueOf(venta.getFecha()));
            psVenta.executeUpdate();

            ResultSet rs = psVenta.getGeneratedKeys();
            rs.next();
            int ventaId = rs.getInt(1);

            for (itemVenta item : venta.getItems()) {

                PreparedStatement psDetalle = con.prepareStatement(sqlDetalle);
                psDetalle.setInt(1, ventaId);
                psDetalle.setInt(2, item.getCelular().getId());
                psDetalle.setInt(3, item.getCantidad());
                psDetalle.executeUpdate();
            }

            con.commit();
            System.out.println("Venta registrada correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
