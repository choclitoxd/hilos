package co.edu.uniquindio.preparcil2.preparcial.ejercicio_5;



import co.edu.uniquindio.preparcil2.preparcial.ejercicio_5.consumidor.Vehiculo;
import co.edu.uniquindio.preparcil2.preparcial.ejercicio_5.productor.C1;
import co.edu.uniquindio.preparcil2.preparcial.ejercicio_5.productor.C3;
import co.edu.uniquindio.preparcil2.preparcial.ejercicio_5.productor.C4;
import co.edu.uniquindio.preparcil2.preparcial.ejercicio_5.sockets.Cliente;

import java.time.LocalDateTime;
import java.util.Queue;

public class Main {


    public static void main(String[] args) {
        combustible();
    }
    private static void combustible(){

        int contador = 0;
        EstacionGasolina estacionGasolina = new EstacionGasolina(1000);
        Thread camion1 = new Thread(new C1(estacionGasolina,100,100));
        Thread camion2 = new Thread(new C1(estacionGasolina,100,150));
        Thread camion3 = new Thread(new C3(estacionGasolina,100,250));
        Thread camion4 = new Thread(new C4(estacionGasolina,100,350));

        camion1.start();
        camion2.start();
        camion3.start();
        camion4.start();

        // Crear e iniciar el hilo consumidor
        int numeroDeVehiculos = 13;

        for (int i = 1; i <= numeroDeVehiculos; i++) {
            // Crear un nombre y tipo específicos para cada hilo
            String nombre = "Conductor" + i;
            String tipo = i % 2 == 0 ? "Carro" : "Moto"; // Alterna entre "Carro" y "Moto"

            // Alterna entre el valor 10 y 4 según la iteración
            int parametroVariable = (i % 2 == 0) ? 10 : 4;
            contador += parametroVariable;
            // Crea un nuevo hilo Vehiculo con los parámetros que deseas
            Thread vehiculo = new Thread(new Vehiculo(estacionGasolina, parametroVariable, 500, nombre, tipo));

            // Inicia el hilo
            vehiculo.start();
        }
        System.out.println("Suma contador" + contador);
        for (String cliente:estacionGasolina.getBuffer()){
            System.out.println("Cliente: "+cliente);
        }
        establecerConexionServidor(estacionGasolina.getBuffer());
    }
    private static void establecerConexionServidor(Queue<String> buffer) {
        Cliente appCliente = new Cliente("localhost",8081,buffer);
        appCliente.iniciarCliente();
    }

//
}




