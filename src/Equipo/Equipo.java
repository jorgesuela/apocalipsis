package Equipo;

import java.io.Serializable;

public abstract class Equipo implements Serializable {
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

    public void mostrarInfo() {
        System.out.println("Nombre del equipo: " + getNombre());
    }

}