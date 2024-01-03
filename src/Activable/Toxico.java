package Activable;

import Logica.Casilla;

import java.io.Serializable;

public abstract class Toxico extends Zombi implements Serializable {

    public Toxico(Casilla pos) {
        super(pos);
    }




    @Override
    public void reaccionarAntesAtaque(Superviviente superviviente) {
        if(superviviente.getPosicion().equals(super.posicion)){
            superviviente.setNbHeridas(+1);
        }
        super.reaccionarAntesAtaque(superviviente);
    }
}
