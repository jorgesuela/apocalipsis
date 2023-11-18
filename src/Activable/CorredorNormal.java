package Activable;

import Activable.Interfaces.Corredor;
import Logica.Casilla;

public class CorredorNormal extends Normal implements Corredor {

    public CorredorNormal(Casilla pos) {
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
