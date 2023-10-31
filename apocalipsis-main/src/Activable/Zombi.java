package Activable;

import logica.Casilla;

public abstract class Zombi extends Activable {
    protected int aguante;
    protected int nbActivaciones;

    public Zombi(Casilla posicion, int aguante, int nbActivaciones){
        super(posicion);
        this.aguante = aguante;
        this.nbActivaciones = nbActivaciones;
    }


    public abstract void reaccionarAntesAtaque();

    public int getAguante() {
        return aguante;
    }

    public int getNbActivaciones() {
        return nbActivaciones;
    }

    public void setAguante(int aguante) {
        this.aguante = aguante;
    }

    public void setNbActivaciones(int nbActivaciones) {
        this.nbActivaciones = nbActivaciones;
    }
}
