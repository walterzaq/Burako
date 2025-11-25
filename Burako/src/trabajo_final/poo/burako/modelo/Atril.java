package trabajo_final.poo.burako.modelo;

public class Atril extends ConjuntoDeFichas{

    //Aca podria recibir como conjunto tomable solo al mazo o al muerto, pero no al pozo.
    public void setFichas(ConjuntoTomable conjunto){
        Ficha ficha;
        for(int i = 1;i<=11;i++){
            ficha = (conjunto.tomar()).getLast();
            setFicha(ficha);
        }
    }

    public int calcularPuntajeFichas(){
        int puntaje = 0;
        for(Ficha ficha : getFichas()){
            if(ficha.getNumero() == 1){
                puntaje+=15;
            }
            else if (ficha.getNumero() == 2) {
                puntaje+=20;
            }
            else if (ficha.getNumero() >= 3 && ficha.getNumero() <= 7) {
                puntaje+=5;
            }
            else if (ficha.getNumero() >= 8 && ficha.getNumero() <= 13) {
                puntaje+=10;
            }
            else if (ficha.getColor() == Color.COMODIN) {
                puntaje+=50;
            }
        }
        return puntaje;
    }
}
