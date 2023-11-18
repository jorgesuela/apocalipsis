package Activable;

import Activable.Interfaces.Corredor;
import Logica.Casilla;

public class CorredorToxico extends Toxico implements Corredor {


    public CorredorToxico(Casilla pos) {
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
