package Activable;

import Activable.Interfaces.Corredor;
import Logica.Casilla;

public class CorredorBershker extends Bershker implements Corredor {

    public CorredorBershker(Casilla pos) {
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
