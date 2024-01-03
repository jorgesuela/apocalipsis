package Activable;

import Activable.Interfaces.Caminante;
import Logica.*;

import java.io.Serializable;

public class CaminanteNormal extends Normal implements Caminante, Serializable {


    public CaminanteNormal(Casilla pos) {
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


