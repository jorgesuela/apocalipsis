package Activable;

import Activable.Interfaces.Caminante;
import Logica.Casilla;

import java.io.Serializable;

public class CaminanteBerserker extends Berserker implements Caminante, Serializable {

    public CaminanteBerserker(Casilla pos) {
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
