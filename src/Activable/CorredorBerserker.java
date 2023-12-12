package Activable;

import Activable.Interfaces.Corredor;
import Logica.Casilla;

public class CorredorBerserker extends Berserker implements Corredor {

    public CorredorBerserker(Casilla pos) {
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
