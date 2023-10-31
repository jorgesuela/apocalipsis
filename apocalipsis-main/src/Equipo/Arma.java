package Equipo;

import java.util.Random;

public class Arma extends Equipo {
    private Integer potencia;
    private Integer alcance;
    private Integer valorExito;
    private Integer numDados;

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

    public void mostrarInfo(){
        System.out.println("ARMA");
        System.out.println("nombre: " + getNombre());
        System.out.println("potencia: " + getPotencia().toString());
        System.out.println("alcance: " + getAlcance().toString());
        System.out.println("valExito: " + getValorExito());
        System.out.println("nDados: " + getNumDados());
    }

    public static Arma generarArmaAleatoria(){
        String nombreArma = "";
        int potencia = 0;
        int alcance = 0;
        int valorExito = 0;
        int numDados = 0;
        Random random = new Random();
        int min = 1;
        int max = 100;

        // POTENCIA DE ARMA ENCONTRADA
        int randomPotencia = random.nextInt(max - min + 1) + min;
        if (randomPotencia < 50) potencia = 1; nombreArma = "arma mala"; //50 % potencia 1 en arma encontrada
        if ((randomPotencia >= 50) && (randomPotencia < 90) ) potencia = 2; nombreArma = "arma estandar"; //40 % potencia 2 en arma encontrada
        if (randomPotencia >= 90) potencia = 3; nombreArma = "arma potente"; //10 % potencia 3 en arma encontrada


        // ALCANCE DE ARMA ENCONTRADA
        int randomAlcance = random.nextInt(max - min + 1) + min;
        if (randomAlcance < 20) alcance = 0; nombreArma += ", cuerpo a cuerpo";//20 % alcance 0 en arma encontrada
        if ((randomAlcance >= 20) && (randomAlcance < 50)) alcance = 1; nombreArma += ", de alcance 1"; //30 % alcance 1 en arma encontrada
        if ((randomAlcance >= 50) && (randomAlcance < 85)) alcance = 2; nombreArma += ", de alcance 2";//35 % alcance 2 en arma encontrada
        if (randomAlcance >= 85) alcance = 3; nombreArma += ", de alcance 3"; //15 % alcance 3 en arma encontrada

        // VALOR EXITO DE ARMA ENCONTRADA
        int randomExito = random.nextInt(max - min + 1) + min;
        if (randomExito < 40) valorExito = 4; nombreArma += ", exito con 4";//40 % exito con 4 en arma encontrada
        if (randomExito >= 40) valorExito = 5; nombreArma += ", exito con 5"; //60 % exito con 5 en arma encontrada

        // NUMDADOS DE ARMA ENCONTRADA
        int randomDados = random.nextInt(max - min + 1) + min;
        if (randomDados < 30) numDados = 1; nombreArma += " y de disparo unico";//30 % 1 dado en arma encontrada
        if ((randomDados >= 30) && (randomDados < 85)) numDados = 2; nombreArma += " y de disparo doble"; //55 % 2 dados en arma encontrada
        if (randomDados >= 85) numDados = 3; nombreArma += "y de disparo triple"; //15 % 3 dados en arma encontrada

        Arma armaEncontrada = new Arma(nombreArma, potencia, alcance, valorExito, numDados);

        //
        return armaEncontrada;
    }

}

