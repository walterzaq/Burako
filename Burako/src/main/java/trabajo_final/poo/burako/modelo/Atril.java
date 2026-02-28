package trabajo_final.poo.burako.modelo;

public class Atril extends ConjuntoDeFichas{

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
            else if (ficha.getColor().getTipo().equals("CO")) {
                puntaje+=50;
            }
        }
        return puntaje;
    }
}
