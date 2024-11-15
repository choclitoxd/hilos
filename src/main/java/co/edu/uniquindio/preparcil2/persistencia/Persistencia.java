package co.edu.uniquindio.preparcil2.persistencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Persistencia {
    private static final String pathLetra = "persistencia/letra.txt";
    private static final String pathBasedeDatosCliente = "persistencia/clientes.txt";
    private static final String pathRegistro = "persistencia/log/registro.txt";
    public static void guardarLetrasSobrantes(List<Character> lista) throws IOException {
        String ruta = pathLetra;
        String contenido = "";
        for(Character asignacion:lista) {
            contenido+= asignacion;
            contenido+="##";
        }
        ArchivoUtil.guardarArchivo(ruta, contenido, false);
    }

    public static void registrarLog(String mensaje, String accion) {
        String ruta = pathRegistro;
        ArchivoUtil.guardarRegistroLog(mensaje,1,"guardar",ruta);
    }
    public static void guardarDatosCliente(Queue<String> clientes) throws IOException {
        String ruta = pathBasedeDatosCliente;
        String contenido = "";
        for (String cliente:clientes) {
            contenido+= cliente;
            contenido+="\n";
        }
        ArchivoUtil.guardarArchivo(ruta, contenido, false);
    }
}
