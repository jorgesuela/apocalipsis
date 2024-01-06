package Activable;

import Activable.Interfaces.Caminante;
import Activable.Toxico;
import Logica.Casilla;

import java.io.Serializable;

public class CaminanteToxico extends Toxico implements Caminante, Serializable {

    public CaminanteToxico(Casilla pos) {
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
    public void reaccionarAlAtaque(Superviviente superviviente) {
        super.reaccionarAlAtaque(superviviente);
    }
}
