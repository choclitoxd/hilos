package co.edu.uniquindio.preparcil2.preparcial.ejercisio_4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    String host;
    int puerto;
    Socket socketComunicacion;
    DataOutputStream flujoSalida;
    DataInputStream flujoEntrada;
    Scanner myObj = new Scanner(System.in);
    int numero;
    String mensaje;
    int contador = 0;


    public Cliente(String host, int puerto) {
        this.puerto = puerto;
        this.host = host;
    }



    public void iniciarCliente() {


        try {
            while(true) {
                crearConexion();

                flujoEntrada = new DataInputStream(socketComunicacion.getInputStream());
                flujoSalida = new DataOutputStream(socketComunicacion.getOutputStream());
                enviarDatosPrimitivos();



                System.out.println("Enviando datos al servidor");

                recibirDatosPrimitivos();


                flujoEntrada.close();
                flujoSalida.close();
                socketComunicacion.close();

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    private void enviarDatosPrimitivos() throws IOException {
        System.out.println("Ingrese numero");
        numero = Integer.parseInt(myObj.nextLine());
        flujoSalida.writeInt(numero);
        System.out.println("Ingrese palabra");
        mensaje = myObj.nextLine();
        flujoSalida.writeUTF(mensaje);
    }


    private void recibirDatosPrimitivos() throws IOException {

        System.out.println("Datos recibidos del servidor: "+flujoEntrada.readInt());

        System.out.println("Datos recibidos del servidor: "+flujoEntrada.readUTF());
    }



    private void crearConexion() throws IOException {
        socketComunicacion = new Socket(host, puerto);
    }

}
