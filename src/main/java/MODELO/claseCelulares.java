package MODELO;

public class claseCelulares {

    private int id;
    private claseMarca marca;
    private String modelo;
    private String sistemaOperativo;
    private tipoGama gama;
    private double precio;
    private int stock;

    public claseCelulares(int id, claseMarca marca, String modelo,
                          String sistemaOperativo, tipoGama gama,
                          double precio, int stock) {

        if (precio <= 0) {
            throw new IllegalArgumentException("Precio inválido");
        }

        if (stock < 0) {
            throw new IllegalArgumentException("Stock inválido");
        }

        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.sistemaOperativo = sistemaOperativo;
        this.gama = gama;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() { return id; }
    public claseMarca getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getSistemaOperativo() { return sistemaOperativo; }
    public tipoGama getGama() { return gama; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    public void reducirStock(int cantidad) {
        if (cantidad > stock) {
            throw new IllegalArgumentException("Stock insuficiente");
        }
        stock -= cantidad;
    }

    @Override
    public String toString() {
        return marca + " " + modelo + " | $" + precio + " | Stock: " + stock;
    }
}
