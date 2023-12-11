package Logica;

import Activable.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Juego {
    private ArrayList<Superviviente> supervivientes;
    private ArrayList<Zombi> zombis;
    private Casilla objetivo;
    public Tablero tablero;

    public Juego() {
        this.supervivientes = new ArrayList<>();
        this.zombis = new ArrayList<>();
    }

    public void mostrarEstadisticas(){}

    public Tablero getTablero() {
        return tablero;
    }

    public void mostrarMenuSuperviviente(){
        System.out.println("######ACCIONES CON COSTE######");
        System.out.println("1: Moverse");
        System.out.println("2: Buscar");
        System.out.println("3: Atacar");
        System.out.println("4: Elegir arma");
        System.out.println("5: No hacer nada");
        System.out.println("######ACCIONES SIN COSTE######");
        System.out.println("6: Consultar equipo");
        System.out.println("7: Consultar armas equipadas");
        System.out.println("######OPCIONES DE PARTIDA######");
        System.out.println("0: finalizar la partida");
        System.out.println("10: reiniciar la partida");
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public void iniciar(){
        int contadorTurnos = 1;
        System.out.println("COMENZANDO EL JUEGO...");

        // cuantos supervivientes?
        int numSupervivientes = seleccionarNumSupervivientes();

        // crear el tablero en base al numero de supervivientes
        crearTablero(numSupervivientes);

        // aqui creamos los supervivientes iniciales y los metemos al tablero
        this.supervivientes = crearSupervivientes(numSupervivientes);

        // cual sera la casilla objetivo?
        objetivo = tablero.casillaObjetivo(tablero.getTamano());

        // aqui deberia haber un menu despues de cada turno que te permitiera elegir
        // entre avanzar al siguiente turno, reiniciar partida o finalizar partida

        //esta condicion while true habra que cambiarla, es solo para probar funcionalidades!!!!!
        //deberia ser o que los supervivientes hayan ganado o que hayan muerto
        while(Superviviente.supervivientesVivos(supervivientes).size() != 0){
            //meter zombis al tablero por cada turno que pase
            zombis.addAll(this.crearTandaZombis(supervivientes.size(), contadorTurnos));
            //turno de todos los supervivientes
            realizarTurnoSupervivientes();
            //turno de todos los zombis
            realizarActivacionesZombis();

            contadorTurnos++;
            System.out.println(" ");
            System.out.println("----------------------------------------");
            System.out.println("EL TURNO " + contadorTurnos + " HA FINALIZADO.");
            System.out.println("----------------------------------------");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");

        }


    }

    public void finalizar() {
        System.out.println("¡La partida ha finalizado!");
        mostrarEstadisticas();
        System.exit(0);  // Esto finaliza la ejecución del programa
    }

    public void reiniciar() {
        System.out.println("Reiniciando la partida...");
        supervivientes.clear();  // Limpiar la lista de supervivientes
        zombis.clear();  // Limpiar la lista de zombis
        iniciar();  // Volver a iniciar el juego
    }

    public void realizarTurnoSupervivientes(){
        ArrayList<Superviviente> supervivientesVivos = Superviviente.supervivientesVivos(supervivientes);
        for(Superviviente superviviente: supervivientesVivos){
            // mostrar el tablero antes de tomar acciones
            while(superviviente.getNbAcciones() > 0) {
                // mostrar tablero antes de acciones
                tablero.printTablero(zombis, supervivientesVivos, objetivo);
                System.out.println("Que debe hacer el superviviente "  + superviviente.getNombre() + "?");
                System.out.println("Te quedan " + superviviente.getNbAcciones() + " acciones.");
                mostrarMenuSuperviviente();
                // Leer la entrada del usuario como una cadena
                Scanner scanner = new Scanner(System.in);
                String entrada = scanner.nextLine();

                switch (entrada) {
                    case "0" -> this.finalizar();
                    case "1" -> superviviente.moverse(tablero, zombis);
                    case "2" -> superviviente.buscarEquipo();
                    case "3" -> {
                        List<Zombi> zombisEliminados = superviviente.atacar(tablero, zombis);
                        zombis.removeAll(zombisEliminados); // Eliminar de lista zombis los que hayan muerto
                    }
                    case "4" -> superviviente.equiparArma();
                    case "5" -> superviviente.noHacerNada();
                    case "6" -> superviviente.consultarEquipo();
                    case "7" -> superviviente.armasEquipadas();
                    case "10" -> this.reiniciar();
                    default -> System.out.println("Por favor, selecciona una accion valida");
                }

            }
            //mostrar tablero al acabar acciones de superviviente
            tablero.printTablero(zombis, Superviviente.supervivientesVivos(supervivientes), objetivo);
            //después del turno de cada superviviente, se reestablecen sus acciones a 5
            superviviente.resetearAcciones();
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
                System.out.println("Por favor, ingresa un número entero del 1 al 4.");
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
        ArrayList<Superviviente> supervivientes = new ArrayList<>();
        for (int i = 1; i <= numSupervivientes; i++) {
            //hay que crear el metodo para crear supervivientes por teclado!!!
            Superviviente newSuperviviente = new Superviviente(tablero.getCasilla(0,0), "S"+i);
            supervivientes.add(newSuperviviente);
        }
        return supervivientes;
    }

    public void realizarActivacionesZombis() {
        for (Zombi zombi : zombis) {
            for (int i = 1; i <= zombi.getNbActivaciones(); i++) {
                // consigue la lista de supervivientes vivos en caso de que un zombi
                // de 2 activaciones mate a 1 superviviente en su primera accion
                ArrayList<Superviviente> supervivientesVivos = Superviviente.supervivientesVivos(supervivientes);
                // Obtener el superviviente más cercano
                Superviviente supervivienteMasCercano = zombi.encontrarSupervivienteMasCercano(supervivientesVivos);

                // Verificar si el zombi está en la misma casilla que el superviviente
                if (supervivienteMasCercano != null && zombi.getPosicion().equals(supervivienteMasCercano.getPosicion())) {
                    // Si están en la misma casilla, morder al superviviente
                     zombi.morder(supervivienteMasCercano);
                } else {
                    // Si no están en la misma casilla, mover hacia el superviviente más cercano
                    zombi.moverHaciaSupervivienteMasCercano(tablero, supervivientes, supervivienteMasCercano);
                }
            }
        }
    }

    public ArrayList<Zombi> crearTandaZombis(int numberSupervivientes, int contadorTurnos){
        ArrayList<Zombi> zombis = new ArrayList<>();
        int nbZombies;
        //si es inicio de partida -> 3 zombis x superviviente
        if (contadorTurnos == 1) nbZombies = numberSupervivientes * 3;
        //si no es inicio de partida -> 1 zombi x superviviente
        else nbZombies = numberSupervivientes;

        for (int i =0 ; i< nbZombies ; i++){
            zombis.add(createZombi());
        }
        return zombis;
    }

    private Zombi createZombi() {
        int tamanoTablero = tablero.getTamano();


        int coordX = new Random().nextInt(tamanoTablero-1);
        int coordY = new Random().nextInt(tamanoTablero-1);

        if(coordX==0){coordX++;}
        if(coordY==0){coordY++;}

        int i = new Random().nextInt(100);
        int j = new Random().nextInt(1,3);

        if (i<=60){//Caminante
            switch (j){
                case 1 : return new CaminanteToxico(tablero.getCasilla(coordX, coordY));
                case 2 : return new CaminanteNormal(tablero.getCasilla(coordX, coordY));
                case 3 : return new CaminanteBershker(tablero.getCasilla(coordX, coordY));
            }
        }else if (i<=90){//Corredor
            switch (j){
                case 1 : return new CorredorNormal(tablero.getCasilla(coordX, coordY));
                case 2 : return new CorredorToxico(tablero.getCasilla(coordX, coordY));
                case 3 : return new CorredorBershker(tablero.getCasilla(coordX, coordY));
            }
        }else{//Abominacion
            switch (j){
                case 1 : return new AbominacionNormal(tablero.getCasilla(coordX, coordY));
                case 2 : return new AbominacionBershker(tablero.getCasilla(coordX, coordY));
                case 3 : return new AbominacionToxico(tablero.getCasilla(coordX, coordY));
            }
        }
        return null;
    }

}
