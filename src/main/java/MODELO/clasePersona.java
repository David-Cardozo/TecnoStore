package MODELO;

public class clasePersona {

    protected int id;
    protected String nombre;
    protected String identificacion;
    protected String correo;
    protected String telefono;

    public clasePersona(int id, String nombre, String identificacion, 
                   String correo, String telefono) {

        if (!correo.contains("@")) {
            throw new IllegalArgumentException("Correo inválido");
        }

        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.correo = correo;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }
}
