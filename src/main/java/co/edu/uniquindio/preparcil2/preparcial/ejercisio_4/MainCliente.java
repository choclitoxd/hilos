package co.edu.uniquindio.preparcil2.preparcial.ejercisio_4;

import javax.swing.*;
import java.util.Scanner;

public class MainCliente {

	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		System.out.println("Ingrese port");
		String port = myObj.nextLine();
		System.out.println("Ingrese host");
		String host = myObj.nextLine();
		Cliente appCliente = new Cliente(host,Integer.parseInt(port));
		System.out.println("Iniciando cliente\n");
		appCliente.iniciarCliente();
	}

}
