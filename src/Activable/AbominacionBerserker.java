package Activable;

import Activable.Interfaces.Abominacion;
import Logica.Casilla;

import java.io.Serializable;

public class AbominacionBerserker extends Berserker implements Abominacion, Serializable {

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
    @Override
    public void reaccionarAntesAtaque(Superviviente superviviente) {
        super.reaccionarAntesAtaque(superviviente);
    }
}