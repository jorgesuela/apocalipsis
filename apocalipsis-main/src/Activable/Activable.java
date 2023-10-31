package Activable;

import logica.Casilla;

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

    public void moverse(){

    }

    public void activarse(){

    }
}
