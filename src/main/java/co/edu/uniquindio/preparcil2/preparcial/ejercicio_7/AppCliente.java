package co.edu.uniquindio.preparcil2.preparcial.ejercicio_7;

import co.edu.uniquindio.preparcil2.preparcial.ejercicio_7.webSocket.Cliente;

public class AppCliente {
    public static void main(String[] args) {
        Cliente appCliente = new Cliente("localhost",8081);
        appCliente.iniciarCliente();
    }
}
