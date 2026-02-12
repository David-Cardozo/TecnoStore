package CONTROLADOR;

import MODELO.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionCelularesImpl implements GestionCelulares {

    Conexion c = new Conexion();

    @Override
    public void registrarCelular(claseCelulares celular) {

        if (!Validaciones.validarPrecio(celular.getPrecio())) {
            System.out.println("Precio inválido.");
            return;
        }

        if (!Validaciones.validarStock(celular.getStock())) {
            System.out.println("Stock inválido.");
            return;
        }

        String sql = "INSERT INTO celulares (marca, modelo, precio, stock, sistema_operativo, gama) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = c.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, celular.getMarca().toString());
            ps.setString(2, celular.getModelo());
            ps.setDouble(3, celular.getPrecio());
            ps.setInt(4, celular.getStock());
            ps.setString(6, celular.getGama().toString());

            ps.executeUpdate();
            System.out.println("Celular registrado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarCelular(claseCelulares celular) {

        String sql = "UPDATE celulares SET marca=?, modelo=?, precio=?, stock=?, sistema_operativo=?, gama=? WHERE id=?";

        try (Connection con = c.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, celular.getMarca().toString());
            ps.setString(2, celular.getModelo());
            ps.setDouble(3, celular.getPrecio());
            ps.setInt(4, celular.getStock());
            ps.setString(6, celular.getGama().toString());
            ps.setInt(7, celular.getId());

            ps.executeUpdate();
            System.out.println("Celular actualizado.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarCelular(int id) {

        String sql = "DELETE FROM celulares WHERE id=?";

        try (Connection con = c.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Celular eliminado.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public claseCelulares buscarPorId(int id) {

        String sql = "SELECT c.id, c.modelo, c.sistema_operativo, c.gama, c.precio, c.stock, "
                + "m.id AS id_marca, m.nombre AS nombre_marca "
                + "FROM celulares c "
                + "INNER JOIN marca m ON c.id_marca = m.id "
                + "WHERE c.id = ?";

        claseCelulares celular = null;

        try (Connection con = c.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                celular = new claseCelulares(
                        rs.getInt("id"),
                        new claseMarca(
                                rs.getInt("id_marca"),
                                rs.getString("nombre_marca")
                        ),
                        rs.getString("modelo"),
                        rs.getString("sistema_operativo"),
                        tipoGama.valueOf(rs.getString("gama").toUpperCase()),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return celular;
    }

    @Override
    public List<claseCelulares> listarCelulares() {

        List<claseCelulares> lista = new ArrayList<>();
        String sql = "SELECT c.id, c.modelo, c.sistema_operativo, c.gama, c.precio, c.stock, "
                + "m.id AS id_marca, m.nombre AS nombre_marca "
                + "FROM celulares c "
                + "INNER JOIN marca m ON c.id_marca = m.id";

        try (Connection con = c.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new claseCelulares(
                        rs.getInt("id"),
                        new claseMarca(
                                rs.getInt("id_marca"),
                                rs.getString("nombre_marca")
                        ),
                        rs.getString("modelo"),
                        rs.getString("sistema_operativo"),
                        tipoGama.valueOf(rs.getString("gama").toUpperCase()),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
