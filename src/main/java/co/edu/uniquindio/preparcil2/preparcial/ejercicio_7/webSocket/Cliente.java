package co.edu.uniquindio.preparcil2.preparcial.ejercicio_7.webSocket;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Queue;
import java.util.Scanner;

public class Cliente {
    String host;
    int puerto;
    Socket socketComunicacion;
    ObjectOutputStream flujoSalida;
    ObjectInputStream flujoEntrada;
    Scanner myObj = new Scanner(System.in);
    int numero;
    String mensaje;


    public Cliente(String host, int puerto) {
        this.puerto = puerto;
        this.host = host;
    }



    public void iniciarCliente() {

        try {
            crearConexion();

            flujoEntrada = new ObjectInputStream(socketComunicacion.getInputStream());
            flujoSalida = new ObjectOutputStream(socketComunicacion.getOutputStream());
            recibirDatosPrimitivos();

            System.out.println("Enviando datos al servidor");
            enviarDatosPrimitivos();

            recibirDatosPrimitivos();

            flujoEntrada.close();
            flujoSalida.close();
            socketComunicacion.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    private void enviarDatosPrimitivos() throws IOException {
//        flujoSalida.writeObject(clientes);
        System.out.println("Ingrese el numero del titulo");
        numero = Integer.parseInt(myObj.nextLine());
        flujoSalida.writeObject(numero);
    }


    private void recibirDatosPrimitivos() throws IOException, ClassNotFoundException {

//        System.out.println("Datos recibidos del servidor: "+flujoEntrada.readInt());
        System.out.println("Datos recibidos del servidor: "+"\n"+flujoEntrada.readObject());
    }



    private void crearConexion() throws IOException {
        socketComunicacion = new Socket(host, puerto);
    }

}

