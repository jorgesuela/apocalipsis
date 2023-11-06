package Activable;

import logica.Casilla;

public class Toxico extends Zombi{
    public Toxico(Casilla posicion, int aguante, int nbActivaciones) {
        super(posicion, aguante, nbActivaciones);
    }

    @Override
    public void reaccionarAntesAtaque() {

    }
}
