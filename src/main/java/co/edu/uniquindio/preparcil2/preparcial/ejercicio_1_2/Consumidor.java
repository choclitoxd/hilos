package co.edu.uniquindio.preparcil2.preparcial.ejercicio_1_2;

import co.edu.uniquindio.preparcil2.persistencia.Persistencia;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class Consumidor implements Runnable {
    private BufferCompartido buffer;
    private String palabraObjetivo;
    private char[] arregloObjetivo;
    private char[] arregloPalabra;
    private List<Character> letrasSobrantes;
    private int delay;

    public Consumidor(BufferCompartido buffer, String palabraObjetivo, char[] arregloPalabra, List<Character> letrasSobrantes, int delay) {
        this.buffer = buffer;
        this.palabraObjetivo = palabraObjetivo;
        this.arregloPalabra = arregloPalabra;
        this.letrasSobrantes = letrasSobrantes;
        this.arregloObjetivo = palabraObjetivo.toCharArray();
        this.delay = delay;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c1 = buffer.extraer();
                char c2 = buffer.extraer();

                System.out.println("Consumidor recogió: " + c1 + ", " + c2);

                verificarCaracter(c1);
                verificarCaracter(c2);

                Thread.sleep(delay);

                if (new String(arregloPalabra).equals(palabraObjetivo)) {
                    System.out.println("Palabra formada: " + new String(arregloPalabra));
                    guardarLetrasSobrantes();
                    registrarLog();
                    break;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void verificarCaracter(char c) {
        boolean encontrado = false;
        for (int i = 0; i < palabraObjetivo.length(); i++) {
            if (c == arregloObjetivo[i]) {
                arregloPalabra[i] = c;
                encontrado = true;

            }
        }
        if (encontrado) {
            System.out.println("Caracter agregado a la palabra: " + c);
        }else {
            letrasSobrantes.add(c);
            System.out.println("Caracter no utilizado: " + c);
        }
    }

    private void guardarLetrasSobrantes() throws IOException {
        Persistencia.guardarLetrasSobrantes(letrasSobrantes);
    }

    private void registrarLog() {
        Persistencia.registrarLog("[" + LocalDateTime.now() + "] Palabra '"+ palabraObjetivo  +"' completada.", "Guardar");
    }
}
