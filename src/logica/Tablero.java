package logica;

import Activable.Superviviente;
import Activable.Zombi;
import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private final Casilla[][] casillas;
    private final int tamaño;

    public Tablero(int tamaño) {
        this.tamaño = tamaño;
        this.casillas = new Casilla[tamaño][tamaño];

        // Inicializar el tablero
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                casillas[i][j] = new Casilla(i, j);
            }
        }
    }

    public Casilla getCasilla(int x, int y) {
        if (x >= 0 && x < casillas.length && y >= 0 && y < casillas[0].length) {
            return casillas[x][y];
        }
        return null; // Devuelve null si las coordenadas están fuera de los límites del tablero
    }

    public int getTamaño(){
        return this.tamaño;
    }

    public int cuantosZombi(ArrayList<Zombi> listaZombis, int x, int y){
        int contador = 0;
        if (x >= 0 && x < casillas.length && y >= 0 && y < casillas[0].length) {
            for (Zombi zombi : listaZombis) {
                if (zombi.getPosicion().equals(casillas[x][y])) {
                    contador += 1;
                }
            }
        }
        return contador; // devuelve n zombis de casilla
    }

    public void printTablero(ArrayList<Zombi> listaZombis, ArrayList<Superviviente> listaSupervivientes) {
        // Imprimir números en la parte superior
        System.out.print("  ");
        for (int col = 0; col < tamaño; col++) {
            System.out.print(col + "  ");
        }
        System.out.println();

        for (int fila = 0; fila < tamaño; fila++) {
            // Imprimir número a la izquierda
            System.out.print(fila + " ");

            for (int columna = 0; columna < tamaño; columna++) {
                List<String> objetosEnCasilla = new ArrayList<>();

                int cantidadZombis = 0;
                int cantidadSupervivientes = 0;

                // matrizCasillas está mal, hay que pensar cómo hacer para comparar las casillas
                for (Zombi zombi : listaZombis) {
                    if (zombi.getPosicion().equals(casillas[fila][columna])) {
                        cantidadZombis++; // Contar zombis en la casilla
                    }
                }

                for (Superviviente superviviente : listaSupervivientes) {
                    if (superviviente.getPosicion().equals(casillas[fila][columna])) {
                        cantidadSupervivientes++; // Contar supervivientes en la casilla
                    }
                }

                if (cantidadZombis > 0) {
                    objetosEnCasilla.add("Z" + cantidadZombis); // Agregar número de zombis
                }
                if (cantidadSupervivientes > 0) {
                    // Modificar para agregar etiqueta "S" y la cantidad o la inicial en mayúscula
                    if (cantidadSupervivientes > 1) {
                        objetosEnCasilla.add("S" + cantidadSupervivientes);
                    } else {
                        objetosEnCasilla.add("S" + obtenerInicialMayuscula(listaSupervivientes, fila, columna));
                    }
                }

                if (objetosEnCasilla.isEmpty()) {
                    System.out.print("__ "); // Casilla vacía
                } else {
                    // Si hay objetos en la casilla, mostrarlos todos
                    for (String objeto : objetosEnCasilla) {
                        System.out.print(objeto);
                    }
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    // Método para obtener la inicial en mayúscula del nombre del superviviente
    private String obtenerInicialMayuscula(ArrayList<Superviviente> listaSupervivientes, int fila, int columna) {
        for (Superviviente superviviente : listaSupervivientes) {
            if (superviviente.getPosicion().equals(casillas[fila][columna])) {
                String nombre = superviviente.getNombre();
                return String.valueOf(nombre.charAt(0)).toUpperCase();
            }
        }
        return "";
    }


}
