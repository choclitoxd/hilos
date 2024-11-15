package co.edu.uniquindio.preparcil2.preparcial.ejercicio_3;


import co.edu.uniquindio.preparcil2.persistencia.ArchivoUtil;
import co.edu.uniquindio.preparcil2.persistencia.Persistencia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Buscador extends Thread {
    private int delay;
    private String directorio;
    private String palabra;
    private int cantidadArchivosEncontrados = 0;


    public Buscador(String directorio, int delay,String palabra) {
        this.directorio = directorio;
        this.delay = delay;
        this.palabra = palabra;
    }

    @Override
    public void run() {
        File dir = new File(directorio);
        if (!dir.exists() || !dir.isDirectory()) {
            System.err.println("El directorio especificado no existe o no es válido.");
            //Persistencia.guardaRegistroLog("Directorio inválido: " + directorio, 2, "BusquedaPalabra");
            return;
        }

        File[] archivos = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".txt"));
        if (archivos == null || archivos.length == 0) {
            System.err.println("No se encontraron archivos .txt en el directorio.");
            // Persistencia.guardaRegistroLog("No se encontraron archivos .txt en el directorio: " + directorio, 2, "BusquedaPalabra");
            return;
        }

        for (File archivo : archivos) {
            ArrayList<String> contenido = null;
            try {
                contenido = ArchivoUtil.leerArchivo(archivo.getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (String linea : contenido) {
                if (linea.contains(palabra)) {
                    cantidadArchivosEncontrados++;
                    break; // No es necesario seguir buscando en este archivo
                }
            }
        }
        System.out.println("Contador: "+cantidadArchivosEncontrados);

    }
}
