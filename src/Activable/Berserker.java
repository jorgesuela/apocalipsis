package Activable;

import Logica.Casilla;

public abstract class Berserker extends Zombi {
    public Berserker(Casilla pos) {
        super(pos);
    }


    /*public Bershker(Casilla posicion, int aguante, int nbActivaciones) {
        super(posicion, aguante, nbActivaciones);
    }*/

    @Override
    public void reaccionarAntesAtaque() {

    }
}
