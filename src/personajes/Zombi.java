package personajes;

public abstract class Zombi {
    protected int aguante;
    protected int nbActivaciones;


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
