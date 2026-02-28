package trabajo_final.poo.burako.modelo;
import java.util.ArrayList;

public class Juego extends ConjuntoDeFichas{

    public void armar(ArrayList<Ficha> fichas) {
        for(Ficha ficha : fichas){
            setFicha(ficha);
        }
    }

    public boolean verificarJuegoValido() {
        boolean resp = false;

        if(verificarEscalera() == true){resp = true;}
        else{
            if(verificarPierna() == true){resp = true;}
        }

        return resp;
    }

    private boolean verificarTieneMultiplesComodines(){
        boolean resp = false;
        int cantComodines = 0;
        ArrayList<Ficha> fichas = getFichas();
        int i = 0;

        while (cantComodines<2 && i<fichas.size()){
            if(fichas.get(i).comprobarEsComodin() == true){cantComodines++;}
            i++;
        }
        if(cantComodines >= 2){resp = true;}

        return resp;
    }

    public boolean verificarPierna() {
        boolean respuesta = true;
        int cantidad = getFichas().size(), cantDe2 = 0, cantDeCO = 0, i = 0;
        Ficha ficha;

        if(cantidad >= 3){

            while(i<cantidad){
                ficha = getFichas().get(i);
                if(ficha.getNumero() == 2){
                    cantDe2++;
                }
                if(ficha.getColor().getTipo().equals("CO")){
                    cantDeCO++;
                }
                i++;
            }

            i = 0;
            if(cantDe2 == cantidad || (cantDe2 == cantidad-1 && cantDeCO == 1)){
                while(i<cantidad){
                    ficha = getFichas().get(i);
                    if(ficha.getNumero() == 2){ficha.setEsComodin(false);}
                    i++;
                }
            }
            else{
                int numero = -1;
                ficha = new Ficha(1,new Color("AM"),true);
                while(i<cantidad && ficha.comprobarEsComodin() == true){
                    ficha = getFichas().get(i);
                    if(ficha.comprobarEsComodin() == false) {
                        numero = ficha.getNumero();
                    }
                    i++;
                }

                if(numero != -1){
                    i = 0;
                    while(i<cantidad && respuesta == true){
                        ficha = getFichas().get(i);
                        if(ficha.getNumero() != numero && !ficha.comprobarEsComodin()){
                            respuesta = false;
                        }
                        i++;
                    }
                }
            }
            if(verificarTieneMultiplesComodines() == true){respuesta = false;}
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

            if(getFichas().get(0).getNumero() == 2 && (getFichas().get(1).getNumero() == 3 || getFichas().get(1).comprobarEsComodin() == true)){
                getFichas().get(0).setEsComodin(false);
            }

            int numero = 3, i = 0;
            Color color = new Color("AM");
            Ficha ficha = new Ficha(numero,color,true);

            while(i<cantidad && ficha.comprobarEsComodin() == true){
                ficha = getFichas().get(i);

                if(ficha.comprobarEsComodin() == false) {
                    numero = ficha.getNumero();
                    color = ficha.getColor();
                }

                i++;
            }

            while(i<cantidad && respuesta == true){
                ficha = getFichas().get(i);

                if((!ficha.getColor().getTipo().equals(color.getTipo()) || ficha.getNumero() != numero+1) && !ficha.comprobarEsComodin()){
                    if(ficha.getNumero() == 1 && ficha.getColor().getTipo().equals(color.getTipo())){
                        if(numero != 13){
                            respuesta = false;
                        }
                        else{
                            numero = 1;
                        }
                    }
                    else{
                        respuesta = false;
                    }
                }
                if(ficha.getNumero() == numero+1 || (ficha.comprobarEsComodin() == true && numero != 13)){
                    if((ficha.comprobarEsComodin() == true && ficha.getNumero() == 2 && numero == 1)){
                        ficha.setEsComodin(false);
                    }
                    numero++;
                }
                else if (ficha.comprobarEsComodin() == true && numero == 13) {
                    numero = 1;
                }
                i++;

                if(getFichas().get(0).getNumero() == 2 && (getFichas().get(1).getNumero() == 3 || getFichas().get(1).comprobarEsComodin() == true) && respuesta == false){
                    getFichas().get(0).setEsComodin(true);
                }
            }
            if(verificarTieneMultiplesComodines() == true){respuesta = false;}
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
                    if(ficha.comprobarEsComodin() == true){
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
            else if (ficha.getColor().getTipo().equals("CO")) {
                puntaje+=50;
            }
        }
        return puntaje;
    }
}
