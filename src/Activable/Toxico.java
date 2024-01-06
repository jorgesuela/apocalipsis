package Activable;

import Logica.Casilla;

import java.io.Serializable;

public abstract class Toxico extends Zombi implements Serializable {

    public Toxico(Casilla pos) {
        super(pos);
    }


    @Override
    public String getImageName() {
        return "/zombi_tox.png";
    }

    @Override
    public void reaccionarAntesAtaque(Superviviente superviviente) {
        if(superviviente.getPosicion().equals(super.posicion)){
            superviviente.setNbHeridas(+1);
            System.out.println(superviviente.toString() +" fue herido. Fue herido "+superviviente.getNbHeridas()+"veces en total " );
        }
        super.reaccionarAntesAtaque(superviviente);
    }

    @Override
    public String toString() {
        return this.getClass().toString();
    }
}
