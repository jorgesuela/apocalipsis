package Activable;

import Activable.Interfaces.Caminante;
import Equipo.Arma;
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
    public void reaccionarAlAtaque(Superviviente superviviente, Arma armaElegida) {
        super.reaccionarAlAtaque(superviviente, armaElegida);
    }
}


