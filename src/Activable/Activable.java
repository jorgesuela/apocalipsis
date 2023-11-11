package Activable;

import logica.Casilla;
import logica.Tablero;

public abstract class Activable {
    private Casilla posicion;

    public Activable(Casilla pos){
        this.posicion = pos;
    }

    //getters
    public Casilla getPosicion(){
        return this.posicion;
    }

    public void setPosicion(Casilla posicion) {
        this.posicion = posicion;
    }

    public void atacar(Activable atacado){

    }

    protected boolean moverEnDireccion(Tablero tablero, String direccion) {
        int nuevaCoordX = this.getPosicion().getCoordx();
        int nuevaCoordY = this.getPosicion().getCoordy();

        switch (direccion) {
            case "left":
                nuevaCoordY--;
                break;
            case "right":
                nuevaCoordY++;
                break;
            case "up":
                nuevaCoordX--;
                break;
            case "down":
                nuevaCoordX++;
                break;
            default:
                return false; // Dirección no válida
        }

        if (tablero.getCasilla(nuevaCoordX, nuevaCoordY) == null) {
            System.out.println("No puedes moverte mas en esa direccion (final del tablero).");
            return false;
        }

        this.setPosicion(tablero.getCasilla(nuevaCoordX, nuevaCoordY));
        return true;
    }

    public void activarse(){

    }
}
