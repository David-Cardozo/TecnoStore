package MODELO;

public class CelularGamaBajaFactory implements CelularFactory {

    @Override
    public claseCelulares crearCelular(int id,
            claseMarca marca,
            String modelo,
            String sistemaOperativo,
            double precio,
            int stock) {

        return new claseCelulares(
                id,
                marca,
                modelo,
                sistemaOperativo,
                tipoGama.BAJA,
                precio,
                stock
        );
    }
}
