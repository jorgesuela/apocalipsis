package Equipo;

public abstract class Equipo {
    private String nombre;

    public Equipo(String name){
        this.nombre = name;
    }

    //getters
    public String getNombre() {
        return nombre;
    }

    //setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}