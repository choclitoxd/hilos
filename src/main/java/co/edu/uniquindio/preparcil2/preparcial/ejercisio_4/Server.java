package co.edu.uniquindio.preparcil2.preparcial.ejercisio_4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    String host = "localhost";
    int port = 8081;
    ServerSocket serverSocket;

    Socket socketComunication;
    DataOutputStream flujoSalida;
    DataInputStream flujoEntrada;
    String mensajeCliente;
    int numero;
    public Server() {
    }
    public void iniciarServidor() {
        try {
            serverSocket = new ServerSocket(port);
            while(true) {
                System.out.println("Esperando al cliente");
                socketComunication = serverSocket.accept();

                flujoSalida = new DataOutputStream(socketComunication.getOutputStream());
                flujoEntrada = new DataInputStream(socketComunication.getInputStream());
                recibirDatosPrimitivos();


                System.out.println("Recibiendo datos del cliente");

                enviarDatosPrimitivos();

                flujoEntrada.close();
                flujoSalida.close();
                socketComunication.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void recibirDatosPrimitivos() throws IOException {
        numero = flujoEntrada.readInt();
        System.out.println(numero);
        mensajeCliente = flujoEntrada.readUTF();
        System.out.println(mensajeCliente);
    }


    private void enviarDatosPrimitivos() throws IOException {
        int sizeNumero = countNumber(numero);
        flujoSalida.writeInt(sizeNumero);
        System.out.println("Enviando entero:"+ sizeNumero);
        int contadorConstante = contadorConsonantes(mensajeCliente,0,0);
        int contadorVocales = contadorVocales(mensajeCliente,0,0);
        flujoSalida.writeUTF("Consonantes: "+ String.valueOf(contadorConstante)+ " Vocales " +String.valueOf(contadorVocales));
        System.out.println("Consonantes: "+ String.valueOf(contadorConstante)+ " Vocales " +String.valueOf(contadorVocales));
    }

    private int contadorVocales(String mensajeCliente, int i, int i1) {
        if (i >= mensajeCliente.length()) {
            return i1;
        }
        if ("aeiouAEIOU".indexOf(mensajeCliente.charAt(i)) >= 0) {
            i1++;
        }
        return contadorVocales(mensajeCliente,i+1,i1)  ;
    }

    private int contadorConsonantes(String mensajeCliente, int i, int contador) {
        if (mensajeCliente.length() <= i){
            return contador;
        }
        if(Character.isLetter(mensajeCliente.charAt(i)) && "aeiouAEIOU".indexOf(mensajeCliente.charAt(i)) < 0){
            contador++;
        }
        return contadorConsonantes(mensajeCliente,i+1,contador)  ;
    }

    private int countNumber(int numero) {
        if (numero == 0) {
            return 0;
        }
        return 1 + countNumber(numero / 10);
    }

}

