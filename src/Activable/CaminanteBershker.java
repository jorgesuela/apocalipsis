package Activable;

import Activable.Bershker;
import Activable.Interfaces.Caminante;
import Logica.Casilla;

public class CaminanteBershker extends Bershker implements Caminante {

    public CaminanteBershker(Casilla pos) {
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
