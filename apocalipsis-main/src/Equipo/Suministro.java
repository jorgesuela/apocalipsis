package Equipo;

import java.util.Random;

public class Suministro extends Equipo {
    private String valorEnergetico;
    private String caducidad;

    public Suministro(String nombre, String valorEnergetico, String caducidad){
        super(nombre);
        this.valorEnergetico = valorEnergetico;
        this.caducidad = caducidad;
    }

    public String getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(String caducidad) {
        this.caducidad = caducidad;
    }

    public String getValorEnergetico() {
        return valorEnergetico;
    }

    public void setValorEnergetico(String valorEnergetico) {
        this.valorEnergetico = valorEnergetico;
    }

    public void mostrarInfo(){
        System.out.println("nombre: " + getNombre());
        System.out.println("valor energetico: " + getValorEnergetico());
        System.out.println("caducidad: " + getCaducidad());
    }

    public static Suministro generarSuministroAleatorio(){
        String[] alimentos = {"Judias", "Galletas", "Garbanzos", "Chocolate"};
        String valorEnergetico = "0kcal";
        String caducidad = "";
        Random random = new Random();
        int indiceAleatorio = random.nextInt(alimentos.length);

        // Seleccionar un elemento al azar
        String nombre = alimentos[indiceAleatorio];

        if(nombre == "Judias") valorEnergetico = "300kcal"; caducidad = "22/07/24";
        if(nombre == "Galletas") valorEnergetico = "200kcal"; caducidad = "06/02/25";
        if(nombre == "Garbanzos") valorEnergetico = "250kcal"; caducidad = "30/12/23";
        if(nombre == "Chocolate") valorEnergetico = "500kcal"; caducidad = "01/09/27";

        Suministro nuevoSuministro = new Suministro(nombre, valorEnergetico, caducidad);
        return nuevoSuministro;
    }
}