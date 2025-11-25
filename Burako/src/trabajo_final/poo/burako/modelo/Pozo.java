package trabajo_final.poo.burako.modelo;
import java.util.ArrayList;

public class Pozo extends ConjuntoDeFichas implements ConjuntoTomable {

    public ArrayList<Ficha> tomar(){
        ArrayList<Ficha> fichas = new ArrayList<>();
        int cant = (getFichas()).size();
        Ficha ficha;
        for(int i = cant;i>=1;i--){
            ficha = getFicha(i);
            fichas.add(ficha);
        }
        return fichas;
    }
}
