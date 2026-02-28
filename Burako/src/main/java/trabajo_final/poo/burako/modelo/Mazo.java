package trabajo_final.poo.burako.modelo;
import java.util.ArrayList;
import java.util.Random;

public class Mazo extends ConjuntoDeFichas{

    public void armarMazo(){
        Ficha ficha = new Ficha(0,new Color("CO"),true);
        setFicha(ficha);
        ficha = new Ficha(0,new Color("CO"),true);
        setFicha(ficha);
        for(int i = 1;i<=8;i++){
            for(int j=1;j<=13;j++){
                if(i==1 || i==2){
                    ficha = new Ficha(j,new Color("AZ"),false);
                    if(ficha.getNumero() == 2){
                        ficha.setEsComodin(true);
                    }
                }
                if(i==3 || i==4){
                    ficha = new Ficha(j,new Color("AM"),false);
                    if(ficha.getNumero() == 2){
                        ficha.setEsComodin(true);
                    }
                }
                if(i==5 || i==6){
                    ficha = new Ficha(j,new Color("RO"),false);
                    if(ficha.getNumero() == 2){
                        ficha.setEsComodin(true);
                    }
                }
                if(i==7 || i==8){
                    ficha = new Ficha(j,new Color("NE"),false);
                    if(ficha.getNumero() == 2){
                        ficha.setEsComodin(true);
                    }
                }
                setFicha(ficha);
            }
        }
    }
}
