package co.edu.uniquindio.preparcil2.preparcial.ejercicio_6.consumidor;



import co.edu.uniquindio.preparcil2.preparcial.ejercicio_6.Tuberia;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class Consumidor implements Runnable {
    private Tuberia buffer;
    private String palabraObjetivo;
    private char[] arregloObjetivo;
    private char[] arregloPalabra;
    private int delay;

    public Consumidor(Tuberia buffer, String palabraObjetivo, char[] arregloPalabra, int delay) {
        this.buffer = buffer;
        this.palabraObjetivo = palabraObjetivo;
        this.arregloPalabra = arregloPalabra;
        this.arregloObjetivo = palabraObjetivo.toCharArray();
        this.delay = delay;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c1 = buffer.extraer();
                System.out.println("Consumidor recogió: " + new String(arregloPalabra));

                verificarCaracter(c1);

                Thread.sleep(delay);

                if (new String(arregloPalabra).equals(palabraObjetivo)) {
                    System.out.println("Palabra formada: " + new String(arregloPalabra));
                    break;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void verificarCaracter(char c) {
        boolean encontrado = false;
        for (int i = 0; i < palabraObjetivo.length(); i++) {
            if (c == arregloObjetivo[i]) {
                arregloPalabra[i] = c;
                encontrado = true;

            }
        }
        if (encontrado) {
            System.out.println("Caracter agregado a la palabra: " + c);
        }
    }

}
