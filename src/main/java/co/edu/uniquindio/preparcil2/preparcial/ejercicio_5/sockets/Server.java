package co.edu.uniquindio.preparcil2.preparcial.ejercicio_5.sockets;

import co.edu.uniquindio.preparcil2.persistencia.Persistencia;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;

public class Server {
    String host = "localhost";
    int port = 8081;
    ServerSocket serverSocket;

    Socket socketComunication;
    DataOutputStream flujoSalida;
    ObjectInputStream flujoEntrada;
    Queue<String> clientes;
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
                flujoEntrada = new ObjectInputStream(socketComunication.getInputStream());
                recibirDatosPrimitivos();
                guardarDatosPrimitivos();
                System.out.println("Recibiendo datos del cliente");

                enviarDatosPrimitivos();

                flujoEntrada.close();
                flujoSalida.close();
                socketComunication.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void guardarDatosPrimitivos() throws IOException {
        Persistencia.guardarDatosCliente(clientes);
    }

    private void recibirDatosPrimitivos() throws IOException, ClassNotFoundException {
        clientes = (Queue<String>) flujoEntrada.readObject();
    }


    private void enviarDatosPrimitivos() throws IOException {
        flujoSalida.writeUTF("Listo");
    }


}

