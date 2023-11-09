package logica;

import Activable.Superviviente;
import Activable.Zombi;
import Equipo.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Juego {
    private ArrayList<Superviviente> supervivientes;
    private ArrayList<Zombi> zombis;
    private ArrayList<Equipo> equipo;
    private Casilla objectivo;
    private Tablero tablero;

    public void mostrarEstadisticas(){}
    public void mostrarMenuSuperviviente(){
        System.out.println("1: Moverse");
        System.out.println("2: Buscar");
        System.out.println("3: Atacar");
        System.out.println("4: Elegir arma");
        System.out.println("5: No hacer nada");
    }
    public void iniciar(){
        int contadorTurnos = 0;
        System.out.println("COMENZANDO EL JUEGO...");
        
        // cuantos supervivientes?
        int numSupervivientes = seleccionarNumSupervivientes();

        // crear el tablero en base al numero de supervivientes
        crearTablero(numSupervivientes);
        
        // aqui habria que crear los supervivientes y zombis iniciales y meterlos al tablero
        
        // aqui deberia haber un menu despues de cada turno que te permitiera elegir
        // entre avanzar al siguiente turno, reiniciar partida o finalizar partida
        
        while(true){ // esta condicion habra que cambiarla, es solo para probar funcionalidades
            //turno de todos los supervivientes
            realizarTurnoSupervivientes();
            //turno de todos los zombis
            realzarActivacionesZombis();

            contadorTurnos++;
            System.out.println("El turno " + contadorTurnos + " ha finalizado.");
        }


    }
    public void finalizar(){}
    
    public void reiniciar(){}
    
    public void realizarTurnoSupervivientes(){
        for(Superviviente superviviente: supervivientes){
            // mostrar el tablero antes de tomar acciones
            int numAcciones = superviviente.getNbAcciones();
            while(numAcciones > 0) {
                // mostrar tablero antes de acciones
                tablero.printTablero(zombis, supervivientes);
                System.out.println("¿Que debe hacer el superviviente "  + superviviente.getNombre() + "?");
                mostrarMenuSuperviviente();
                // Leer la entrada del usuario como una cadena
                Scanner scanner = new Scanner(System.in);
                String entrada = scanner.nextLine();

                switch (entrada) {
                    case "1":   superviviente.moverse(tablero);
                                numAcciones--;
                                break;

                    case "2":   superviviente.buscarEquipo();
                                numAcciones--;
                                break;

                    case "3":   numAcciones--; // aqui falta la accion buscar
                                break;

                    case "4":   numAcciones--; // aqui falta la accion elegir arma
                                break;

                    case "5":   superviviente.noHacerNada();
                                numAcciones--;
                                break;

                    default:
                        System.out.println("Por favor, selecciona una accion valida");
                        break;
                }

            }
            //mostrar tablero al acabar acciones de superviviente
            tablero.printTablero(zombis, supervivientes);
        }
    }
    public int seleccionarNumSupervivientes() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Selecciona el numero de supervivientes(de 1 a 4)");
                int numero = Integer.parseInt(scanner.nextLine());

                if (numero >= 1 && numero <= 4) {
                    return numero;
                } else {
                    System.out.println("Por favor, ingresa un número válido del 1 al 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un número entero.");
            }
        }
    }

    public void crearTablero(int numSupervivientes){
        switch (numSupervivientes) {
            case 1 -> this.tablero = new Tablero(7);
            case 2 -> this.tablero = new Tablero(8);
            case 3 -> this.tablero = new Tablero(9);
            case 4 -> this.tablero = new Tablero(10);
        }
    }

    public ArrayList<Superviviente> crearSupervivientes(int numSupervivientes){
        ArrayList<Superviviente> supervivientes = new ArrayList<Superviviente>();
        for (int i = 1; i <= numSupervivientes; i++) {
            //hay que crear el metodo para crear supervivientes por teclado!!!
            Superviviente newSuperviviente = Superviviente.crearSuperviviente();
            supervivientes.add(newSuperviviente);
        }
        return supervivientes;
    }

    public void realzarActivacionesZombis(){}
    public void generarZombis(){}

}

