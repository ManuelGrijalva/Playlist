package edu.ejercicios;


// Clase Nodo que representa una canción en la playlist
public class Nodo {
    String nombre;
    String artista;
    String genero;
    int duracion;
    Nodo siguiente;

    // Constructor de la clase Nodo
    public Nodo(String nombre, String artista, String genero, int duracion) {
        this.nombre = nombre;
        this.artista = artista;
        this.genero = genero;
        this.duracion = duracion;
        this.siguiente = null; // Inicialmente el siguiente es null
    }
}

