package trabajo_final.poo.burako.vista;
import trabajo_final.poo.burako.modelo.*;

import java.util.*;

public class VistaConsola {
    private Scanner scanner = new Scanner(System.in);


    public void mostrarMenuPrincipal(){
        System.out.println("BIENVENIDO A BURAKO!! Seleccione una opcion: ");
        System.out.println("1) INICIAR PARTIDA");
        System.out.println("0) SALIR");
        System.out.print("Ingrese una opcion aqui: ");
    }

    public int obtenerOpcion(){
        return scanner.nextInt();
    }

    public void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }

    public void mostrarMensajeIngreso(String mensaje){
        System.out.print(mensaje);
    }

    public void mostrarMenuOpcion1(Jugador jugador,Burako burako){
        int opcion;
        System.out.println("Es el turno de " + jugador + "!!");
        burako.mostrarPozo();
        jugador.mostrarDatosJugador();
        System.out.println("1) Tomar Ficha del Mazo/Pozo");
        System.out.println("2) Armar Jugada");
        System.out.println("3) Depositar Ficha en el Pozo");
        System.out.print("Ingrese una opcion: ");
    }

    public void mostrarMenuOpcion2TomarFicha(){
        System.out.println("Desea tomar fichas del Mazo o del Pozo?");
        System.out.println("1) Tomar Ficha del Mazo");
        System.out.println("2) Tomar Fichas del Pozo");
        System.out.print("Ingrese una opcion: ");
    }

    public void mostrarMenuOpcion2ArmarJuego(){
        System.out.println("Seleccione el tipo de jugada");
        System.out.println("1) Bajar un Juego Nuevo");
        System.out.println("2) Completar un Juego Existente");
        System.out.print("Ingrese una opcion: ");
    }






}
