package CONTROLADOR;

import MODELO.claseClientes;
import java.util.List;

public interface GestionClientes {

    void registrarCliente(claseClientes cliente);

    claseClientes buscarPorIdentificacion(String identificacion);

    List<claseClientes> listarClientes();
}
