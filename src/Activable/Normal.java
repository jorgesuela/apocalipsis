package Activable;

import Logica.Casilla;

import java.io.Serializable;

public abstract class Normal extends Zombi implements Serializable {

    @Override
    public String getImageName() {
        return "/zombi.png";
    }

    public Normal(Casilla pos) {
        super(pos);
    }

    public void reaccionarAntesAtaque(Superviviente superviviente){
        super.reaccionarAlAtaque(superviviente);
    }

    @Override
    public String toString() {
        return this.getClass().toString();
    }

}
