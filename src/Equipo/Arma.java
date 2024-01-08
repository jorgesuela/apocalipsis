package Equipo;

import java.io.Serializable;
import java.util.Random;

import Logica.Dado;

public class Arma extends Equipo implements Serializable {
    private final Integer potencia;
    private final Integer alcance;
    private final Dado dado;

    public Arma(String nombre, Integer potencia, Integer alcance, Dado dado){
        super(nombre);
        this.potencia = potencia;
        this.alcance = alcance;
        this.dado = dado;
    }

    //getters

    @Override
    public String getNombre() {
        return super.getNombre();
    }

    public Integer getAlcance() {
        return alcance;
    }

    public Integer getPotencia() {
        return potencia;
    }

    public Dado getDado(){
        return this.dado;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo(); // Llama al método de la clase base para mostrar la información común
        System.out.println("potencia: " + getPotencia().toString());
        System.out.println("alcance: " + getAlcance().toString());
        System.out.println("dado -> " + this.dado.getNumDados() + " dados y exito con " + this.dado.getValorExito());
    }

    public static Arma generarArmaAleatoria() {
        String nombreArma = "Arma";
        int potencia;
        int alcance;
        int valorExito;
        int numDados;
        Random random = new Random();
        int min = 1;
        int max = 100;

        // POTENCIA DE ARMA ENCONTRADA
        int randomPotencia = random.nextInt(max - min + 1) + min;
        if (randomPotencia < 50) {
            potencia = 1;
        } else if (randomPotencia < 90) {
            potencia = 2;
        } else {
            potencia = 3;
        }

        // ALCANCE DE ARMA ENCONTRADA
        int randomAlcance = random.nextInt(max - min + 1) + min;
        if (randomAlcance < 20) {
            alcance = 0;
        } else if (randomAlcance < 50) {
            alcance = 1;
        } else if (randomAlcance < 85) {
            alcance = 2;
        } else {
            alcance = 3;
        }

        // VALOR EXITO DE ARMA ENCONTRADA
        int randomExito = random.nextInt(max - min + 1) + min;
        if (randomExito < 40) {
            valorExito = 4;
        } else {
            valorExito = 5;
        }

        // NUMDADOS DE ARMA ENCONTRADA
        int randomDados = random.nextInt(max - min + 1) + min;
        if (randomDados < 30) {
            numDados = 1;
        } else if (randomDados < 85) {
            numDados = 2;
        } else {
            numDados = 3;
        }

        nombreArma += construirNombreArma(potencia, alcance, valorExito, numDados);

        return new Arma(nombreArma, potencia, alcance, new Dado(numDados, valorExito));
    }

    // pone nombre al arma dependiendo de sus caracteristicas
    private static String construirNombreArma(int potencia, int alcance, int valorExito, int numDados) {
        StringBuilder nombre = new StringBuilder();

        nombre.append(new String[]{" mala", " estandar", " potente"}[potencia - 1]);
        nombre.append(new String[]{", cuerpo a cuerpo", ", de corto alcance", ", de medio alcance", ", de largo alcance"}[alcance]);
        nombre.append(new String[]{", exito con 4", ", exito con 5"}[valorExito - 4]);
        nombre.append(new String[]{" y de disparo unico", " y de disparo doble", " y de disparo triple"}[numDados - 1]);

        return nombre.toString();
    }

    public int disparar() {
        return dado.lanzarDado();
    }  

}

