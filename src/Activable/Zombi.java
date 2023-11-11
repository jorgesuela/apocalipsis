package Activable;

import logica.Casilla;
import logica.Tablero;

import java.util.ArrayList;

public abstract class Zombi extends Activable {
    protected int aguante;
    protected int nbActivaciones;

    public Zombi(Casilla posicion, int aguante, int nbActivaciones){
        super(posicion);
        this.aguante = aguante;
        this.nbActivaciones = nbActivaciones;
    }


    public abstract void reaccionarAntesAtaque();

    public int getAguante() {
        return aguante;
    }

    public int getNbActivaciones() {
        return nbActivaciones;
    }

    public void setAguante(int aguante) {
        this.aguante = aguante;
    }

    public void setNbActivaciones(int nbActivaciones) {
        this.nbActivaciones = nbActivaciones;
    }

    public void moverHaciaSupervivienteMasCercano(Tablero tablero, ArrayList<Superviviente> supervivientes, Superviviente supervivienteMasCercano) {
        if (supervivienteMasCercano != null) {
            // Calcular la dirección hacia el superviviente más cercano
            String direccion = calcularDireccionHaciaSuperviviente(supervivienteMasCercano);

            // Mover el zombi en la dirección calculada
            moverEnDireccion(tablero, direccion);
        }
    }

    // Método para calcular la dirección hacia un superviviente
    private String calcularDireccionHaciaSuperviviente(Superviviente superviviente) {
        // Calcular la diferencia en coordenadas entre el zombi y el superviviente en el eje X
        int deltaX = superviviente.getPosicion().getCoordx() - getPosicion().getCoordx();
        // Calcular la diferencia en coordenadas entre el zombi y el superviviente en el eje Y
        int deltaY = superviviente.getPosicion().getCoordy() - getPosicion().getCoordy();

        // verifica si diferencia coordenadas en el eje X es mayor que en el eje Y
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            // Si es así, retornar "down" si deltaX es positivo, de lo contrario, retornar "up"
            return (deltaX > 0) ? "down" : "up";
        } else {
            // Si la diferencia en coordenadas en el eje Y es mayor o igual que la diferencia en el eje X,
            // retornar "right" si deltaY es positivo, de lo contrario, retornar "left"
            return (deltaY > 0) ? "right" : "left";
        }
    }

    // Método para encontrar al superviviente más cercano
    public Superviviente encontrarSupervivienteMasCercano(ArrayList<Superviviente> supervivientes) {
        double distanciaMinima = Double.MAX_VALUE;
        Superviviente supervivienteMasCercano = null;

        for (Superviviente superviviente : supervivientes) {
            double distancia = calcularDistanciaEntreCasillas(getPosicion(), superviviente.getPosicion());

            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                supervivienteMasCercano = superviviente;
            }
        }

        return supervivienteMasCercano;
    }

    // Método para calcular la distancia euclidiana entre dos casillas
    private double calcularDistanciaEntreCasillas(Casilla casilla1, Casilla casilla2) {
        return Math.sqrt(Math.pow(casilla1.getCoordx() - casilla2.getCoordx(), 2) +
                Math.pow(casilla1.getCoordy() - casilla2.getCoordy(), 2));
    }

}
