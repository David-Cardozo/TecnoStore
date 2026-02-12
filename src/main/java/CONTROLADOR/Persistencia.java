package CONTROLADOR;

import java.io.*;
import java.util.ArrayList;

public class Persistencia {

    private static final String RUTA = System.getProperty("user.home") + "/Documents/celulares.dat";

    // GUARDAR LISTA EN ARCHIVO
    public static void guardar(ArrayList<?> lista) {
        try (ObjectOutputStream oos
                = new ObjectOutputStream(new FileOutputStream(RUTA))) {

            oos.writeObject(lista);
            System.out.println("Datos guardados correctamente.");

        } catch (IOException e) {
            System.out.println("Error al guardar los datos.");
        }
    }

    // CARGAR LISTA DESDE ARCHIVO
    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> cargar() {

        ArrayList<T> lista = new ArrayList<>();

        try (ObjectInputStream ois
                = new ObjectInputStream(new FileInputStream(RUTA))) {

            lista = (ArrayList<T>) ois.readObject();
            System.out.println("Datos cargados correctamente.");

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Se creará uno nuevo.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los datos.");
        }

        return lista;
    }
}
