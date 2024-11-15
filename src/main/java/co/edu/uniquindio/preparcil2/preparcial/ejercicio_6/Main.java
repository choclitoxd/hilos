package co.edu.uniquindio.preparcil2.preparcial.ejercicio_6;


import co.edu.uniquindio.preparcil2.preparcial.ejercicio_6.consumidor.Consumidor;
import co.edu.uniquindio.preparcil2.preparcial.ejercicio_6.productor.P1;
import co.edu.uniquindio.preparcil2.preparcial.ejercicio_6.productor.P2;
import co.edu.uniquindio.preparcil2.preparcial.ejercicio_6.productor.P3;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        char[] caracteres = "abcdefghijklmnoprstuvwx6y0123456789".toCharArray();

        Tuberia buffer = new Tuberia(8);
        String palabraObjetivo = "otorrin0lar1ng010go";
        char[] arregloPalabra = new char[palabraObjetivo.length()];
        Arrays.fill(arregloPalabra, '_');

        Thread productorVocales = new Thread(new P1(buffer,caracteres,100));
        Thread productorConsonantes = new Thread(new P2(buffer,caracteres,150));
        Thread productorNumeros = new Thread(new P3(buffer,caracteres,250));
        productorVocales.start();
        productorConsonantes.start();
        productorNumeros.start();
        Thread consumidor = new Thread(new Consumidor(buffer, palabraObjetivo, arregloPalabra,500));
        consumidor.start();
    }
}
