package co.edu.uniquindio.preparcil2.preparcial.ejercicio_1_2.productor;

import co.edu.uniquindio.preparcil2.preparcial.ejercicio_1_2.BufferCompartido;

public class P3 extends Thread {
    private BufferCompartido buffer;
    private char[] caracteres;
    private int delay;

    public P3(BufferCompartido buffer, char[] caracteres, int delay) {
        this.buffer = buffer;
        this.caracteres = caracteres;
        this.delay = delay;
    }
    @Override
    public void run() {
        try {
            for (char c : caracteres) {
                if (Character.isDigit(c)) {
                    buffer.agregar(c);
                    System.out.println("Productor de " + "numero" + " envió: " + c);
                    Thread.sleep(delay);
                }
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
