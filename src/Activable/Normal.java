package Activable;

import logica.Casilla;

public class Normal extends Zombi{
    public Normal(Casilla posicion, int aguante, int nbActivaciones) {
        super(posicion, aguante, nbActivaciones);
    }

    @Override
    public void reaccionarAntesAtaque() {

    }
}
