package Activable;

import Logica.Casilla;

import java.io.Serializable;

import Equipo.Arma;

public abstract class Normal extends Zombi implements Serializable {

    @Override
    public String getImageName() {
        return "/zombi.png";
    }

    public Normal(Casilla pos) {
        super(pos);
    }

    public void reaccionarAlAtaque(Superviviente superviviente, Arma armaElegida) {
        super.reaccionarAlAtaque(superviviente, armaElegida);
    }

    @Override
    public String toString() {
        return this.getClass().toString();
    }

}
