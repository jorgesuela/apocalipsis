
package Activable;

import Equipo.Arma;
import Equipo.Equipo;
import Equipo.Suministro;
import Logica.Casilla;
import Logica.Tablero;

import java.io.Serializable;
import java.util.*;

public class Superviviente extends Activable implements Serializable {
    private final String nombre;
    private Integer nbAcciones;
    private final ArrayList<Equipo> equipo;
    private final ArrayList<Arma> armasActivas;
    private Integer nbHeridas;
    private Boolean estaASalvo;

    public Superviviente(Casilla posicion, String nombre){
        super(posicion);
        this.nombre = nombre;
        this.nbAcciones = 3;
        this.equipo = new ArrayList<>();
        this.armasActivas = new ArrayList<>();
        this.nbHeridas = 0;
        this.estaASalvo = false;
    }

    @Override
    public String toString() {
        return "Superviviente{" +
                "nombre='" + nombre + '\'' +
                ", nbHeridas=" + nbHeridas +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getNbAcciones() {
        return nbAcciones;
    }

    public Boolean aSalvo() {return this.estaASalvo; }

    public void setNbAcciones(Integer nbAcciones) {
        this.nbAcciones = nbAcciones;
    }

    public ArrayList<Arma> getArmasActivas() {
        return armasActivas;
    }

    public ArrayList<Equipo> getEquipo() {
        return equipo;
    }

    public Integer getNbHeridas() {
        return nbHeridas;
    }

    public void setNbHeridas(Integer nbHeridas) {
        this.nbHeridas = nbHeridas;
    }

    // este metodo habra que modificarlo para cuando haya que restar acciones extra!!!
    public void restarAcciones(int num){
        this.nbAcciones = this.nbAcciones - num;
    }

    public void resetearAcciones(){
        this.nbAcciones = 3;
    }


    // sirve para desplazar 1 superviviente por el tablero
    public void moverse(Tablero tablero, ArrayList<Zombi> listaZombis) {
        Scanner scanner = new Scanner(System.in);

        int zombisCercanos = tablero.cuantosZombi(listaZombis, this.getPosicion().getCoordx(),
                this.getPosicion().getCoordy());

        // Comprobar si el superviviente puede moverse
        if (zombisCercanos >= 4 || this.getNbAcciones() < zombisCercanos + 1) {
            System.out.println("El superviviente esta atrapado por los zombis, no puede moverse.");
            return;
        }

        // Restar acciones
        this.restarAcciones(zombisCercanos + 1);

        // Sal del bucle si se selecciona una opción válida
        String input;
        while (true) {
            System.out.println("Elige una direccion: up, down, left, right");
            input = scanner.nextLine().toLowerCase().trim();

            if (moverEnDireccion(tablero, input)) {
                System.out.println("El superviviente avanzo a la casilla" + this.getPosicion().toString());
                break;
            } else {
                System.out.println("Opcion no valida. Por favor, elige una direccion valida.");
            }
        }
    }

    // muestra por pantalla el equipo disponible
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

    // muestra por pantalla las armas equipadas
    public void armasEquipadas(){
        int contador = 1;
        System.out.println("############## ARMAS EQUIPADAS DE " + this.nombre.toUpperCase(Locale.ROOT) + " ##############");
        for (Arma armaActiva : armasActivas) {
            System.out.println("--------------------------------------" + "Arma activa " + contador + " --------------------------------------");
            armaActiva.mostrarInfo();
            contador++;
        }
        System.out.println("######################################################");
    }

    // gasta 1 accion sin realizar ningun cambio
    public void noHacerNada(){
        this.restarAcciones(1);
        System.out.println("El superviviente no hizo nada");
    }

    // este metodo es para escoger un arma de las que tiene en la bolsa el superviviente
    public Arma elegirArma(){
        Scanner scanner = new Scanner(System.in);

        // mostrar la bolsa del superviviente
        this.consultarEquipo();

        // Verificar si hay al menos un arma en el equipo
        List<Arma> armasDisponibles = equipo.stream()
                .filter(arma -> arma instanceof Arma)
                .map(arma -> (Arma) arma).toList();
        if (armasDisponibles.isEmpty()) {
            System.out.println("No tienes armas en tu equipo.");
            return null;  // Devolver null para indicar que no se puede seleccionar un arma
        }

        while(true) {
            System.out.println("Seleccione 1 arma para equipar con el numero correspondiente: ");
            int seleccion = scanner.nextInt();

            // Validar la entrada del usuario
            if (seleccion >= 1 && seleccion <= equipo.size() && equipo.get(seleccion - 1) != null && equipo.get(seleccion - 1) instanceof Arma) {
                Arma armaSeleccionada = (Arma) equipo.get(seleccion - 1);

                // Verificar si el arma ya está equipada
                if (!armasActivas.contains(armaSeleccionada)) {
                    return armaSeleccionada;
                } else {
                    System.out.println("Ya tienes equipada esa arma! Selecciona otra.");
                    return null;
                }
            } else {
                System.out.println("Selección no válida. Inténtalo de nuevo.");
            }
        }
    }

    // sirve para seleccionar como arma activa las armas
    // que encuentres por el tablero y poder atacar con ellas
    public void equiparArma(){
        // si vale null quiere decir que no hay armas validas para equipar
        Arma armaAEquipar = this.elegirArma();
        if (armaAEquipar == null){
            return;
        }
        if (armasActivas.size() < 2){
            armasActivas.add(armaAEquipar);
            System.out.println("Se ha equipado el arma "+ armaAEquipar.getNombre());
        }
        else cambiarArma(armaAEquipar);

        this.restarAcciones(1);
    }

    // si ya tienes 2 armas activas y quieres sustituir 1 de ellas por otra nueva de tu equipo:
    public void cambiarArma(Arma armaNueva){
        while(true) {
            System.out.println("Ya tienes 2 armas equipadas");
            System.out.println("Que arma quieres desequipar?");
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

    // si tienes el inventario lleno puedes deshacerte de algo que no necesites con este metodo
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

    // sirve para buscar equipo por las casilla del tablero
    public void buscarEquipo(){
        Equipo equipoNuevo;

        this.restarAcciones(1);

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

    // sirve para seleccionar la casilla de ataque y comprobar que esta es valida
    public Casilla seleccionarCasillaAtaque(Tablero tablero, Arma armaElegida) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Ingrese coordenada Y de ataque:");
            int coordX = scanner.nextInt();
            System.out.println("Ingrese coordenada X de ataque:");
            int coordY = scanner.nextInt();
            if (tablero.getCasilla(coordX, coordY) != null) {
                // sirve para comprobar que la casilla a atacar esta a rango
                if (calcularDistanciaCasillas(this.posicion, (tablero.getCasilla(coordX, coordY))) <= armaElegida.getAlcance()){
                    return tablero.getCasilla(coordX, coordY);
                }
                else{
                    System.out.println("Casilla a atacar está fuera del alcance de tu arma");
                }
            }
            else{
                System.out.println("Seleccione una casilla valida del tablero");
            }
        }

    }

    // Método para calcular la distancia entre dos casillas
    public static int calcularDistanciaCasillas(Casilla casilla1, Casilla casilla2) {
        return (int) Math.round(Math.sqrt(Math.pow((casilla2.getCoordx() - casilla1.getCoordx()), 2) + Math.pow((casilla2.getCoordy() - casilla1.getCoordy()), 2)));
    }

    // este metodo es para escoger un arma de las que tiene un superviviente equipada como activa
    public Arma elegirArmaEquipada(){
        Scanner scanner = new Scanner(System.in);

        // mostrar la bolsa del superviviente
        this.armasEquipadas();
        while(true) {
            System.out.println("Seleccione 1 arma de las activas: ");
            int seleccion = scanner.nextInt();

            // Validar la entrada del usuario
            if (seleccion >= 1 && seleccion <= armasActivas.size() && armasActivas.get(seleccion - 1) != null && armasActivas.get(seleccion - 1) != null) {
                return armasActivas.get(seleccion - 1);
            } else {
                System.out.println("Selección no válida. Inténtalo de nuevo.");
            }
        }
    }

    // este método obligatoriamente tiene que devolver una lista de los zombies eliminados para
    // poder sacarlos de la lista en la clase juego(asi evitamos el uso de var globales)
    public void atacar(Tablero tablero, ArrayList<Zombi> listaZombis) {
        int contadorMuertos = 0;
        // restar acciones
        this.restarAcciones(1);

        // Con qué arma quieres atacar
        Arma armaElegida = this.elegirArmaEquipada();

        // Qué casilla deseas atacar, y comprobar si el alcance del arma es suficiente
        Casilla casillaElegida = seleccionarCasillaAtaque(tablero, armaElegida);

        // Comprobar si hay zombi en casilla
        int numZombisEnCasilla = tablero.cuantosZombi(listaZombis, casillaElegida.getCoordx(), casillaElegida.getCoordy());

        if (numZombisEnCasilla == 0) {
            System.out.println("Ataque fallido, no hay zombies en la casilla " + casillaElegida);
            return; // Devolver lista vacía si no hay zombies
        }
        else{
            int nExitosArma = armaElegida.disparar();

            // Filtrar la lista de zombis: solo zombies de la lista que se encuentran en la casilla marcada para ataque
            // Además, ordena la lista de zombis por aguante de manera descendente
            List<Zombi> zombisEnCasillaMarcada = listaZombis.stream()
                    .filter(zombi -> zombi.getPosicion() == casillaElegida).sorted((z1, z2) ->
                            Integer.compare(z2.getAguante(), z1.getAguante())).toList();

            // El orden de eliminar zombis será siempre de los más fuertes que se puedan eliminar
            // con éxito a los menos fuertes(por eso el orden descendente de la lista).
            // OJO: EN ESTE APARTADO HABRÁ QUE REACCIONAR AL ATAQUE DE TODOS LOS ZOMBIES QUE SE HAYAN INTENTADO ELIMINAR
            for (Zombi zombi : zombisEnCasillaMarcada) {
                if (nExitosArma == 0) break; //cuando no queden tiros salimos y devolvemos la lista de zombis muertos
                zombi.reaccionarAlAtaque(this, armaElegida);
                contadorMuertos++;
                nExitosArma--; //restamos un tiro exitoso
                
            }
        }
        System.out.println("Ataque realizado, se han elimimado " + contadorMuertos + " zombie/s.");
        return;
    }
    // util para ir quitando de la lista del juego de supervivientes los que esten muertos
    public static ArrayList<Superviviente> supervivientesVivos(ArrayList<Superviviente> supervivientes) {
        ArrayList<Superviviente> supervivientesVivos = new ArrayList<>();

        for (Superviviente superviviente : supervivientes) {
            if (superviviente.isVivo()) {
                supervivientesVivos.add(superviviente);
            }
        }

        return supervivientesVivos;
    }

    // método que comprueba si el superviviente lleva algún suministro y lo devuelve
    public Equipo haySuministroEnEquipo() {
        for (Equipo equipacion : equipo) {
            if (equipacion instanceof Suministro) {
                return equipacion; // Si hay al menos un suministro, devuelve true
            }
        }
        return null;
    }

    // método que comprueba si el superviviente está en la casilla objetivo y tiene al menos 1 suministro
    public void checkIfSupervivienteASalvo(Tablero tablero){
        Equipo suministro = this.haySuministroEnEquipo();
        if (this.posicion == tablero.casillaObjetivo(tablero.getTamano())){
            if (suministro != null) {
                System.out.println(this.getNombre() + " entrego " + suministro.getNombre() + " y entro al refugio :D!");
                this.estaASalvo = true;
            }
        }
    }

}

