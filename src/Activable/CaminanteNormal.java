package Activable;

import Activable.Interfaces.Caminante;
import Logica.*;

public class CaminanteNormal extends Normal implements Caminante {


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
    public void reaccionarAntesAtaque() {
        super.reaccionarAntesAtaque();
    }
}
