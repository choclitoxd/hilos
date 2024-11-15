package co.edu.uniquindio.preparcil2.preparcial.ejercicio_1_2.productor;

import co.edu.uniquindio.preparcil2.preparcial.ejercicio_1_2.BufferCompartido;

public class P2 extends Thread {
    private BufferCompartido buffer;
    private char[] caracteres;
    private int delay;

    public P2(BufferCompartido buffer, char[] caracteres, int delay) {
        this.buffer = buffer;
        this.caracteres = caracteres;
        this.delay = delay;
    }
    @Override
    public void run() {
        try {
            for (char c : caracteres) {
                if (Character.isLetter(c) && "aeiouAEIOU".indexOf(c) < 0) {
                    buffer.agregar(c);
                    System.out.println("Productor de " + "consonantes" + " envió: " + c);
                    Thread.sleep(delay);
                }
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
