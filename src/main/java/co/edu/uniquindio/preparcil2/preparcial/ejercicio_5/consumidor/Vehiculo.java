package co.edu.uniquindio.preparcil2.preparcial.ejercicio_5.consumidor;

import co.edu.uniquindio.preparcil2.preparcial.ejercicio_5.EstacionGasolina;
import co.edu.uniquindio.preparcil2.preparcial.ejercicio_5.sockets.Cliente;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Vehiculo implements Runnable{
    private EstacionGasolina buffer;
    private int capacidad;
    private int delay;
    private String identificacion;
    private String tipo;

    public Vehiculo(EstacionGasolina buffer, int capacidad, int delay,String identificacion,String tipo) {
        this.buffer = buffer;
        this.capacidad = capacidad;
        this.delay = delay;
        this.identificacion = identificacion;
        this.tipo = tipo;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int c1 = buffer.extraer(capacidad,identificacion,tipo,LocalDateTime.now());

//
                Thread.sleep(delay);

                if (c1 == capacidad) {
                    break;
                }
                if (c1 == -1){
                    break;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }




}
