package co.edu.uniquindio.preparcil2.preparcial.ejercicio_5.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Queue;
import java.util.Scanner;

public class Cliente {
    String host;
    int puerto;
    Socket socketComunicacion;
    ObjectOutputStream flujoSalida;
    DataInputStream flujoEntrada;
    Scanner myObj = new Scanner(System.in);
    int numero;
    String mensaje;
    Queue<String> clientes;


    public Cliente(String host, int puerto,Queue<String> clientes) {
        this.puerto = puerto;
        this.host = host;
        this.clientes = clientes;
    }



    public void iniciarCliente() {

        try {
                crearConexion();

                flujoEntrada = new DataInputStream(socketComunicacion.getInputStream());
                flujoSalida = new ObjectOutputStream(socketComunicacion.getOutputStream());
                enviarDatosPrimitivos();



                System.out.println("Enviando datos al servidor");

                recibirDatosPrimitivos();


                flujoEntrada.close();
                flujoSalida.close();
                socketComunicacion.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    private void enviarDatosPrimitivos() throws IOException {
        flujoSalida.writeObject(clientes);
        System.out.println("Enviando datos al servidor");
    }


    private void recibirDatosPrimitivos() throws IOException {

//        System.out.println("Datos recibidos del servidor: "+flujoEntrada.readInt());

        System.out.println("Datos recibidos del servidor: "+flujoEntrada.readUTF());
    }



    private void crearConexion() throws IOException {
        socketComunicacion = new Socket(host, puerto);
    }

}
