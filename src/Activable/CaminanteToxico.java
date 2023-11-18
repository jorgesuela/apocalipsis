package Activable;

import Activable.Interfaces.Caminante;
import Activable.Toxico;
import Logica.Casilla;

public class CaminanteToxico extends Toxico implements Caminante {

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
}
