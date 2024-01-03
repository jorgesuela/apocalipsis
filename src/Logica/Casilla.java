package Logica;

import java.io.Serializable;
import java.util.Objects;

public class Casilla implements Serializable {
    private Integer coordx;
    private Integer coordy;
    private Boolean buscado;

    public Casilla(Integer x, Integer y){
        this.coordx = x;
        this.coordy = y;
        this.buscado = false;
    }

    //getters
    public Integer getCoordx(){
        return coordx;
    }

    public Integer getCoordy(){
        return coordy;
    }

    public Boolean getBuscado(){
        return buscado;
    }
    //setters
    public void setCasilla(Integer x, Integer y){
        this.coordx = x;
        this.coordy = y;
    }

    public void setBuscado(Boolean bool){
        this.buscado = bool;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Casilla casilla = (Casilla) o;
        return Objects.equals(coordx, casilla.coordx) && Objects.equals(coordy, casilla.coordy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordx, coordy);
    }

    @Override
    public String toString() {
        return "(" + coordx + ", " + coordy + ")";
    }
}
