package Activable;

import Activable.Interfaces.Abominacion;
import Logica.Casilla;

import java.io.Serializable;

public class AbominacionNormal extends Normal implements Abominacion, Serializable {


    public AbominacionNormal(Casilla pos) {
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
