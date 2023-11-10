import Activable.Superviviente;
import Activable.Toxico;
import Activable.Zombi;
import Equipo.Arma;
import Equipo.Equipo;
import Equipo.Suministro;
import logica.Casilla;
import logica.Juego;
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

        Juego juego = new Juego();
        juego.iniciar();
    }
}

