package trabajo_final.poo.burako.modelo;
import java.util.ArrayList;

public class Muerto extends ConjuntoDeFichas implements ConjuntoTomable{

    public void setFichas(Mazo mazo){
        Ficha ficha;
        for(int i = 1;i<=11;i++){
            ficha = (mazo.tomar()).getLast();
            setFicha(ficha);
        }
    }

    public ArrayList<Ficha> tomar(){
        ArrayList<Ficha> fichas = new ArrayList<>();
        int cant = (getFichas()).size();
        fichas.add(getFicha(cant));
        return fichas;
    }
}


