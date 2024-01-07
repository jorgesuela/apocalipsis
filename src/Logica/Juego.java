package Logica;

import Activable.*;
import Graphics.MyInterfaceGrafica;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Juego implements Serializable {
    private ArrayList<Superviviente> supervivientes;
    private ArrayList<Zombi> zombis;
    private Casilla objetivo;
    public Tablero tablero;
    private int contadorTurnos = 1;
    MyInterfaceGrafica interfaceGrafica;

    public Juego() {
        this.supervivientes = new ArrayList<>();
        this.zombis = new ArrayList<>();
    }




    public int seleccionarNumSupervivientes() {
        while (true) {
            try {
                System.out.println("Selecciona el numero de supervivientes(de 1 a 4)");
                int entrada = obtenerEntradaUsuario();

                if (entrada >= 1 && entrada <= 4) {
                    return entrada;
                } else {
                    System.out.println("Por favor, ingresa un número válido del 1 al 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un número entero del 1 al 4.");
            }
        }
    }

    private int obtenerEntradaUsuario() {
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    private void imprimirInformacionSuperviviente(Superviviente superviviente) {
        System.out.println(superviviente.getNombre() + ":");
        if (superviviente.aSalvo()) System.out.println("- ha llegado al refugio.");
        else System.out.println("- no ha sobrevivido.");
        System.out.println("- numero de heridas = " + superviviente.getNbHeridas());
        System.out.println("- ha eliminado a " + superviviente.getKillScore() + " zombis.");
        System.out.println("- equipo:");
        for (int i = 0; i < superviviente.getEquipo().size(); i++) {
            System.out.println("   " + (i + 1) + ") " + superviviente.getEquipo().get(i).getNombre());
        }
        System.out.println("- armas activas:");
        for (int i = 0; i < superviviente.getArmasActivas().size(); i++) {
            System.out.println("   " + (i + 1) + ") " + superviviente.getArmasActivas().get(i).getNombre());
        }
    }

    private void imprimirInformacionZombi(Zombi zombi) {
        if(zombi.getHeridasInfligidas() > 0){
            System.out.println(zombi.getNombre() + ":");
            System.out.println("- ha hecho " + zombi.getHeridasInfligidas() + " heridas a superviviente/s.");
            System.out.println("- ha eliminado a " + zombi.getKillScore() + " superviviente/s.");
        } 
    }

    public void mostrarResultadosDelJuego(){
        for (Superviviente superviviente : supervivientes) {
            imprimirInformacionSuperviviente(superviviente);
        }
        for (Zombi zombi : zombis) {
            imprimirInformacionZombi(zombi);
        }
    }

    public void mostrarSuperviventesYZombis(){
        System.out.println();
        System.out.println(supervivientes.size()>1?"Superviviente :": "Supervivientes :");
        for(Superviviente s: supervivientes){System.out.print(s.getNombre()+": heridas:"+s.getNbHeridas()+"\t");}
        System.out.println(zombis.size()>1?"\nZombi : ":"\nZombis :");
        for(Zombi z: zombis){System.out.print(z.getNombre()+": "+z.toString().replace("Activable.", "").replace("class", "")+"\t");}
        System.out.println();System.out.println();

    }

    public void mostrarMenuSuperviviente(){
        interfaceGrafica.repaintPanel();
        mostrarSuperviventesYZombis();
        System.out.println("######ACCIONES CON COSTE######");
        System.out.println("1: Moverse");
        System.out.println("2: Buscar");
        System.out.println("3: Atacar");
        System.out.println("4: Elegir arma");
        System.out.println("5: No hacer nada");
        System.out.println("######ACCIONES SIN COSTE######");
        System.out.println("6: Consultar equipo");
        System.out.println("7: Consultar armas equipadas");
        System.out.println("10: Opciones de partida");

    }

    private void ejecutarTurno(int contadorTurnos) {
        zombis.addAll(crearTandaZombis(supervivientes.size(), contadorTurnos));
        realizarTurnoSupervivientes();
        realizarActivacionesZombis();
    }

    private void mostrarFinDeTurno(int contadorTurnos) {
        System.out.println(" ");
        System.out.println("----------------------------------------");
        System.out.println("EL TURNO " + contadorTurnos + " HA FINALIZADO.");
        System.out.println("----------------------------------------");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
    }

    public void iniciar() {
        // Intenta cargar el estado del juego desde un archivo existente
        Juego juegoGuardado = cargarEstado("estado_del_juego.ser");
        if (juegoGuardado != null) {
            System.out.println("HAY UNA PARTIDA GUARDADA, QUIERES CONTINUARLA?");
            System.out.println("1) SI");
            System.out.println("2) NO");
            int entrada = obtenerEntradaUsuario();
            switch (entrada) {
                case 1 -> {
                    this.supervivientes = juegoGuardado.supervivientes;
                    this.zombis = juegoGuardado.zombis;
                    this.objetivo = juegoGuardado.objetivo;
                    this.tablero = juegoGuardado.tablero;
                    this.contadorTurnos = juegoGuardado.contadorTurnos;
                }
                case 2 -> this.reiniciar();
            }
        } else {
            int numSupervivientes = seleccionarNumSupervivientes();
            crearTablero(numSupervivientes);
            supervivientes = crearSupervivientes(numSupervivientes);
            objetivo = tablero.casillaObjetivo(tablero.getTamano());
        }

        interfaceGrafica = new MyInterfaceGrafica(zombis, supervivientes, tablero.getTamano());
        interfaceGrafica.showFrame();


        System.out.println("COMENZANDO EL JUEGO...");

        while (continuarElJuego()) {
            ejecutarTurno(contadorTurnos);
            contadorTurnos++;
            mostrarFinDeTurno(contadorTurnos);
        }

        finalizar();
    }


    public void finalizar() {
        System.out.println("LA PARTIDA HA FINALIZADO!!!");
        mostrarResultadosDelJuego();
        System.exit(0);  // Esto finaliza la ejecución del programa
    }

    // para saber si el juego debe continuar o acabar
    public boolean continuarElJuego() {
        for (Superviviente superviviente : supervivientes) {
            if (superviviente.isVivo() && !(superviviente.aSalvo())) {
                return true; // Si algún superviviente no está muerto ni a salvo, el juego continua(true)
            }
        }
        return false; // Si todos los supervivientes están muertos o a salvo, el juego termina(false)
    }

    public void reiniciar() {
        System.out.println("Reiniciando la partida...");
        supervivientes.clear();  // Limpiar la lista de supervivientes
        zombis.clear();  // Limpiar la lista de zombis
        contadorTurnos = 1;
        this.borrarPartidaGuardada("estado_del_juego.ser");
        iniciar();  // Volver a iniciar el juego
    }

    public void realizarTurnoSupervivientes(){
        ArrayList<Superviviente> supervivientesVivos = Superviviente.supervivientesVivos(supervivientes);
        for(Superviviente superviviente: supervivientesVivos){
            // si el superviviente ya está a salvo, pasar al siguiente superviviente
            if (superviviente.aSalvo()) {
                continue;
            }
            // bucle hasta que superviviente sin acciones o muera
            while((superviviente.getNbAcciones() > 0) && (superviviente.isVivo())) {
                // si el superviviente ya esta a salvo salir del bucle
                if (superviviente.aSalvo()){
                    superviviente.setNbAcciones(0);
                    continue;
                }
                // mostrar tablero antes de acciones
                System.out.println(" ");
                tablero.printTablero(Zombi.zombisVivos(zombis), Superviviente.supervivientesVivos(supervivientes), objetivo);
                System.out.println(" ");
                mostrarSuperviventesYZombis();
                System.out.println(" ");
                System.out.println("Que debe hacer el superviviente "  + superviviente.getNombre() + "?");
                System.out.println("Te quedan " + superviviente.getNbAcciones() + " acciones.");
                mostrarMenuSuperviviente();

                // Leer la entrada del usuario como una cadena
                int entrada = obtenerEntradaUsuario();
                switch (entrada) {
                    case 1 -> superviviente.moverse(tablero, Zombi.zombisVivos(zombis));
                    case 2 -> superviviente.buscarEquipo();
                    case 3 -> superviviente.atacar(tablero, Zombi.zombisVivos(zombis));
                    case 4 -> superviviente.equiparArma();
                    case 5 -> superviviente.noHacerNada();
                    case 6 -> superviviente.consultarEquipo();
                    case 7 -> superviviente.armasEquipadas();
                    case 10 -> menuOpciones();
                    default -> System.out.println("Por favor, selecciona una accion valida");
                }
                // después de cada acción se comprueba si el superviviente ha logrado llegar al objetivo con el suministro
                superviviente.checkIfSupervivienteASalvo(tablero);
            

            }
            //mostrar tablero al acabar acciones de superviviente
            System.out.println(" ");
            tablero.printTablero(Zombi.zombisVivos(zombis), Superviviente.supervivientesVivos(supervivientes), objetivo);
            System.out.println(" ");

        }
        //después del turno de los supervivientes, se reestablecen sus acciones a 5
        for(Superviviente superviviente: supervivientesVivos) superviviente.resetearAcciones();
    }

    public void menuOpciones(){
        while(true) {
            mostrarMenuOpciones();
            int entrada = obtenerEntradaUsuario();
            switch (entrada) {
                case 0 -> {
                    return;
                }
                case 1 -> this.finalizar();
                case 2 -> this.reiniciar();
                case 3 -> this.guardarEstado("estado_del_juego.ser");
                case 4 -> cargarEstado("estado_del_juego.ser");
                default -> System.out.println("Por favor, selecciona una accion valida");
            }
        }
    }

    public void mostrarMenuOpciones(){
        System.out.println("#############OPCIONES#############");
        System.out.println("0: Atras");
        System.out.println("1: Finalizar partida");
        System.out.println("2: Reinicar partida");
        System.out.println("3: Guardar partida");
        System.out.println("4: Cargar partida");
    }

    public void crearTablero(int numSupervivientes){
        switch (numSupervivientes) {
            case 1 -> this.tablero = new Tablero(7);
            case 2 -> this.tablero = new Tablero(8);
            case 3 -> this.tablero = new Tablero(9);
            case 4 -> this.tablero = new Tablero(10);
        }
    }

    public void realizarActivacionesZombis() {
        System.out.println("###########################################");
        System.out.println("Los zombis se han activado!!!");
        System.out.println("###########################################");
        System.out.println(" ");
        ArrayList<Zombi> zombisVivos = Zombi.zombisVivos(zombis);
        for (Zombi zombi : zombisVivos) {
            for (int i = 1; i <= zombi.getNbActivaciones(); i++) {
                // los zombis solo iran a por supervivientes que no estén a salvo y que sigan vivos obviamente
                List<Superviviente> supervivientesObjetivo= supervivientes.stream()
                        .filter(superviviente -> !superviviente.aSalvo())
                        .filter(superviviente -> superviviente.isVivo()).toList();

                // Obtener el superviviente más cercano
                Superviviente supervivienteMasCercano = zombi.encontrarSupervivienteMasCercano(supervivientesObjetivo);

                // Verificar si el zombi está en la misma casilla que el superviviente
                if (supervivienteMasCercano != null && zombi.getPosicion().equals(supervivienteMasCercano.getPosicion())) {
                    // Si están en la misma casilla, morder al superviviente
                     zombi.morder(supervivienteMasCercano);
                } else {
                    // Si no están en la misma casilla, mover hacia el superviviente más cercano
                    zombi.moverHaciaSupervivienteMasCercano(tablero, supervivienteMasCercano);
                }
            }
        }
        System.out.println(" ");
        System.out.println(" ");
        tablero.printTablero(zombis, Superviviente.supervivientesVivos(supervivientes), objetivo);
        System.out.println(" ");
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
                case 3 : return new CaminanteBerserker(tablero.getCasilla(coordX, coordY));
            }
        }else if (i<=90){//Corredor
            switch (j){
                case 1 : return new CorredorNormal(tablero.getCasilla(coordX, coordY));
                case 2 : return new CorredorToxico(tablero.getCasilla(coordX, coordY));
                case 3 : return new CorredorBerserker(tablero.getCasilla(coordX, coordY));
            }
        }else{//Abominacion
            switch (j){
                case 1 : return new AbominacionNormal(tablero.getCasilla(coordX, coordY));
                case 2 : return new AbominacionBerserker(tablero.getCasilla(coordX, coordY));
                case 3 : return new AbominacionToxico(tablero.getCasilla(coordX, coordY));
            }
        }
        return null;
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

    public void guardarEstado(String nombreArchivo) {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            salida.writeObject(this);
            System.out.println("SE HA GUARDADO LA PARTIDA CORRECTAMENTE.");
        } catch (IOException e) {
            System.out.println("Error al guardar el estado del juego: " + e.getMessage());
        }
    }

    public static Juego cargarEstado(String nombreArchivo) {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return (Juego) entrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    public void borrarPartidaGuardada(String nombreArchivo) {
        File archivo = new File(nombreArchivo);

        // Verificar si el archivo existe
        if (archivo.exists()) {
            //borrar el archivo
            if (archivo.delete()) {
                System.out.println("Se ha borrado la partida que estaba guardada.");
            } else {
                System.out.println("Error al borrar la partida guardada.");
            }
        }
    }

}
