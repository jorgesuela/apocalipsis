package Activable;

import Activable.Interfaces.Caminante;
import Logica.Casilla;

public class CaminanteBerserker extends Berserker implements Caminante {

    public CaminanteBerserker(Casilla pos) {
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
