package Activable;

import Activable.Interfaces.Abominacion;
import Logica.Casilla;

public class AbominacionToxico extends Toxico implements Abominacion {

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
