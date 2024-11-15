package co.edu.uniquindio.preparcil2.preparcial.ejercicio_6.productor;

import co.edu.uniquindio.preparcil2.preparcial.ejercicio_6.Tuberia;

import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class P1 extends Thread {
    private BlockingQueue<Integer> indicesRestantes;
    private Tuberia buffer;
    private int delay;
    private char[] caracteres;
    public P1(Tuberia buffer, char[] caracteres, int delay) {
        this.delay = delay;
        this.caracteres = caracteres;
        this.buffer = buffer;
        this.indicesRestantes = new LinkedBlockingQueue<>();
        for (int i = 0; i < caracteres.length; i++) {
            indicesRestantes.add(i);
        }
    }
    @Override
    public void run() {
        try {
            while (!indicesRestantes.isEmpty()) {
                int index = generarNumeroAleatorio();
                if (index >= caracteres.length) {
                    System.out.println("Índice fuera de límites: " + index);
                    continue; // Saltar esta iteración si el índice está fuera de los límites
                }
                char c = caracteres[index];
                if ("aeiou".indexOf(c) >= 0) {
                    buffer.agregar(c);
                    System.out.println("Productor de " + "vocales" + " envió: " + c);
                    Thread.sleep(delay);
                }
            }


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    private int generarNumeroAleatorio() throws InterruptedException {
        return indicesRestantes.take();
    }
}
