package co.edu.uniquindio.preparcil2.preparcial.ejercicio_5.productor;


import co.edu.uniquindio.preparcil2.preparcial.ejercicio_5.EstacionGasolina;

public class C3 extends Thread {
    private EstacionGasolina buffer;
    private int capacidad;
    private int delay;

    public C3(EstacionGasolina buffer, int capacidad, int delay) {
        this.buffer = buffer;
        this.capacidad = capacidad;
        this.delay = delay;
    }
    @Override
    public void run() {
        try {
            while (capacidad > 0){
                buffer.agregar(20);
                System.out.println("Productor de " + "combustible" + " envió: " + 20);
                capacidad -= 20;
                Thread.sleep(delay);
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
