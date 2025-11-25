package trabajo_final.poo.burako.modelo;
import java.util.ArrayList;

public class Juego extends ConjuntoDeFichas{

    public void armar(ArrayList<Ficha> fichas) {
        for(Ficha ficha : fichas){
            setFicha(ficha);
        }
    }

    public boolean verificarJuegoValido() {
        return (verificarEscalera() || verificarPierna());
    }

    public boolean verificarPierna() {
        boolean respuesta = true;
        int cantidad = getFichas().size();

        if(cantidad >= 3){
            int numero = (getFichas().getFirst()).getNumero(), i = 0;
            Ficha ficha;

            while(i<cantidad && respuesta == true){
                ficha = getFichas().get(i);

                if(ficha.getNumero() != numero && ficha.getColor() != Color.COMODIN){
                    respuesta = false;
                }
                i++;
            }
        }
        else{
            respuesta = false;
        }
        return respuesta;
    }

    public boolean verificarEscalera() {
        boolean respuesta = true;
        int cantidad = getFichas().size();

        if(cantidad >= 3){
            int numero = (getFichas().getFirst()).getNumero(), i = 1;
            Color color = (getFichas().getFirst()).getColor();
            Ficha ficha;

            while(i<cantidad && respuesta == true){
                ficha = getFichas().get(i);

                if((ficha.getColor() != color || ficha.getNumero() != numero+1) && ficha.getColor() != Color.COMODIN){
                    respuesta = false;
                }
                //Nota: esta solución provisional todavía no contempla al 2 como comodín o las escaleras que hacen una transición del 13 al 1.
                numero++;
                i++;
            }
        }
        else{
            respuesta = false;
        }
        return respuesta;
    }

    public boolean verificarCanasta(){
        boolean respuesta = true;

        if(verificarJuegoValido() == false){
            respuesta = false;
        }
        else{
            if(getFichas().size() < 7){
                respuesta = false;
            }
        }
        return respuesta;
    }

    public boolean verificarCanastaPura(){
        boolean respuesta = true;

        if(verificarJuegoValido() == false){
            respuesta = false;
        }
        else{
            if(verificarCanasta() == false){
                respuesta = false;
            }
            else{
                for(Ficha ficha : getFichas()){
                    if(ficha.getColor() == Color.COMODIN){
                        respuesta = false;
                    }
                }
            }
        }
        return respuesta;
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
