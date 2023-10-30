package personajes;

import logica.Casilla;

public abstract class Activables {
    protected Casilla position;

    public Casilla getPosition(){return null;}
    public void atacar(Activables activables){}
    public void moverse(){}
    public void activarse(){}
}
