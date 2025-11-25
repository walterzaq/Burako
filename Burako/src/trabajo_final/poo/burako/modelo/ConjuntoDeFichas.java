package trabajo_final.poo.burako.modelo;
import java.util.ArrayList;


public abstract class ConjuntoDeFichas {
    private ArrayList<Ficha> fichas;

    public ConjuntoDeFichas(){
        fichas = new ArrayList<>();
    }

    //Obtiene una ficha a partir de una posicion ordinal, aunque internamente la primera posicion sea 0. Al hacerlo, retira la ficha del conjunto.
    public Ficha getFicha(int posicion) {
        posicion--;
        Ficha ficha = fichas.get(posicion);
        fichas.remove(posicion);
        return ficha;
    }

    //Este metodo devuelve una lista con todas las fichas del conjunto, sin retirarlas del conjunto. Ademas, las modificaciones realizadas sobre la lista obtenida no tendran efecto sobre la lista
    //interna que maneja el conjunto.
    public ArrayList<Ficha> getFichas() {
        ArrayList<Ficha> fichas2 = new ArrayList<>();
        for(Ficha ficha : fichas){
            fichas2.add(ficha);
        }
        return fichas2;
    }

    /* Este metodo por ahora no lo implemento, despues veo si lo uso.
    public void setFicha(Ficha ficha,int posicion) {
        this.fichas.add(posicion,ficha);
    }
    */

    public void setFicha(Ficha ficha) {
        this.fichas.add(ficha);
    }

    public boolean verificarConjuntoVacio(){
        return fichas.isEmpty();
    }
}

//Hara falta un metodo "vaciarMazo"?
