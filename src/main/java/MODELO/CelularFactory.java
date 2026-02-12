package MODELO;

public interface CelularFactory {

    claseCelulares crearCelular(int id,
            claseMarca marca,
            String modelo,
            String sistemaOperativo,
            double precio,
            int stock);
}
