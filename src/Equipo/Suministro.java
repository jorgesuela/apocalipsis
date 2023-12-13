package Equipo;

import java.io.Serializable;
import java.util.Random;

public class Suministro extends Equipo implements Serializable {
    private final String valorEnergetico;
    private final String caducidad;

    public Suministro(String nombre, String valorEnergetico, String caducidad){
        super(nombre);
        this.valorEnergetico = valorEnergetico;
        this.caducidad = caducidad;
    }

    public String getCaducidad() {
        return caducidad;
    }

    public String getValorEnergetico() {
        return valorEnergetico;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo(); // Llama al método de la clase base para mostrar la información común
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

        if(nombre.equals("Judias")){ valorEnergetico = "300kcal"; caducidad = "22/07/24";}
        if(nombre.equals("Galletas")) {valorEnergetico = "200kcal"; caducidad = "06/02/25";}
        if(nombre.equals("Garbanzos")) {valorEnergetico = "250kcal"; caducidad = "30/12/23";}
        if(nombre.equals("Chocolate")) {valorEnergetico = "500kcal"; caducidad = "01/09/27";}

        return new Suministro(nombre, valorEnergetico, caducidad);
    }
}