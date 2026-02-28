package trabajo_final.poo.burako.vista;
import trabajo_final.poo.burako.modelo.*;

import java.util.*;

public class VistaConsola {
    private Scanner scanner = new Scanner(System.in);


    public void mostrarMenuPrincipal(){
        System.out.println("\nBIENVENIDO A BURAKO!! Seleccione una opcion: ");
        System.out.println("1) INICIAR PARTIDA");
        System.out.println("0) SALIR");
        System.out.print("Ingrese una opcion aqui: ");
    }

    public int obtenerOpcionint(){
        int resp = scanner.nextInt();
        scanner.nextLine();
        return resp;
    }

    public String obtenerOpcionString(){
        return scanner.nextLine().toUpperCase();
    }

    public void mostrarMensajeGenerico(String mensaje){
        System.out.println(mensaje);
    }

    public void mostrarMensajeIngresos(){
        System.out.print("\nRespuesta: ");
    }

    public void mostrarMenuModoDeJuego(){
        System.out.print("\nSeleccione modo de juego:\n" +
                "1) 2 Jugadores (1 VS 1)\n" +
                "2) 4 Jugadores (2 VS 2)\n");
    }

    public void mostrarPozo(ConjuntoDeFichas pozo){
        java.util.ArrayList<trabajo_final.poo.burako.modelo.Ficha> fichas = pozo.getFichas();
        System.out.println("Pozo:");
        for(Ficha ficha : fichas){
            System.out.print("[" + ficha + "] ");
        }
        System.out.println();
    }

    public void mostrarJuego(Juego juego){
        ArrayList<Ficha> fichas = juego.getFichas();
        for(Ficha ficha : fichas){
            System.out.print("[" + ficha + "] ");
        }
    }

    public void mostrarDatosTurno(Jugador jugador,Equipo equipo){

        System.out.println("Puntos: " + equipo.getPuntos() + "\n");
        System.out.println("Juegos:");
        int cant = equipo.getJuegos().size();
        for(int i = 1;i<=cant;i++){
            mostrarJuego(equipo.getJuegos().get(i-1));
            System.out.println();
        }
        System.out.println();

        System.out.println("Atril:");
        ArrayList<Ficha> fichas = jugador.getAtril().getFichas();
        for(Ficha ficha : fichas){
            System.out.print("[" + ficha + "] ");
        }
        System.out.println("\n1) Tomar Ficha del Mazo/Pozo");
        System.out.println("2) Armar Jugada");
        System.out.println("3) Depositar Ficha en el Pozo");
    }

    public void mostrarTomarOtraFicha(Ficha ficha){
        System.out.println("La ficha seleccionada es: [" + ficha + "]");
        System.out.println("Desea depositar la ficha en el pozo y tomar otra? (S/N).");
    }

    public void mostrarMenuTipoDeJugada(){
        System.out.println("Seleccione el tipo de jugada");
        System.out.println("1) Bajar un Juego Nuevo");
        System.out.println("2) Completar un Juego Existente");
        System.out.println("3) Intercambiar por comodin en un Juego Existente");
    }

    public void mostrarAtrilJugadorActual(Atril atril){
        ArrayList<Ficha> fichas = atril.getFichas();
        System.out.println("Atril:");
        int numero = 1;
        for(Ficha ficha : fichas){
            System.out.print(numero + ")[" + ficha + "]   ");
            numero++;
        }
    }

    public void mostrarJuegos(Equipo equipo){
        System.out.println("Juegos:");
        int cant = equipo.getJuegos().size();
        for(int i = 1;i<=cant;i++){
            System.out.print(i + ") ");
            mostrarJuego(equipo.getJuegos().get(i-1));
            System.out.println();
        }
    }


    //Recibe un 0 si se quisiera mostrar las posiciones disponibles, incluyendo una posicion adicional (para usar con el metodo armarJuegoExistente). Ingresando otro numero, se muestran solo las
    //posiciones disponibles en el juego (para usar con el metodo intercambiarFichas).
    public void mostrarJuegoNumerado(Juego juego,int valor){
        ArrayList<Ficha> fichas = juego.getFichas();
        int i;
        for(i=0;i<fichas.size();i++){
            System.out.print(i+1 + ")[" + fichas.get(i) + "]   ");
        }
        if(valor == 0){System.out.print(i+1 + ")_");}
    }


}
