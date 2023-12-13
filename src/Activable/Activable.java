package Activable;

import Logica.Casilla;
import Logica.Tablero;

import java.io.Serializable;

public abstract class Activable implements Serializable {
    protected Casilla posicion;
    protected Boolean vivo;

    public Activable(Casilla pos){
        this.vivo = true;
        this.posicion = pos;
    }

    //getters
    public Casilla getPosicion(){
        return this.posicion;
    }

    public void setPosicion(Casilla posicion) {
        this.posicion = posicion;
    }

    public boolean isVivo() {
        return this.vivo;
    }

    protected void setMuerto(){
        this.vivo = false;
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
}
