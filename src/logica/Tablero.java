package logica;

import Activable.Superviviente;
import Activable.Zombi;
import Equipo.Suministro;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private Casilla[][] casillas;
    private Integer tamaño;

    public Tablero(int x) {
        this.casillas = new Casilla[x][x];
    }

    public Casilla getCasilla(int x, int y) {
        if (x >= 0 && x < casillas.length && y >= 0 && y < casillas[0].length) {
            return casillas[x][y];
        }
        return null; // Devuelve null si las coordenadas están fuera de los límites del tablero
    }

    public void crearTablero() {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                casillas[i][j] = new Casilla(i, j);
            }
        }
    }

    public void printTablero() {
        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                List<String> objetosEnCasilla = new ArrayList<>();

                int cantidadZombis = 0;
                int cantidadCiudadanos = 0;
                int cantidadSuministros = 0;

                //matrizCasillas esta mal, hay que pensar como hacer para comparar las casillas
                for (Zombi zombi : listaZombis) {
                    if (zombi.getPosicion().equals(matrizCasillas[fila][columna])) {
                        cantidadZombis++; // Contar zombis en la casilla
                    }
                }

                for (Superviviente superviviente : listaSupervivientes) {
                    if (superviviente.getPosicion().equals(matrizCasillas[fila][columna])) {
                        cantidadCiudadanos++; // Contar ciudadanos en la casilla
                    }
                }
                // no se si hay que mostrar el equipo tambien
                for (Suministro suministro : listaSuministros) {
                    if (suministro.getPosicion().equals(matrizCasillas[fila][columna])) {
                        cantidadSuministros++; // Contar suministros en la casilla
                    }
                }

                if (cantidadZombis > 0) {
                    objetosEnCasilla.add("Z" + cantidadZombis); // Agregar número de zombis
                }
                if (cantidadCiudadanos > 0) {
                    objetosEnCasilla.add("C" + cantidadCiudadanos); // Agregar número de ciudadanos
                }
                if (cantidadSuministros > 0) {
                    objetosEnCasilla.add("S" + cantidadSuministros); // Agregar número de suministros
                }

                if (objetosEnCasilla.isEmpty()) {
                    System.out.print("-"); // Casilla vacía
                } else {
                    // Si hay objetos en la casilla, mostrarlos todos
                    for (String objeto : objetosEnCasilla) {
                        System.out.print(objeto);
                    }
                }

                System.out.print(" ");
            }
            System.out.println();
        }
    }

}
