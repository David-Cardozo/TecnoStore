package CONTROLADOR;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    private String ip = "localhost";
    private String database = "tecnostore_db";
    private String user = "root";
    private String password = "Sajuma23";

    public Connection conectar() {
        Connection c = null;
        try {
//            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/campusan", "root", "123");
            c = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + database, user, password);
            JOptionPane.showMessageDialog(null, "Se conecto correctamente");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }
}
