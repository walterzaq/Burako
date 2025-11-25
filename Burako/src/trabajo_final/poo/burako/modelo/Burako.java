package trabajo_final.poo.burako.modelo;
import trabajo_final.poo.burako.observer.Observador;
import trabajo_final.poo.burako.observer.Observable;
import java.util.*;

public class Burako implements Observable {
    private ArrayList<Jugador> jugadores;
    private int[] puntajeMasAlto; //Todavia no llegue a darle utilidad
    private Mazo mazo;
    private Pozo pozo;

    //Estos atributos se usan exclusivamente con Observer
    private ArrayList<Observador> observadores;
    private Jugador jugadorActual;
    private int opcion1;
    private int opcion2;

    public Burako() {
        puntajeMasAlto = new int[5];
        jugadores = new ArrayList<>();
        mazo = new Mazo();
        pozo = new Pozo();
    }

    //Estos 3 metodos de abajo son para usar con Observer.

    public Jugador getJugadorActual(){
        return jugadorActual;
    }

    public void setOpcion1(int opcion){
        opcion1 = opcion;
    }

    public void setOpcion2(int opcion){
        opcion2 = opcion;
    }

    public void setJugadores(int cantidad){
        Jugador jugador;
        for(int i = 1;i<=cantidad;i++){
            jugador = new Jugador("Jugador " + i);
            jugadores.add(jugador);
        }
    }

    public void iniciarTurno(Jugador jugador){
        int opcion1 = 2;
        int opcion2;

        while (opcion1!=3){
            //Te diria que primero comiences por ir pidiendo ingreso de datos y mostrando cosas en pantalla desde el mismo modelo. Solo para probar que lo que vas haciendo este bien. Luego te fijas
            //como implementar observer y mvc.

            System.out.println("Es el turno de " + jugador + "!!");
            jugador.mostrarDatosJugador();
            //Nota: tambien necesita ver las fichas del pozo.
            System.out.println("1) Tomar Ficha del Mazo/Pozo");
            System.out.println("2) Armar Jugada");
            System.out.println("3) Depositar Ficha en el Pozo");
            System.out.print("Ingrese una opcion:");
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            opcion1 = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\n\n\n\n\n\n");


            if(opcion1==1){
                //Los menus internos TIENEN que usar una variable de opcion distinta.
                if(jugador.verificarSiTomoFicha() == false){
                    System.out.println("Desea tomar fichas del Mazo o del Pozo?");
                    System.out.println("1) Tomar Ficha del Mazo");
                    System.out.println("2) Tomar Fichas del Pozo");
                    opcion2 = scanner.nextInt();
                    scanner.nextLine();

                    ConjuntoTomable conjunto;
                    if(opcion2==1){conjunto = mazo;}
                    else{conjunto = pozo;}
                    jugador.tomarFicha(conjunto);
                }
                else{System.out.println("Error!! Ya se han tomado fichas del mazo o del pozo");}
            }
            else if (opcion1==2) {
                if(jugador.verificarSiTomoFicha() == true){
                    Juego juego = jugador.armarJuego();

                    if(juego != null){
                    System.out.println("Seleccione el tipo de jugada");
                    System.out.println("1) Bajar un Juego Nuevo");
                    System.out.println("2) Completar un Juego Existente");
                    opcion2 = scanner.nextInt();
                    scanner.nextLine();

                        if(opcion2==1){
                            jugador.bajarJuegoNuevo(juego);
                        }
                        else{
                            jugador.completarJuegoExistente(juego);
                        }
                    }

                }
                else{System.out.println("Error!! No se puede armar una jugada si no se ha levantado una ficha del mazo o del pozo");}
            }
            else if (opcion1==3){
                if(jugador.verificarSiTomoFicha() == true){
                    jugador.depositarFicha(pozo);
                }
                else{
                    System.out.println("Error!! No se puede depositar una ficha en el pozo si no se ha levantado una ficha del mazo o del pozo");
                    opcion1 = 1;
                }
            }

            if(jugador.comprobarAtrilVacio() == true){

                if(jugador.comprobarMuertoVacio() == false){
                    Atril atril = jugador.getAtril();
                    Muerto muerto = jugador.getMuerto();
                    atril.setFichas(muerto);
                    System.out.println("El jugador tal ha comprado el muerto!!");
                }
                else{
                    opcion1 = 3;
                }
            }
            System.out.println();
        }
    }

    public void iniciarRonda(){
        mazo.armarMazo();
        mazo.repartirFichas(jugadores);
        Jugador jugador = new Jugador("Jugadorcito");
        boolean resp = false;
        int i = 0;

        while(resp == false){
            jugador = jugadores.get(i);

            //Esta instruccion es para usar Observer
            jugadorActual = jugador;

            jugador.setTomoFicha(false);
            iniciarTurno(jugador);
            resp = jugador.comprobarAtrilVacio();

            i++;
            if(i == jugadores.size()){
                i = 0;
            }
        }

        jugador.indicarCorto();

        System.out.println("EL " + jugador + " HA CORTADO!! A continuacion, los puntajes actuales de cada jugador:\n");
        for(Jugador j : jugadores){
            System.out.println(j + ": " + j.getPuntaje());
        }
        System.out.println();
        System.out.println("Se iniciara una nueva ronda:\n");

        calcularPuntajeRonda();
        reiniciarElementosRonda();
    }

    public void iniciarPartida(int cantidadJugadores){
        setJugadores(cantidadJugadores);
        boolean finalizo = false;

        while(finalizo == false){

            iniciarRonda();

            finalizo = comprobarFinDePartida();

            if (finalizo == true){
                for(Jugador jugador : jugadores){
                    if(jugador.comprobarGano() == true){
                        System.out.println("PARTIDA FINALIZADA!! EL GANADOR ES EL " + jugador);
                    }
                }
            }
        }
    }

    public boolean comprobarFinDePartida(){
        boolean resp = false;
        int i = 0;
        Jugador jugador;
        while(resp == false && i<jugadores.size()){
            jugador = jugadores.get(i);
            if(jugador.getPuntaje() >= 2000){
                resp = true;
                jugador.indicarGano();
            }
            i++;
        }
        return resp;
    }

    public void calcularPuntajeRonda(){
        Jugador jugador;
        int cantidad = jugadores.size();
        for(int i = 0;i<cantidad;i++){
            jugador = jugadores.get(i);
            jugador.calcularPuntaje();
        }
    }

    public void reiniciarElementosRonda(){
        mazo = new Mazo();
        pozo = new Pozo();
        for(Jugador jugador : jugadores){
            jugador.reiniciarElementosJugador();
        }
    }


    @Override
    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    @Override
    public void quitarObservador(Observador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores() {
        for(Observador o : observadores){
            o.actualizar();
        }
    }
}