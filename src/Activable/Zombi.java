package Activable;

import Logica.Casilla;
import Logica.Tablero;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Equipo.Arma;

public abstract class Zombi extends Activable implements Serializable {
    private static int contadorInstancias = 0;
    protected int heridasInfligidas;
    protected String nombre;

    public Zombi(Casilla pos) {
        super(pos);
        heridasInfligidas = 0;
        contadorInstancias++;
        this.nombre = "Z" + contadorInstancias;
    }


    public abstract String getImageName();
    public abstract int getAguante();

    public String getNombre(){
         return this.nombre;   
    }

    public int getHeridasInfligidas(){
        return this.heridasInfligidas;
    }

    public void setHeridasInfligidas(int heridasInfligidas){
        this.heridasInfligidas = heridasInfligidas;
    }


    public void reaccionarAlAtaque(Superviviente superviviente, Arma armaElegida){
        if (armaElegida.getPotencia()>=getAguante()){
            this.setMuerto();
            superviviente.setKillScore(superviviente.getKillScore() + 1); //sumamos una kill al superviviente
        }
    }

    public abstract int getNbActivaciones();

    // util para ir quitando de la lista del juego de supervivientes los que esten muertos
    public static ArrayList<Zombi> zombisVivos(ArrayList<Zombi> zombis) {
        ArrayList<Zombi> zombisVivos = new ArrayList<>();

        for (Zombi zombi : zombis) {
            if (zombi.isVivo()) {
                zombisVivos.add(zombi);
            }
        }

        return zombisVivos;
    }


    public void moverHaciaSupervivienteMasCercano(Tablero tablero, Superviviente supervivienteMasCercano) {
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
    public Superviviente encontrarSupervivienteMasCercano(List<Superviviente> supervivientes) {
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

    public void morder(Superviviente supervivienteMasCercano) {
        supervivienteMasCercano.setNbHeridas(supervivienteMasCercano.getNbHeridas() + 1);
        if (supervivienteMasCercano.getNbHeridas() < 2) {
            this.setHeridasInfligidas(this.getHeridasInfligidas() + 1);
            System.out.println("superviviente " + supervivienteMasCercano.getNombre() + " fue herido. Heridas = " + supervivienteMasCercano.getNbHeridas());
        }
        else{
            System.out.println("superviviente " + supervivienteMasCercano.getNombre() + " ha muerto.");
            supervivienteMasCercano.setMuerto();
            this.setHeridasInfligidas(this.getHeridasInfligidas() + 1);
            this.setKillScore(this.getKillScore()+ 1);
        }
    }
}
