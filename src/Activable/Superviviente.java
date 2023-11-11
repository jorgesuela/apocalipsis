package Activable;

import Equipo.*;
import logica.Casilla;
import logica.Tablero;

import java.util.*;

public class Superviviente extends Activable {
    private String nombre;
    private Boolean vivo;
    private Integer nbAcciones;
    private ArrayList<Equipo> equipo;
    private ArrayList<Arma> armasActivas;
    private Integer killScore;
    private Integer nbHeridas;

    public Superviviente(Casilla posicion, String nombre){
        super(posicion);
        this.nombre = nombre;
        this.vivo = true;
        this.nbAcciones = 5;
        this.equipo = new ArrayList<>();
        this.armasActivas = new ArrayList<>();;
        this.killScore = 0;
        this.nbHeridas = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getVivo() {
        return vivo;
    }

    public void setVivo(Boolean vivo) {
        this.vivo = vivo;
    }

    public Integer getNbAcciones() {
        return nbAcciones;
    }

    public void setNbAcciones(Integer nbAcciones) {
        this.nbAcciones = nbAcciones;
    }

    public ArrayList<Arma> getArmasActivas() {
        return armasActivas;
    }

    public void setArmasActivas(ArrayList<Arma> armasActivas) {
        this.armasActivas = armasActivas;
    }

    public ArrayList<Equipo> getEquipo() {
        return equipo;
    }

    public Integer getKillScore() {
        return killScore;
    }

    public void setKillScore(Integer killScore) {
        this.killScore = killScore;
    }

    public Integer getNbHeridas() {
        return nbHeridas;
    }

    public void setNbHeridas(Integer nbHeridas) {
        this.nbHeridas = nbHeridas;
    }

    public void noHacerNada(){
        System.out.println("El superviviente no hizo nada");
    }

    public void consultarEquipo(){
        int contador = 1;
        System.out.println("################# BOLSA DE " + this.nombre.toUpperCase(Locale.ROOT) + " #################");
        for (Equipo equipacion : equipo) {
            System.out.println("--------------------------------------" + "Equipo " + contador + " --------------------------------------");
            equipacion.mostrarInfo();
            contador++;
        }
        System.out.println("##################################################");
    }

    // este metodo habra que modificarlo para cuando haya que restar acciones extra!!!
    public void restarAcciones(){
        this.nbAcciones = this.nbAcciones - 1;
    }

    public void resetearAcciones(){
        this.nbAcciones = 5;
    }

    public void cambiarArma(Arma armaNueva){
        while(true) {
            System.out.println("Ya tienes 2 armas equipadas");
            System.out.println("¿Qué arma quieres desequipar?");
            for (int i = 0; i < armasActivas.size(); i++) {
                System.out.println("arma " + "'" +(i + 1)+ "'" + ": " + armasActivas.get(i).getNombre());
                }

            Scanner scanner = new Scanner(System.in);
            String entrada = scanner.nextLine();

            // Convertir la entrada a un entero
            int numero;
            try {
                numero = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Ingrese un número.");
                continue; // Volver a pedir la entrada
            }

            if ((numero == 1) || (numero == 2)) {
                armasActivas.set(numero - 1, armaNueva); // Restamos 1 porque los índices comienzan en 0
                System.out.println("se ha desequipado el arma " + numero + " y se ha equipado: " + armaNueva.getNombre());
                break; // Salir del bucle cuando se ha eliminado correctamente
            } else {
                System.out.println("Selecciona un arma valida.");
            }
        }



    }

    private void mostrarArmasDisponibles() {
        int numeroArma = 1;
        for (Equipo equipacion : equipo) {
            if (equipacion instanceof Arma) {
                Arma arma = (Arma) equipacion;
                System.out.println("Arma " + numeroArma + ": " + arma.getNombre());
                numeroArma++;
            }
        }
    }

    public void equiparArma(Arma armaAEquipar){
        if (armasActivas.size() < 2){
            armasActivas.add(armaAEquipar);
            System.out.println("Se ha equipado el arma "+ armaAEquipar.getNombre());
        }
        else cambiarArma(armaAEquipar);
    }

    public void soltarEquipo() {
        while (true) {
            System.out.println("¿Qué equipo quieres soltar?");
            for (int i = 0; i < equipo.size(); i++) {
                Equipo equipacion = equipo.get(i);
                System.out.println("Equipacion " + (i + 1) + ": " + equipacion.getNombre());
            }

            // Leer la entrada del usuario como una cadena
            Scanner scanner = new Scanner(System.in);
            String entrada = scanner.nextLine();

            // Convertir la entrada a un entero
            int numero;
            try {
                numero = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no valida. Ingrese un número.");
                continue; // Volver a pedir la entrada
            }

            if (numero >= 1 && numero <= equipo.size()) {
                System.out.println("Se ha soltado el siguiente equipo: " + equipo.get(numero -1).getNombre() + ".");
                equipo.remove(numero - 1); // Restamos 1 porque los índices comienzan en 0
                break; // Salir del bucle cuando se ha eliminado correctamente
            } else {
                System.out.println("Selecciona un equipamiento valido.");
            }
        }
    }


    public void buscarEquipo(){
        Equipo equipoNuevo;
        // comprobar si ya se ha buscado equipo en esa casilla
        if (this.getPosicion().getBuscado()){ // si ya se ha buscado en esa casilla
            System.out.println("alguien ha buscado ya en esta casilla, no hay equipo");
        }
        else{ // si nadie ha buscado en esa casilla
            //marcamos como que ya esta buscada la casilla
            this.getPosicion().setBuscado(true);
            //generamos un random, 1 o 2, si 1 o 2 -> generamos Arma, si 3 -> generamos suministro
            Random random = new Random();
            int min = 1;
            int max = 3;
            int randomArmaOSuministro = random.nextInt(max - min + 1) + min;
            if (randomArmaOSuministro <= 2){
                equipoNuevo = Arma.generarArmaAleatoria();
            }
            else{
                equipoNuevo = Suministro.generarSuministroAleatorio();
            }
            System.out.println("se ha encontrado el siguiente equipo: ");
            equipoNuevo.mostrarInfo();

            // comprobar si equipo ya esta completo
            if (equipo.size() == 5){ // ya no puedes llevar mas equipo, esta completo
                while(true) {
                    System.out.println("Equipo ya esta completo");
                    // aqui hay que dar la opcion de:
                    System.out.println("opcion 'a': dejar el objeto donde estaba"); // no coger el objeto
                    System.out.println("opcion 'b': soltar algun objeto del inventario para recogerlo");

                    // Leer la entrada del usuario como una cadena
                    Scanner scanner = new Scanner(System.in);
                    String entrada = scanner.nextLine();

                    if (Objects.equals(entrada, "a")) {
                        System.out.println("No se recogio el objeto");
                        break;
                    } else if (Objects.equals(entrada, "b")) {
                        soltarEquipo(); //soltar algo del equipo que lleves
                        equipo.add(equipoNuevo); // equipar el objeto encontrado
                        System.out.println("Se ha equipado a " + this.nombre + " con el arma encontrada");
                        break;
                    } else System.out.println("Seleccione un opcion valida.");
                }
            }
            else if (equipo.size() <= 4){ // puedes llevar mas equipo
                equipo.add(equipoNuevo); //no esta completo -> añadir equipo encontrado
                System.out.println("Se ha guardado en el inventario de " + this.nombre + " el equipo encontrado");
            }
        }


    }

    public static Superviviente crearSuperviviente(Tablero tablero) {
        Casilla posicionInicial = tablero.getCasilla(0, 0);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del superviviente:");
        String nombre = scanner.nextLine();

        System.out.println("Elige cual quieres que sea tu posicion inicial:");
        System.out.println("1: Arriba izquierda (0,0)");
        System.out.println("2: Abajo izquierda (0," + (tablero.getTamaño() - 1) + ")");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1 :    posicionInicial = tablero.getCasilla(0, 0);
                        break;
            case 2 :    posicionInicial = tablero.getCasilla(tablero.getTamaño() - 1, 0);
                        break;
        }

        // Crear y devolver el superviviente
        return new Superviviente(posicionInicial, nombre);
    }

}

