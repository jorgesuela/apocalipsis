package Activable;

import logica.Casilla;
import logica.Tablero;

import java.util.Scanner;

public abstract class Activable {
    private Casilla posicion;

    public Activable(Casilla pos){
        this.posicion = pos;
    }

    //getters
    public Casilla getPosicion(){
        return this.posicion;
    }

    public void atacar(Activable atacado){

    }

    public void moverse(Tablero tablero){
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.println("Elige una direccion: up, down, left, right");
            input = scanner.nextLine();

            switch (input) {
                case "left":
                    if (tablero.getCasilla(this.posicion.getCoordx(), this.posicion.getCoordy() - 1) == null){
                        System.out.println("no puedes moverte mas hacia la izquierda (final del tablero)");
                    }
                    else{
                        this.posicion = tablero.getCasilla(this.posicion.getCoordx(), this.posicion.getCoordy() - 1);
                        System.out.println("el superviviente avanzo a la casilla" + this.posicion.toString());
                        break;
                    }

                case "right":
                    if (tablero.getCasilla(this.posicion.getCoordx(), this.posicion.getCoordy() + 1) == null){
                        System.out.println("no puedes moverte mas hacia la derecha (final del tablero)");
                    }
                    else{
                        this.posicion = tablero.getCasilla(this.posicion.getCoordx(), this.posicion.getCoordy() + 1);
                        System.out.println("el superviviente avanzo a la casilla" + this.posicion.toString());
                        break;
                    }

                case "up":
                    if (tablero.getCasilla(this.posicion.getCoordx() - 1, this.posicion.getCoordy()) == null){
                        System.out.println("no puedes moverte mas hacia arriba (final del tablero)");
                    }
                    else{
                        this.posicion = tablero.getCasilla(this.posicion.getCoordx() - 1, this.posicion.getCoordy());
                        System.out.println("el superviviente avanzo a la casilla" + this.posicion.toString());
                        break;
                    }

                case "down":
                    if (tablero.getCasilla(this.posicion.getCoordx() + 1, this.posicion.getCoordy()) == null){
                        System.out.println("no puedes moverte mas hacia abajo (final del tablero)");
                    }
                    else{
                        this.posicion = tablero.getCasilla(this.posicion.getCoordx() + 1, this.posicion.getCoordy());
                        System.out.println("el superviviente avanzo a la casilla" + this.posicion.toString());
                        break;
                    }

                default:
                    System.out.println("Opcion no valida. Por favor, elige una direccion valida.");
            }

            if (input.equals("up") || input.equals("down") || input.equals("left") || input.equals("right")) {
                break; // Sal del bucle si se selecciona una opción válida
            }
        }
    }

    public void activarse(){

    }
}
