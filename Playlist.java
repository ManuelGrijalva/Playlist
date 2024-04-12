package edu.ejercicios;

import java.util.Scanner;

public class Playlist {
    Nodo inicio; // Inicio de la lista

    // Método para agregar una canción al final de la playlist
    public void agregarCancion(String nombre, String artista, String genero, int duracion) {
        Nodo nuevaCancion = new Nodo(nombre, artista, genero, duracion);
        if (inicio == null) {
            inicio = nuevaCancion;
        } else {
            Nodo actual = inicio;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevaCancion;
        }
    }

    // Método para imprimir la playlist completa
    public void imprimirPlaylist() {
        Nodo actual = inicio;
        while (actual != null) {
            System.out.println("Nombre: " + actual.nombre + ", Artista: " + actual.artista +
                    ", Género: " + actual.genero + ", Duración: " + actual.duracion + " segundos");
            actual = actual.siguiente;
        }
    }

    // Método para eliminar una canción de la playlist por su nombre
    public void eliminarCancion(String nombre) {
        if (inicio == null) {
            System.out.println("La playlist está vacía.");
            return;
        }

        if (inicio.nombre.equals(nombre)) {
            inicio = inicio.siguiente;
            System.out.println("Canción eliminada exitosamente.");
            return;
        }

        Nodo actual = inicio;
        while (actual.siguiente != null) {
            if (actual.siguiente.nombre.equals(nombre)) {
                actual.siguiente = actual.siguiente.siguiente;
                System.out.println("Canción eliminada exitosamente.");
                return;
            }
            actual = actual.siguiente;
        }
        System.out.println("La canción no se encuentra en la playlist.");
    }

    // Método para insertar una nueva canción en una posición específica de la playlist
    public void insertarCancion(String nombre, String artista, String genero, int duracion, int posicion) {
        if (posicion < 0) {
            System.out.println("La posición no puede ser negativa.");
            return;
        }

        Nodo nuevaCancion = new Nodo(nombre, artista, genero, duracion);

        if (posicion == 0) {
            nuevaCancion.siguiente = inicio;
            inicio = nuevaCancion;
            return;
        }

        Nodo actual = inicio;
        int contador = 0;
        while (actual != null && contador < posicion - 1) {
            actual = actual.siguiente;
            contador++;
        }

        if (actual == null) {
            System.out.println("La posición excede el tamaño de la playlist.");
        } else {
            nuevaCancion.siguiente = actual.siguiente;
            actual.siguiente = nuevaCancion;
        }
    }

    // Método para buscar una canción por su nombre y mostrar su información
    public void buscarCancion(String nombre) {
        Nodo actual = inicio;
        while (actual != null) {
            if (actual.nombre.equals(nombre)) {
                System.out.println("Nombre: " + actual.nombre + ", Artista: " + actual.artista +
                        ", Género: " + actual.genero + ", Duración: " + actual.duracion + " segundos");
                return;
            }
            actual = actual.siguiente;
        }
        System.out.println("La canción no se encuentra en la playlist.");
    }

    // Método para ordenar la playlist por nombre de canción
    public void ordenarPorNombre() {
        if (inicio == null || inicio.siguiente == null) {
            return;
        }

        Nodo i = inicio;
        while (i != null) {
            Nodo j = i.siguiente;
            while (j != null) {
                if (i.nombre.compareTo(j.nombre) > 0) {
                    // Intercambio de valores entre i y j
                    String tempNombre = i.nombre;
                    String tempArtista = i.artista;
                    String tempGenero = i.genero;
                    int tempDuracion = i.duracion;

                    i.nombre = j.nombre;
                    i.artista = j.artista;
                    i.genero = j.genero;
                    i.duracion = j.duracion;

                    j.nombre = tempNombre;
                    j.artista = tempArtista;
                    j.genero = tempGenero;
                    j.duracion = tempDuracion;
                }
                j = j.siguiente;
            }
            i = i.siguiente;
        }
    }

    // Método para calcular la duración total de la playlist en minutos y segundos
    public void calcularDuracionTotal() {
        int duracionTotal = 0;
        Nodo actual = inicio;
        while (actual != null) {
            duracionTotal += actual.duracion;
            actual = actual.siguiente;
        }
        int minutos = duracionTotal / 60;
        int segundos = duracionTotal % 60;
        System.out.println("Duración total de la playlist: " + minutos + " minutos y " + segundos + " segundos");
    }

    // Método para ejecutar un menú interactivo que permite al usuario interactuar con la playlist
    public void ejecutarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Agregar canción");
            System.out.println("2. Imprimir playlist");
            System.out.println("3. Eliminar canción");
            System.out.println("4. Insertar canción en una posición específica");
            System.out.println("5. Buscar canción por nombre");
            System.out.println("6. Ordenar playlist por nombre");
            System.out.println("7. Calcular duración total de la playlist");
            System.out.println("0. Salir");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre de la canción: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese el nombre del artista: ");
                    String artista = scanner.nextLine();
                    System.out.print("Ingrese el género de la canción: ");
                    String genero = scanner.nextLine();
                    System.out.print("Ingrese la duración de la canción (minutos) ");
                    String duracionStr = scanner.nextLine();
                    String[] duracionSplit = duracionStr.split(":");
                    int minutos = Integer.parseInt(duracionSplit[0]);
                    int segundos = Integer.parseInt(duracionSplit[1]);
                    int duracion = minutos * 60 + segundos;//convierte a segundos
                    agregarCancion(nombre, artista, genero, duracion);
                    break;
                case 2:
                    System.out.println("\nPlaylist:");
                    imprimirPlaylist();
                    break;
                case 3:
                    System.out.print("Ingrese el nombre de la canción que desea eliminar: ");
                    String nombreEliminar = scanner.nextLine();
                    eliminarCancion(nombreEliminar);
                    break;
                case 4:
                    System.out.print("Ingrese el nombre de la canción: ");
                    String nombreInsertar = scanner.nextLine();
                    System.out.print("Ingrese el nombre del artista: ");
                    String artistaInsertar = scanner.nextLine();
                    System.out.print("Ingrese el género de la canción: ");
                    String generoInsertar = scanner.nextLine();
                    System.out.print("Ingrese la duración de la canción en segundos: ");
                    int duracionInsertar = scanner.nextInt();
                    System.out.print("Ingrese la posición en la que desea insertar la canción: ");
                    int posicionInsertar = scanner.nextInt();
                    insertarCancion(nombreInsertar, artistaInsertar, generoInsertar, duracionInsertar, posicionInsertar);
                    break;
                case 5:
                    System.out.print("Ingrese el nombre de la canción que desea buscar: ");
                    String nombreBuscar = scanner.nextLine();
                    buscarCancion(nombreBuscar);
                    break;
                case 6:
                    ordenarPorNombre();
                    System.out.println("Playlist ordenada por nombre.");
                    break;
                case 7:
                    calcularDuracionTotal();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida, por favor intente de nuevo.");
            }
        } while (opcion != 0);
        scanner.close();
    }
}
