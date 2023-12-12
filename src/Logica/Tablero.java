package Logica;

import Activable.Superviviente;
import Activable.Zombi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public int getTamano(){
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

    public void printTablero(ArrayList<Zombi> listaZombis, ArrayList<Superviviente> listaSupervivientes, Casilla casillaObjetivo) {
        // Imprimir números en la parte superior
        System.out.print("  ");
        for (int col = 0; col < tamaño; col++) {
            System.out.print(col + "      ");
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
                int counter = 1;
                for (Zombi zombi : listaZombis) {
                    if (zombi.getPosicion().equals(casillas[fila][columna])) {
                        objetosEnCasilla.add("  Z"+counter+"  ");
                        cantidadZombis++; // Contar zombis en la casilla
                    }
                    counter++;
                }

                if (cantidadZombis > 1) {
                    objetosEnCasilla.clear();
                    objetosEnCasilla.add(" Z*" + cantidadZombis+"  "); // Agregar número de zombis
                }

                for (Superviviente superviviente : listaSupervivientes) {
                    if (!superviviente.aSalvo() && superviviente.getPosicion().equals(casillas[fila][columna])) {

                        if (!objetosEnCasilla.isEmpty()){
                            String temp = objetosEnCasilla.get(0).trim();objetosEnCasilla.clear();
                            objetosEnCasilla.add(temp);
                            objetosEnCasilla.add("  "+superviviente.toString()+"");
                        }else{objetosEnCasilla.add("  "+superviviente.toString()+"  ");}

                        cantidadSupervivientes++; // Contar supervivientes en la casilla
                    }
                }

                if (cantidadSupervivientes > 0) {
                    // Modificar para agregar etiqueta "S" y la cantidad o la inicial en mayúscula
                    if (cantidadSupervivientes > 1) {
                        if (!objetosEnCasilla.isEmpty()){
                            String temp = objetosEnCasilla.get(0).trim();objetosEnCasilla.clear();
                            objetosEnCasilla.add(temp);
                            objetosEnCasilla.add("S*" + cantidadSupervivientes+"");
                        }else{objetosEnCasilla.add(" S*" + cantidadSupervivientes+"  ");}

                    } /*else {
                        objetosEnCasilla.add("S" + obtenerInicialMayuscula(listaSupervivientes, fila, columna));
                    }*/
                }

                // Verificar si la casilla es la casilla objetivo
                if (casillas[fila][columna].equals(casillaObjetivo)) {
                    objetosEnCasilla.add(" EXIT ");
                }


                if (objetosEnCasilla.isEmpty()) {
                    System.out.print("______ "); // Casilla vacía
                } else {
                    // Si hay objetos en la casilla, mostrarlos todos
                    for (String objeto : objetosEnCasilla) {
                        System.out.print(objeto);
                    }
                    System.out.print(" ");
                }
            }
            System.out.println();System.out.println();
        }




    }

    public Casilla casillaObjetivo(int tamaño) {
        int oposito = tamaño-1;
        return getCasilla(oposito,oposito);
    }


}
