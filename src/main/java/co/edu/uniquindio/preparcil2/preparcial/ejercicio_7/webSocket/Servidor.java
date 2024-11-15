package co.edu.uniquindio.preparcil2.preparcial.ejercicio_7.webSocket;

import co.edu.uniquindio.preparcil2.preparcial.ejercicio_7.LeerTrabajosGrado;
import co.edu.uniquindio.preparcil2.preparcial.ejercicio_7.model.Autor;
import co.edu.uniquindio.preparcil2.preparcial.ejercicio_7.model.TrabajoGrado;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    LeerTrabajosGrado leerTrabajosGrado;
    String host = "localhost";
    int port = 8081;
    ServerSocket serverSocket;
    List<TrabajoGrado> archivosTrabajo;
    List<Autor> autores;
    Socket socketComunication;
    ObjectOutputStream flujoSalida;
    ObjectInputStream flujoEntrada;
    int indiceAutor;
    boolean autorListo = false;
    public Servidor() {
    }
    public void iniciarServidor() {
        try {
            serverSocket = new ServerSocket(port);
            while(true) {
                System.out.println("Esperando al cliente");
                socketComunication = serverSocket.accept();
                listaArchivos();
                flujoSalida = new ObjectOutputStream(socketComunication.getOutputStream());
                flujoEntrada = new ObjectInputStream(socketComunication.getInputStream());
                enviarDatosPrimitivos();
                System.out.println("Mandando datos al cliente");

                recibirDatosPrimitivos();
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
    private void recibirDatosPrimitivos() throws IOException, ClassNotFoundException {
        indiceAutor = (int) flujoEntrada.readObject();
        System.out.println("Recibiendo datos primitivos" + indiceAutor);
        if (indiceAutor <= archivosTrabajo.size() && indiceAutor >= 0) {
            autorListo = true;
        }
    }

    private void enviarDatosPrimitivos() throws IOException {
        if (autorListo) {
            flujoSalida.writeObject(generarMensajeAutores());
            autorListo = false;
        }else{
            flujoSalida.writeObject(generarMensajeListaArchivos());
        }


    }

    private String generarMensajeAutores() {
        String mensaje = "";
        for (Autor autor : autores) {
            String nombre = autor.getNombre() + " " + autor.getApellido();
            for (String nombreArchivoAutor: archivosTrabajo.get(indiceAutor).getAutores()){
                if (nombre.equalsIgnoreCase(nombreArchivoAutor)){
                    mensaje += autor.getNombre() +" " + autor.getApellido()+ " "+ autor.getIdentificacion()+ " "+autor.getTitulo()+ "\n";
                }
            }
        }
        return mensaje;
    }

    private void listaArchivos() throws IOException {
        archivosTrabajo = LeerTrabajosGrado.leerArchivoGrado();
        autores = LeerTrabajosGrado.leerArchivoAutores();

    }
    private String generarMensajeListaArchivos(){
        String cotenido = "";
        if (archivosTrabajo != null){
            for (int i = 0; i < archivosTrabajo.size() ; i++) {
                cotenido += i +": "+ archivosTrabajo.get(i).getTitulo() + "\n";
            }
            return cotenido;
        }
        cotenido = "No ahi nada";
        return cotenido;
    }

}
