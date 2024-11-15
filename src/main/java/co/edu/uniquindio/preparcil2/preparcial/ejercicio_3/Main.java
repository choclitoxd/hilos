package co.edu.uniquindio.preparcil2.preparcial.ejercicio_3;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String ruta = "persistencia/Directorio";
        String palabra1 = "Directorio";
        String palabra2 = "Santiago";

        Thread buscarPalabra1 = new Thread(new Buscador(ruta,100 ,palabra1));
        Thread buscarPalabra2 = new Thread(new Buscador(ruta,100 ,palabra2));
        buscarPalabra1.start();
        buscarPalabra2.start();
    }
}
