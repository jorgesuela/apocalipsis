package Activable;

import Logica.Casilla;

import java.io.Serializable;

public abstract class Normal extends Zombi implements Serializable {


    public Normal(Casilla pos) {
        super(pos);
    }

    public void reaccionarAntesAtaque(Superviviente superviviente){
        super.reaccionarAntesAtaque(superviviente);
    };


}
