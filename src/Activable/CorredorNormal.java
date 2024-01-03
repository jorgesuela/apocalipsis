package Activable;

import Activable.Interfaces.Corredor;
import Logica.Casilla;

import java.io.Serializable;

public class CorredorNormal extends Normal implements Corredor, Serializable {

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

    @Override
    public void reaccionarAntesAtaque(Superviviente superviviente) {
        super.reaccionarAntesAtaque(superviviente);
    }
}
