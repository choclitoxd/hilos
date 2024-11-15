package co.edu.uniquindio.preparcil2.preparcial.ejercicio_7;

import co.edu.uniquindio.preparcil2.persistencia.ArchivoUtil;
import co.edu.uniquindio.preparcil2.preparcial.ejercicio_7.model.Autor;
import co.edu.uniquindio.preparcil2.preparcial.ejercicio_7.model.TrabajoGrado;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeerTrabajosGrado {
    private static final String pathDirectorioGrado ="persistencia/TrabajosGrado/trabajogrados.txt";
    private static final String pathAutor ="persistencia/TrabajosGrado/autores.txt";
//    private File dir = new File(pathDirectorio);
//    public ArrayList<File> buscarArchivos() {
//        ArrayList<File> resultado = new ArrayList<>();
//        if (!validarDirectorio()){
//            System.err.println("El directorio no existe");
//            return null;
//        }
//        File[] archivos = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".txt"));
//        if (archivos == null || archivos.length == 0) {
//            System.err.println("No se encontraron archivos .txt en el directorio.");
//            // Persistencia.guardaRegistroLog("No se encontraron archivos .txt en el directorio: " + directorio, 2, "BusquedaPalabra");
//            return null;
//        }
//        for (File archivo : archivos) {
//            resultado.add(archivo);
//        }
//        return resultado;
//    }
//
//    private boolean validarDirectorio(){
//        if (!dir.exists() || !dir.isDirectory()) {
//            System.err.println("El directorio especificado no existe o no es válido.");
//            //Persistencia.guardaRegistroLog("Directorio inválido: " + directorio, 2, "BusquedaPalabra");
//            return false;
//        }
//        return true;
//    }
    public static List<TrabajoGrado> leerArchivoGrado() throws IOException {
        List<TrabajoGrado> trabajoGrados =new ArrayList<>();
        List<String> contenido = ArchivoUtil.leerArchivo(pathDirectorioGrado);
        String linea="";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);
            TrabajoGrado trabajoGrado = new TrabajoGrado();
            trabajoGrado.getAutores().add(linea.split(";")[0]);
            trabajoGrado.getAutores().add(linea.split(";")[1]);
            trabajoGrado.setFecha(linea.split(";")[2]);
            trabajoGrado.setTitulo(linea.split(";")[3]);
            trabajoGrado.setDescripcion(linea.split(";")[4]);

            trabajoGrados.add(trabajoGrado);
        }
        return trabajoGrados;
    }
    public static List<Autor> leerArchivoAutores() throws IOException {
        List<Autor> autors =new ArrayList<>();
        List<String> contenido = ArchivoUtil.leerArchivo(pathAutor);
        String linea="";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);
            Autor autor = new Autor();
            autor.setNombre(linea.split(";")[0]);
            autor.setApellido(linea.split(";")[1]);
            autor.setIdentificacion(linea.split(";")[2]);
            autor.setTitulo(linea.split(";")[3]);

            autors.add(autor);
        }
        return autors;
    }
}
