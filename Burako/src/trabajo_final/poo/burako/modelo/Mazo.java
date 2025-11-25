package trabajo_final.poo.burako.modelo;
import java.util.ArrayList;
import java.util.Random;

public class Mazo extends ConjuntoDeFichas implements ConjuntoTomable {

    public void armarMazo(){
        Ficha ficha = new Ficha(0,Color.COMODIN);
        setFicha(ficha);
        ficha = new Ficha(0,Color.COMODIN);
        setFicha(ficha);
        for(int i = 1;i<=8;i++){
            for(int j=1;j<=13;j++){
                if(i==1 || i==2){
                    ficha = new Ficha(j,Color.AZUL);
                }
                if(i==3 || i==4){
                    ficha = new Ficha(j, Color.AMARILLO);
                }
                if(i==5 || i==6){
                    ficha = new Ficha(j,Color.NEGRO);
                }
                if(i==7 || i==8){
                    ficha = new Ficha(j,Color.ROJO);
                }
                setFicha(ficha);
            }
        }
    }

    public void repartirFichas(ArrayList<Jugador> jugadores){
        int cantidad = jugadores.size();
        Jugador jugador;
        for(int i = 0;i<cantidad;i++){
            jugador = jugadores.get(i);
            jugador.completarAtril(this);
            jugador.completarMuerto(this);
        }
    }

    public ArrayList<Ficha> tomar(){
        Random rand = new Random();
        int indiceAleatorio = rand.nextInt(getFichas().size());
        indiceAleatorio++;
        ArrayList<Ficha> fichas = new ArrayList<>();
        fichas.add(getFicha(indiceAleatorio));
        return fichas;
    }
}
