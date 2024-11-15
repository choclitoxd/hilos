package co.edu.uniquindio.preparcil2.preparcial.ejercicio_6;

import java.util.LinkedList;
import java.util.Queue;

public class Tuberia {
    private Queue<Character> buffer;
    private int capacidad;

    public Tuberia(int capacidad) {
        this.buffer = new LinkedList<>();
        this.capacidad = capacidad;
    }

    // Método sincronizado para agregar un elemento al buffer
    public synchronized void agregar(char valor) throws InterruptedException {
        while (buffer.size() == capacidad) {
            System.out.println("Buffer lleno. Productor espera...");
            wait(); // Espera hasta que el consumidor libere espacio
        }
        buffer.add(valor);
        System.out.println("Productor agrega: " + valor);
        notifyAll(); // Notifica al consumidor que hay un nuevo elemento
    }

    // Método sincronizado para extraer un elemento del buffer
    public synchronized char extraer() throws InterruptedException {
        while (buffer.isEmpty()) {
            System.out.println("Buffer vacío. Consumidor espera...");
            wait(); // Espera hasta que el productor agregue un elemento
        }
        char valor = buffer.poll();
        System.out.println("Consumidor extrae: " + valor);
        notifyAll(); // Notifica al productor que hay espacio disponible
        return valor;
    }
}
