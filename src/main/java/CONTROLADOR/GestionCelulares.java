package CONTROLADOR;

import MODELO.claseCelulares;
import java.util.List;

public interface GestionCelulares {

    void registrarCelular(claseCelulares celular);

    void actualizarCelular(claseCelulares celular);

    void eliminarCelular(int id);

    claseCelulares buscarPorId(int id);

    List<claseCelulares> listarCelulares();
}
