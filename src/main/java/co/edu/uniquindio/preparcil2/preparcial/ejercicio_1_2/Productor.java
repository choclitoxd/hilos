package co.edu.uniquindio.preparcil2.preparcial.ejercicio_1_2;

import java.util.concurrent.BlockingQueue;

public class Productor implements Runnable {
    private BlockingQueue<Character> buffer;
    private char[] caracteres;
    private String tipo;
    private int delay;

    public Productor(BlockingQueue<Character> buffer, char[] caracteres, String tipo, int delay) {
        this.buffer = buffer;
        this.caracteres = caracteres;
        this.tipo = tipo;
        this.delay = delay;
    }

    @Override
    public void run() {
        try {
            for (char c : caracteres) {
                if ((tipo.equals("vocales") && "aeiouAEIOU".indexOf(c) >= 0) || (tipo.equals("consonantes") && Character.isLetter(c) && "aeiouAEIOU".indexOf(c) < 0) ||
                        (tipo.equals("numeros") && Character.isDigit(c)) || (tipo.equals("especiales") && "@#-*$/()%+:;".indexOf(c) >= 0)) {
                    buffer.put(c);
                    System.out.println("Productor de " + tipo + " envió: " + c);
                    Thread.sleep(delay);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
