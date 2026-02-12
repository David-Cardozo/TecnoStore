package CONTROLADOR;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import CONTROLADOR.Conexion;

public class Validaciones {

    public static boolean validarPrecio(double precio) {
        return precio > 0;
    }

    public static boolean validarStock(int stock) {
        return stock >= 0;
    }

    public static boolean validarCorreo(String correo) {
        return correo.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean validarIdentificacionUnica(String identificacion) {

        String sql = "SELECT COUNT(*) FROM clientes WHERE identificacion = ?";

        Conexion c = new Conexion();
        try (Connection con = c.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, identificacion);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) == 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
