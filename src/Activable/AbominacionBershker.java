package Activable;

import Activable.Interfaces.Abominacion;
import Logica.Casilla;

public class AbominacionBershker extends Bershker implements Abominacion {

    public AbominacionBershker(Casilla pos) {
        super(pos);
    }

    @Override
    public int getAguante() {
        return AGUANTE;
    }

    @Override
    public int getNbActivaciones() {
        return ACTIVACIONES;
    }
}
