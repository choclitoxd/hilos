package co.edu.uniquindio.preparcil2.preparcial.ejercicio_5;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;

public class EstacionGasolina {
    private Queue<String> buffer = new LinkedList<>();
    private int capacidad;
    private int suma;

    public EstacionGasolina(int capacidad) {
        this.capacidad = capacidad;
    }

    // Método sincronizado para agregar un elemento al buffer
    public synchronized void agregar(int valor) throws InterruptedException {

        while (suma == capacidad) {
            System.out.println("Buffer lleno. Productor espera...");
            wait(); // Espera hasta que el consumidor libere espacio
        }
        suma += valor;
        System.out.println("Capacidad que tiene disponible: " + suma);
        System.out.println("Productor agrega: " + valor);
        notifyAll(); // Notifica al consumidor que hay un nuevo elemento
    }

    // Método sincronizado para extraer un elemento del buffer
    public synchronized int extraer(int valorExtraer, String cliente, String tipo, LocalDateTime now) throws InterruptedException {
        System.out.println("Capacidad que tiene disponible: " + suma);

        long tiempoEspera = 3000; // Tiempo máximo de espera en milisegundos
        long inicioEspera = System.currentTimeMillis();

        // Mientras no haya suficiente suma y no se haya agotado el tiempo de espera
        while (suma < valorExtraer) {
            System.out.println("Buffer vacío. Consumidor espera...");

            // Calcular el tiempo restante de espera
            long tiempoRestante = tiempoEspera - (System.currentTimeMillis() - inicioEspera);
            if (tiempoRestante <= 0) {
                System.out.println("Tiempo de espera agotado. Consumidor deja de esperar.");
                return -1; // Indica que el tiempo de espera ha expirado sin éxito
            }

            wait(tiempoRestante); // Espera el tiempo restante o hasta que se notifique
        }

        suma -= valorExtraer;
        buffer.add(cliente+"#"+tipo+"#"+valorExtraer+"#"+now);
        System.out.println("Consumidor extrae: " + valorExtraer);
        notifyAll(); // Notifica al productor que hay espacio disponible

        return valorExtraer;
    }

    public Queue<String> getBuffer() {
        return buffer;
    }

}
