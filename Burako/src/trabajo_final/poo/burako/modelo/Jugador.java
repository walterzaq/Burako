package trabajo_final.poo.burako.modelo;
import java.util.*;

public class Jugador {
    private Atril atril;
    private Muerto muerto;
    private ArrayList<Juego> juegos;
    private int puntaje;
    private String nombre;
    private boolean tomoFicha;
    private boolean hizoCanasta;
    private boolean corto;
    private boolean gano;


    public Jugador(String nombre){
        atril = new Atril();
        muerto = new Muerto();
        juegos = new ArrayList<>();
        puntaje = 0;
        tomoFicha = false;
        this.nombre = nombre;
        hizoCanasta = false;
        corto = false;
        gano = false;
    }

    public Muerto getMuerto() {
        return muerto;
    }

    public Atril getAtril() {
        return atril;
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

    public void completarAtril(ConjuntoTomable conjunto){
        atril.setFichas(conjunto);
    }

    public boolean comprobarAtrilVacio(){
        return atril.verificarConjuntoVacio();
    }

    public void completarMuerto(Mazo mazo){
        muerto.setFichas(mazo);
    }

    public boolean comprobarMuertoVacio(){
        return muerto.verificarConjuntoVacio();
    }

    public int getPuntaje(){
        return puntaje;
    }

    public boolean verificarSiTomoFicha(){
        return tomoFicha;
    }

    public void setTomoFicha(boolean tomo){
        tomoFicha = tomo;
    }

    //tomar ficha pude recibir por parametro solo una instancia de mazo o de pozo, pero no de muerto.
    public void tomarFicha(ConjuntoTomable conjunto){
        ArrayList<Ficha> fichas = conjunto.tomar();
        int cantidad = fichas.size();

        if(cantidad!=0){
            for(Ficha ficha : fichas){
                atril.setFicha(ficha);
            }
            setTomoFicha(true);
            System.out.println("Se han tomado las fichas con exito!!");
        }
        else{
            System.out.println("ERROR!! La pila seleccionada no tiene fichas.");
        }
    }

    public Juego armarJuego(){
        Juego juego = new Juego();
        ArrayList<Ficha> fichas = new ArrayList<>();
        int cantidad, posicion = 6;
        Scanner scanner = new Scanner(System.in);

        while(posicion != 0 || fichas.isEmpty() == true){
            cantidad = (atril.getFichas()).size();
            mostrarAtril();
            System.out.println();
            System.out.print("Seleccione 1 a 1 las fichas para su jugada. Si desea completar un juego existente que es una escalera o armar una nueva escalera, seleccione las fichas en el orden correspondiente. Para finalizar la seleccion, presione 0: ");
            posicion = scanner.nextInt();
            scanner.nextLine();

            if((posicion<1 || posicion>cantidad) && posicion!=0){
                System.out.println("ERROR!! La posicion ingresada no corresponde a ninguna ficha del atril");
            }
            else if(posicion == 0){
                if(fichas.isEmpty() == true){
                    System.out.println("ERROR!! Debe seleccionar al menos una ficha.");
                }
            }
            else{
                fichas.add(atril.getFicha(posicion));
            }
        }

        juego.armar(fichas);
        return juego;
    }

    public void depositarFicha(Pozo pozo){
        int cantidad = (atril.getFichas()).size();
        int posicion = 0;
        Ficha ficha;
        Scanner scanner = new Scanner(System.in);

        while(posicion<1 || posicion>cantidad){
            mostrarAtril();
            System.out.println();
            System.out.print("Seleccione la ficha del atril que desea depositar en el pozo: ");
            posicion = scanner.nextInt();

            if(posicion<1 || posicion>cantidad){
                System.out.println("ERROR!! La posicion ingresada no corresponde a ninguna ficha del atril");
            }
        }

        ficha = atril.getFicha(posicion);
        pozo.setFicha(ficha);
    }

    public void bajarJuegoNuevo(Juego juego){
        boolean respuesta = true;

        if(juego.verificarJuegoValido() == false){
            respuesta = false;
        }
        else{
            respuesta = verficarPuedeCortar();
        }

        if(respuesta==true){
            if(juego.verificarCanasta() == true){
                indicarHizoCanasta();
            }
            juegos.add(juego);
            System.out.println("Se ha bajado el juego exitosamente!!");
        }
        else{
            ArrayList<Ficha> fichas = juego.getFichas();
            for(Ficha ficha : fichas){
                atril.setFicha(ficha);
            }
            System.out.println("Jugada Invalida!! No se ha bajado el juego");
        }
    }

    public void completarJuegoExistente(Juego juego){
        mostrarJuegos();
        int posicion = -1, cantidad = juegos.size();
        boolean juegoValido = true, puedeCortar = true;
        Scanner scanner = new Scanner(System.in);

        while (posicion<1 || posicion>cantidad) {

            System.out.print("Por favor, seleccione el juego que desea completar: ");
            posicion = scanner.nextInt();
            scanner.nextLine();

            if(posicion<1 || posicion>cantidad){
                System.out.println();
                System.out.println("ERROR!! La posicion ingresada no corresponde a uno de los juegos del jugador");
            }
        }
        posicion--;
        Juego hipotetico = new Juego();
        Juego juegoSeleccionado = juegos.get(posicion);
        ArrayList<Ficha> fichasExistente = juegoSeleccionado.getFichas();
        ArrayList<Ficha> fichasNuevo = juego.getFichas();

        for(Ficha ficha : fichasNuevo){
            fichasExistente.add(ficha);
        }

        if(juegoSeleccionado.verificarPierna() == true){
            hipotetico.armar(fichasExistente);
            if(hipotetico.verificarPierna() == false){
                juegoValido = false;
            }
        }
        else if (juegoSeleccionado.verificarEscalera() == true) {
            //No funciona correctamente el completarJuegoExistente cuando tiene que completar una escalera!!

            //Primero recorro la lista de fichasExistente para obtener cada comodin, y los paso a la lista de fichasNuevo.
            Ficha ficha;
            int i = 0;
            while (i<fichasExistente.size()) {
                ficha = fichasExistente.get(i);
                if (ficha.getColor() == Color.COMODIN) {
                    fichasNuevo.add(ficha);
                    fichasExistente.remove(i);
                }
                else{i++;}
            }

            //Ahora ordeno la lista de fichasExistente de menor a mayor.
            fichasExistente.sort((f1,f2) -> Integer.compare(f1.getNumero(),f2.getNumero()));

            //Y ahora recorro primero la lista de fichasNuevo para recuperar cada uno de los comodines. Con cada comodin, recorro la lista de fichasExistente para agregar el comodin en la
            //posicion que corresponda.
            Ficha ficha2;
            int j = 0;
            Ficha comodin;
            while(j<fichasNuevo.size()){
                i = 0;
                comodin = fichasNuevo.get(j);
                while (i<(fichasExistente.size())-1 && comodin != null) {
                    ficha = fichasExistente.get(i);
                    ficha2 = fichasExistente.get(i+1);

                    if (ficha2.getNumero() != (ficha.getNumero())+1 && ficha2.getColor() != Color.COMODIN && ficha.getColor() != Color.COMODIN) {
                        fichasExistente.add(i+1,comodin);
                        comodin = null;
                    }
                    i++;
                }
                if(comodin != null){
                    fichasExistente.add(comodin);
                }
                j++;
            }

            hipotetico.armar(fichasExistente);

            if(hipotetico.verificarEscalera() == false){
                juegoValido = false;
            }

        }

        if(juegoValido == false){
            System.out.println("Jugada Invalida!! No se ha bajado el juego");
            for(Ficha ficha : juego.getFichas()){
                atril.setFicha(ficha);
            }
        }
        else{
            if(verficarPuedeCortar() == true){
                System.out.println("Se ha bajado el juego exitosamente!!");
                for(Ficha ficha : juego.getFichas()){
                    juegoSeleccionado.setFicha(ficha);
                }
            }
            else{
                System.out.println("No se puede cortar!! El jugador aun no hizo una canasta.");

                for(Ficha ficha : juego.getFichas()){
                    atril.setFicha(ficha);
                }
            }
        }
    }

    public boolean verificarHizoCanasta(){
        return hizoCanasta;
    }

    public void indicarHizoCanasta(){
        hizoCanasta = true;
    }

    public boolean verficarPuedeCortar(){
        boolean respuesta = true;

        if(atril.verificarConjuntoVacio() == true || (atril.getFichas()).size() == 1){

            if(muerto.verificarConjuntoVacio() == true){

                if(verificarHizoCanasta() == false){
                    respuesta = false;
                }

            }
        }
        return respuesta;
    }

    public void calcularPuntaje() {

        if(comprobarCorto() == true){
            for(Juego juego : juegos){
                if(juego.verificarCanasta() == true){
                    if(juego.verificarCanastaPura() == true){puntaje+= 200;}
                    else{puntaje+= 100;}
                }
                puntaje+= juego.calcularPuntajeFichas();
            }
            puntaje+= 200; //Porque tomo al muerto y cerro la partida.
        }
        else{
            if(verificarHizoCanasta() == true){
                for(Juego juego : juegos){
                    puntaje+= juego.calcularPuntajeFichas();
                }
            }
            else{
                for(Juego juego : juegos){
                    puntaje-= juego.calcularPuntajeFichas();
                }
            }

            if(comprobarMuertoVacio() == false){puntaje-=100;}
            puntaje-=atril.calcularPuntajeFichas();
        }
    }

    public void reiniciarElementosJugador(){
        atril = new Atril();
        muerto = new Muerto();
        ArrayList<Juego> juegos = new ArrayList<>();
        tomoFicha = false;
        hizoCanasta = false;
        corto = false;
    }

    public String toString(){
        return nombre;
    }






    //Estos son metodos temporales hasta que aplique mvc y observer.

    public void mostrarAtril(){
        ArrayList<Ficha> fichas = atril.getFichas();
        System.out.println("Atril:");
        int numero = 1;
        for(Ficha ficha : fichas){
            System.out.print(numero + ")|" + ficha + "| ");
            numero++;
        }
    }

    public void mostrarJuegos(){
        System.out.println("Juegos:");
        int cant = juegos.size();
        for(int i = 1;i<=cant;i++){
            System.out.print(i + ") ");
            mostrarJuego(i);
            System.out.println();
        }
    }

    public void mostrarJuego(int posicion){
        Juego juego = juegos.get(posicion-1);
        ArrayList<Ficha> fichas = juego.getFichas();
        for(Ficha ficha : fichas){
            System.out.print("|" + ficha + "| ");
        }
    }

    public void mostrarDatosJugador(){

        System.out.println("Juegos:");
        int cant = juegos.size();
        for(int i = 1;i<=cant;i++){
            mostrarJuego(i);
            System.out.println();
        }
        System.out.println();

        System.out.println("Atril:");
        ArrayList<Ficha> fichas = atril.getFichas();
        for(Ficha ficha : fichas){
            System.out.print("(" + ficha + ") ");
        }
        System.out.println("\n");
    }
}
