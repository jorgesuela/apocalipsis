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

        return new Arma(nombreArma, potencia, alcance, valorExito, numDados);
    }

    // pone nombre al arma dependiendo de sus caracteristicas
    private static String construirNombreArma(int potencia, int alcance, int valorExito, int numDados) {
        StringBuilder nombre = new StringBuilder();

        nombre.append(obtenerDescripcion(potencia, "mala", "estandar", "potente"));
        nombre.append(obtenerDescripcion(alcance, ", cuerpo a cuerpo", ", de corto alcance", ", de medio alcance", ", de largo alcance"));
        nombre.append(obtenerDescripcion(valorExito, ", exito con 4", ", exito con 5"));
        nombre.append(obtenerDescripcion(numDados, " y de disparo unico", " y de disparo doble", " y de disparo triple"));

        return nombre.toString();
    }

    private static String obtenerDescripcion(int valor, String... descripciones) {
        int index = Math.min(valor - 1, descripciones.length - 1);
        return descripciones[index];
    }

    public int lanzarDado(){
        int nExitos = 0;
        Random random = new Random();
        for (int i = 0; i < this.numDados; i++) {
            int min = 1;
            int max = 6;
            int randomDados = random.nextInt(max - min + 1) + min;
            if (randomDados >= this.valorExito) {
                System.out.println("dado numero " + (i+1) + " = Exito");
                nExitos++;
            }
            else System.out.println("dado numero " + (i+1) + " = Fallo");
        }
        return nExitos;
    }


}

