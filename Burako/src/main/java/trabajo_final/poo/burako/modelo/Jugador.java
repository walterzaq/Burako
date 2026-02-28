package trabajo_final.poo.burako.modelo;
import java.util.*;

public class Jugador {
    private Atril atril;
    private String nombre;
    private int numero;
    private boolean tomoFicha;

    public Jugador(String nombre,int numero){
        atril = new Atril();
        tomoFicha = false;
        this.nombre = nombre;
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumero() {
        return numero;
    }

    public Atril getAtril(){
        return atril;
    }

    public boolean comprobarAtrilVacio(){
        return atril.verificarConjuntoVacio();
    }

    public boolean verificarSiTomoFicha(){
        return tomoFicha;
    }

    public void setTomoFicha(boolean tomo){
        tomoFicha = tomo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean verficarPuedeBajarJuego(Equipo equipo){
        boolean respuesta = true;

        if(atril.verificarConjuntoVacio() == true || (atril.getFichas()).size() == 1){

            if(equipo.getMuerto().verificarConjuntoVacio() == true){

                if(equipo.verificarHizoCanasta() == false){
                    respuesta = false;
                }

            }
        }
        return respuesta;
    }

    public void reiniciarElementosJugador(){
        atril = new Atril();
        tomoFicha = false;
    }

    public String toString(){
        return nombre;
    }
}
