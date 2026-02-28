package trabajo_final.poo.burako.modelo;
import java.util.*;

public class Equipo {
    private String nombre;
    private int numero;
    private int puntos;
    private ArrayList<Juego> juegos;
    private ConjuntoDeFichas muerto;
    private boolean hizoCanasta;
    private boolean corto;
    private boolean gano;
    private ArrayList<Jugador> jugadores;
    private int posJugadorActual;

    public Equipo(String nombre,int numero, ArrayList<Jugador> jugadores) {
        this.nombre = nombre;
        this.numero = numero;
        muerto = new ConjuntoDeFichas();
        juegos = new ArrayList<>();
        this.jugadores = jugadores;
        puntos = 0;
        hizoCanasta = false;
        corto = false;
        gano = false;
        posJugadorActual = 0;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPosJugadorActual() {
        if(posJugadorActual >= jugadores.size()){
            posJugadorActual = 0;
        }
        return posJugadorActual++;
    }

    public int getPuntos(){
        return puntos;
    }

    public ConjuntoDeFichas getMuerto() {
        return muerto;
    }

    public ArrayList<Juego> getJuegos() {
        return juegos;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumero() {
        return numero;
    }

    public boolean verificarHizoCanasta(){
        return hizoCanasta;
    }

    public void indicarHizoCanasta(){
        hizoCanasta = true;
    }

    public void indicarCorto(){
        corto = true;
    }

    public void indicarGano(){
        gano = true;
    }

    public boolean comprobarCorto(){
        return corto;
    }

    public boolean comprobarGano(){
        return gano;
    }

    public boolean comprobarMuertoVacio(){
        return muerto.verificarConjuntoVacio();
    }

    //Descarga el contenido del muerto en el atril de uno de los jugadores del equipo.
    public void descargarMuerto(Atril atril){
        for(int i = 1;i<=11;i++){
            atril.setFicha(muerto.getFicha(1));
        }
    }

    public void reiniciarElementosEquipo(){
        muerto = new ConjuntoDeFichas();
        juegos = new ArrayList<>();
        hizoCanasta = false;
        corto = false;
        for(Jugador j : jugadores){
            j.reiniciarElementosJugador();
        }
        posJugadorActual = 0;
    }

    public void calcularPuntaje() {

        for(Jugador j : jugadores){
            puntos -=j.getAtril().calcularPuntajeFichas();
        }

        if(comprobarCorto() == true){
            for(Juego juego : juegos){
                if(juego.verificarCanasta() == true){
                    if(juego.verificarCanastaPura() == true){
                        puntos += 200;}
                    else{
                        puntos += 100;}
                }
                puntos += juego.calcularPuntajeFichas();
            }
            puntos += 200; //Porque tomo al muerto y cerro la partida.
        }
        else{
            if(verificarHizoCanasta() == true){
                for(Juego juego : juegos){
                    puntos += juego.calcularPuntajeFichas();
                }
            }
            else{
                for(Juego juego : juegos){
                    puntos -= juego.calcularPuntajeFichas();
                }
            }

            if(comprobarMuertoVacio() == false){
                puntos -=100;
            }
        }
    }

    @Override
    public String toString(){
        return nombre;
    }

}