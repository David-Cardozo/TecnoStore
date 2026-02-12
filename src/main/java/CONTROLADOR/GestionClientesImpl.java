package CONTROLADOR;

import MODELO.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionClientesImpl implements GestionClientes {

    Conexion c = new Conexion();

    @Override
    public void registrarCliente(claseClientes cliente) {

        if (!Validaciones.validarCorreo(cliente.getCorreo())) {
            System.out.println("Correo inválido.");
            return;
        }

        if (!Validaciones.validarIdentificacionUnica(cliente.getIdentificacion())) {
            System.out.println("Identificación ya existe.");
            return;
        }

        String sql = "INSERT INTO clientes (nombre, identificacion, correo, telefono) VALUES (?, ?, ?, ?)";

        try (Connection con = c.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getIdentificacion());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getTelefono());

            ps.executeUpdate();
            System.out.println("Cliente registrado.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public claseClientes buscarPorIdentificacion(String identificacion) {

        String sql = "SELECT * FROM clientes WHERE identificacion=?";
        claseClientes cliente = null;

        try (Connection con = c.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, identificacion);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cliente = new claseClientes(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cliente;
    }

    @Override
    public List<claseClientes> listarClientes() {

        List<claseClientes> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection con = c.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new claseClientes(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
