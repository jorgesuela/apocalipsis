package Logica;

import java.io.Serializable;
import java.util.Random;

// esta clase se utiliza solo para lanzar un dado y saber si el ataque fallara o acertara, 
// se llamara tantas veces como dados tenga el arma
public class Dado implements Serializable {
    int nDados;
    int valorExito;
    int min;
    int max;

    public Dado(int nDados, int valorExito){
        this.nDados = nDados;
        this.valorExito = valorExito;
        this.min = 1;
        this.max = 6;
    }

    public int getNumDados(){
        return this.nDados;
    }

    public int getValorExito(){
        return this.valorExito;
    }

    //devuelve el numero de dados exitosos que se han lanzado
    public int lanzarDado() {
        int exitos = 0;
        for (int i = 1; i <= this.nDados; i++) {
            Random random = new Random();
            int resultadoDado = random.nextInt(this.max - this.min + 1) + this.min;
            if(resultadoDado >= this.valorExito){
                exitos++;
                System.out.println("el resultado del dado " + i + " fue un " + resultadoDado + ", ACIERTO!!!.");
            } 
            else System.out.println("el resultado del dado " + i + " fue un " + resultadoDado + ", FALLO!!!.");
        }
        return exitos;
    }    

}