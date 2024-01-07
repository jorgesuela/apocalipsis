package Equipo;

import java.io.Serializable;
import java.util.Random;

public class Dado implements Serializable {
    public static boolean lanzarDado(int valorExito) {
        Random random = new Random();
        int min = 1;
        int max = 6;
        int resultadoDado = random.nextInt(max - min + 1) + min;
        return resultadoDado >= valorExito;
    }
}