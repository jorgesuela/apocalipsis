package Activable;

import Logica.Casilla;

import java.io.Serializable;

public abstract class Berserker extends Zombi implements Serializable {
    public Berserker(Casilla pos) {
        super(pos);
    }


    /*public Bershker(Casilla posicion, int aguante, int nbActivaciones) {
        super(posicion, aguante, nbActivaciones);
    }*/

    @Override
    public void reaccionarAntesAtaque(Superviviente superviviente) {
        if (superviviente.elegirArmaEquipada().getAlcance()==0){
            super.reaccionarAntesAtaque(superviviente);
        }
    }
}
