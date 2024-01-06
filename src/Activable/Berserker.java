package Activable;

import Logica.Casilla;

import java.io.Serializable;

public abstract class Berserker extends Zombi implements Serializable {
    public Berserker(Casilla pos) {
        super(pos);
    }


    @Override
    public String getImageName() {
        return "/zombi_ab.png";
    }

    /*public Bershker(Casilla posicion, int aguante, int nbActivaciones) {
        super(posicion, aguante, nbActivaciones);
    }*/

    @Override
    public void reaccionarAlAtaque(Superviviente superviviente) {
        if (superviviente.elegirArmaEquipada().getAlcance()==0){
            super.reaccionarAlAtaque(superviviente);
        }
    }
    @Override
    public String toString() {
        return this.getClass().toString();
    }
}
