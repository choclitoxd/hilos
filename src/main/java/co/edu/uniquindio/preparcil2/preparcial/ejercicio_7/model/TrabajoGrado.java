package co.edu.uniquindio.preparcil2.preparcial.ejercicio_7.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TrabajoGrado {
    private ArrayList<String> autores = new ArrayList<>();
    private String fecha;
    private String titulo;
    private String descripcion;
    public TrabajoGrado() {}
    public TrabajoGrado(ArrayList<String> autores, String fecha, String titulo, String descripcion) {
        this.autores = autores;
        this.fecha = fecha;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }
    public ArrayList<String> getAutores() {
        return autores;
    }
    public void setAutores(ArrayList<String> autores) {
        this.autores = autores;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
