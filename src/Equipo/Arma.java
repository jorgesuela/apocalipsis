package Equipo;

import java.util.Random;

public class Arma extends Equipo {
    private final Integer potencia;
    private final Integer alcance;
    private final Integer valorExito;
    private final Integer numDados;

    public Arma(String nombre, Integer potencia, Integer alcance, Integer valorExito, Integer numDados){
        super(nombre);
        this.potencia = potencia;
        this.alcance = alcance;
        this.numDados = numDados;
        this.valorExito = valorExito;
    }

    //getters

    @Override
    public String getNombre() {
        return super.getNombre();
    }

    public Integer getAlcance() {
        return alcance;
    }

    public Integer getNumDados() {
        return numDados;
    }

    public Integer getPotencia() {
        return potencia;
    }

    public Integer getValorExito() {
        return valorExito;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo(); // Llama al método de la clase base para mostrar la información común
        System.out.println("potencia: " + getPotencia().toString());
        System.out.println("alcance: " + getAlcance().toString());
        System.out.println("valExito: " + getValorExito());
        System.out.println("nDados: " + getNumDados());
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
            nombreArma += " mala";
        } else if (randomPotencia < 90) {
            potencia = 2;
            nombreArma += " estandar";
        } else {
            potencia = 3;
            nombreArma += " potente";
        }

        // ALCANCE DE ARMA ENCONTRADA
        int randomAlcance = random.nextInt(max - min + 1) + min;
        if (randomAlcance < 20) {
            alcance = 0;
            nombreArma += ", cuerpo a cuerpo";
        } else if (randomAlcance < 50) {
            alcance = 1;
            nombreArma += ", de corto alcance";
        } else if (randomAlcance < 85) {
            alcance = 2;
            nombreArma += ", de medio alcance";
        } else {
            alcance = 3;
            nombreArma += ", de largo alcance";
        }

        // VALOR EXITO DE ARMA ENCONTRADA
        int randomExito = random.nextInt(max - min + 1) + min;
        if (randomExito < 40) {
            valorExito = 4;
            nombreArma += ", exito con 4";
        } else {
            valorExito = 5;
            nombreArma += ", exito con 5";
        }

        // NUMDADOS DE ARMA ENCONTRADA
        int randomDados = random.nextInt(max - min + 1) + min;
        if (randomDados < 30) {
            numDados = 1;
            nombreArma += " y de disparo unico";
        } else if (randomDados < 85) {
            numDados = 2;
            nombreArma += " y de disparo doble";
        } else {
            numDados = 3;
            nombreArma += " y de disparo triple";
        }

        return new Arma(nombreArma, potencia, alcance, valorExito, numDados);
    }

    public Boolean lanzarDado(){
        Random random = new Random();
        int min = 1;
        int max = 6;
        int randomDados = random.nextInt(max - min + 1) + min;
        return randomDados >= this.valorExito; // true si acertó | false si falló
    }


}

