import Activable.Superviviente;
import Activable.Toxico;
import Activable.Zombi;
import Equipo.Arma;
import Equipo.Equipo;
import Equipo.Suministro;
import logica.Casilla;
import logica.Tablero;

import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // crear arma aleatoria y mostrar info
        Equipo arma = Arma.generarArmaAleatoria();
        arma.mostrarInfo();

        // crear suministro aleatorio y mostrar info
        Equipo suministro = Suministro.generarSuministroAleatorio();
        suministro.mostrarInfo();

        // Crear un tablero
        Tablero tablero = new Tablero(5);

// Crear casillas y agregar zombis a las casillas
        Casilla casilla1 = tablero.getCasilla(2, 2);
        Zombi zombi1 = new Toxico(casilla1, 1, 2);

        Casilla casilla2 = tablero.getCasilla(4, 4);
        Zombi zombi2 = new Toxico(casilla2, 1, 2);

// Crear listas de zombis y supervivientes
        ArrayList<Zombi> listaZombi = new ArrayList<>();
        ArrayList<Superviviente> listaSup = new ArrayList<>();

// Agregar los zombis a la lista de zombis
        listaZombi.add(zombi1);
        listaZombi.add(zombi2);

// Llamar a printTablero pasando las listas de zombis y supervivientes
        tablero.printTablero(listaZombi, listaSup);

// prueba moviendo un zombie del tablero
        zombi2.moverse(tablero);
        tablero.printTablero(listaZombi, listaSup);
        System.out.println(zombi2.getPosicion().toString());
    }
}
