package co.edu.uniquindio.preparcil2.preparcial.ejercicio_1_2;

import co.edu.uniquindio.preparcil2.preparcial.ejercicio_1_2.productor.P1;
import co.edu.uniquindio.preparcil2.preparcial.ejercicio_1_2.productor.P2;
import co.edu.uniquindio.preparcil2.preparcial.ejercicio_1_2.productor.P3;
import co.edu.uniquindio.preparcil2.preparcial.ejercicio_1_2.productor.P4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static char[] caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#-*$/()%+:;".toCharArray();

    public static void main(String[] args) {
        universidad();
        //programacion();
    }
    private static void universidad(){
        BufferCompartido buffer = new BufferCompartido(12);
//        BlockingQueue<Character> buffer = new ArrayBlockingQueue<>(12);
        String palabraObjetivo = "universid@d#2024-2%";
//        char[] caracteresObjetivo = palabraObjetivo.toCharArray();
        char[] arregloPalabra = new char[palabraObjetivo.length()];
        Arrays.fill(arregloPalabra, '_');

        List<Character> letrasSobrantes = new ArrayList<>();

        // Crear e iniciar los hilos productores
//        Thread productorVocales = new Thread(new Productor(buffer, caracteres, "vocales", 100));
//        Thread productorConsonantes = new Thread(new Productor(buffer, caracteres, "consonantes", 150));
//        Thread productorNumeros = new Thread(new Productor(buffer, caracteres, "numeros", 250));
//        Thread productorEspeciales = new Thread(new Productor(buffer, caracteres, "especiales", 350));
        Thread productorVocales = new Thread(new P1(buffer,caracteres,100));
        Thread productorConsonantes = new Thread(new P2(buffer,caracteres,150));
        Thread productorNumeros = new Thread(new P3(buffer,caracteres,250));
        Thread productorEspeciales = new Thread(new P4(buffer,caracteres,350));

        productorVocales.start();
        productorConsonantes.start();
        productorNumeros.start();
        productorEspeciales.start();

        // Crear e iniciar el hilo consumidor
        Thread consumidor = new Thread(new Consumidor(buffer, palabraObjetivo, arregloPalabra, letrasSobrantes,500));
        consumidor.start();
    }
//    private static void programacion(){
//        BlockingQueue<Character> buffer = new ArrayBlockingQueue<>(10);
//        String palabraObjetivo = "progr@macion_3#2023%";
////        char[] caracteresObjetivo = palabraObjetivo.toCharArray();
//        char[] arregloPalabra = new char[palabraObjetivo.length()];
//        Arrays.fill(arregloPalabra, '_');
//
//        List<Character> letrasSobrantes = new ArrayList<>();
//
//        // Crear e iniciar los hilos productores
//        Thread productorVocales = new Thread(new Productor(buffer, caracteres, "vocales", 50));
//        Thread productorConsonantes = new Thread(new Productor(buffer, caracteres, "consonantes", 150));
//        Thread productorNumeros = new Thread(new Productor(buffer, caracteres, "numeros", 250));
//        Thread productorEspeciales = new Thread(new Productor(buffer, caracteres, "especiales", 350));
//
//        productorVocales.start();
//        productorConsonantes.start();
//        productorNumeros.start();
//        productorEspeciales.start();
//
//        // Crear e iniciar el hilo consumidor
//        Thread consumidor = new Thread(new Consumidor(buffer, palabraObjetivo, arregloPalabra, letrasSobrantes,200));
//        consumidor.start();
//    }
}




