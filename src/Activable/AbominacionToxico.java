package Activable;

import Activable.Interfaces.Abominacion;
import Logica.Casilla;

import java.io.Serializable;

public class AbominacionToxico extends Toxico implements Abominacion, Serializable {

    public AbominacionToxico(Casilla pos) {
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
