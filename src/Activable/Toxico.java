package Activable;

import Logica.Casilla;

import java.io.Serializable;

public abstract class Toxico extends Zombi implements Serializable {

    public Toxico(Casilla pos) {
        super(pos);
    }




    @Override
    public void reaccionarAntesAtaque() {

    }
}
