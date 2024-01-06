package Activable;

import Activable.Interfaces.Corredor;
import Equipo.Arma;
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
    public void reaccionarAlAtaque(Superviviente superviviente, Arma armaElegida) {
        super.reaccionarAlAtaque(superviviente, armaElegida);
    }
}
