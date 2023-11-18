package Activable;

import Logica.Casilla;

public abstract class Bershker extends Zombi {
    public Bershker(Casilla pos) {
        super(pos);
    }


    /*public Bershker(Casilla posicion, int aguante, int nbActivaciones) {
        super(posicion, aguante, nbActivaciones);
    }*/

    @Override
    public void reaccionarAntesAtaque() {

    }
}
