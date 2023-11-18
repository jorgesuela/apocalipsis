package Logica;

public class Casilla {
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
    public String toString() {
        return "(" + coordx + ", " + coordy + ")";
    }
}
