package Activable;

import Activable.Interfaces.Abominacion;
import Logica.Casilla;

public class AbominacionBerserker extends Berserker implements Abominacion {

    public AbominacionBerserker(Casilla pos) {
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