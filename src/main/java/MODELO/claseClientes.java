package MODELO;

public class claseClientes extends clasePersona {


    public claseClientes(int id, String nombre, String identificacion,
            String correo, String telefono) {
        super(id, nombre, identificacion, correo, telefono);
    }

    @Override
    public String toString() {
        return "Cliente: " + nombre + " - " + identificacion;
    }
}
