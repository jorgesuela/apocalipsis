package Logica;

import java.io.Serializable;
import java.util.Random;

// esta clase se utiliza solo para lanzar un dado y saber si el ataque fallara o acertara, 
// se llamara tantas veces como dados tenga el arma
public class Dado implements Serializable {
    public static boolean lanzarDado(int valorExito) {
        Random random = new Random();
        int min = 1;
        int max = 6;
        int resultadoDado = random.nextInt(max - min + 1) + min;
        return resultadoDado >= valorExito;
    }
}