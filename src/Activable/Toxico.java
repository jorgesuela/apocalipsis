package Activable;

import Logica.Casilla;

import java.io.Serializable;

import Equipo.Arma;

public abstract class Toxico extends Zombi implements Serializable {

    public Toxico(Casilla pos) {
        super(pos);
    }


    @Override
    public String getImageName() {
        return "/zombi_tox.png";
    }

    @Override
    public void reaccionarAlAtaque(Superviviente superviviente, Arma armaElegida) {
        if(superviviente.getPosicion().equals(super.posicion)){
            superviviente.setNbHeridas(superviviente.getNbHeridas() +1);
            this.setHeridasInfligidas(this.getHeridasInfligidas() + 1);
            System.out.println(superviviente.getNombre() +" fue herido por sangre zombi toxica. numero heridas = " + superviviente.getNbHeridas()+"." );
            if (superviviente.getNbHeridas() >= 2){
                superviviente.setMuerto();
                //killscore de zombi + 1
                this.setKillScore(this.getKillScore() + 1);
                this.setHeridasInfligidas(this.getHeridasInfligidas() + 1);
                System.out.println("superviviente " + superviviente.getNombre() + " ha muerto.");
            } 
        }
        super.reaccionarAlAtaque(superviviente, armaElegida);
    }

    @Override
    public String toString() {
        return this.getClass().toString();
    }
}
