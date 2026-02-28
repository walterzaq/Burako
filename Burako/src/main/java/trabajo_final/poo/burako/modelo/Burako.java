package trabajo_final.poo.burako.modelo;

import trabajo_final.poo.burako.observer.Observador;
import trabajo_final.poo.burako.observer.Observable;

import java.util.*;

public class Burako implements Observable {
    private ArrayList<Equipo> equipos;
    private ArrayList<Puntaje> puntajesMasAltos;
    private Mazo mazo;
    private ConjuntoDeFichas pozo;
    private boolean primerTurno;


    //Estos atributos se usan con Observer
    private ArrayList<Observador> observadores;
    private Jugador jugadorActual;
    private Equipo equipoActual;
    private Juego juegoActual;
    private Ficha fichaActual;
    private int opcion;
    private String opcionLetra;

    public Burako() {
        observadores = new ArrayList<>();
        mazo = new Mazo();
        pozo = new ConjuntoDeFichas();
        puntajesMasAltos = new ArrayList<>();
        primerTurno = true;
    }

    public Jugador getJugadorActual(){
        return jugadorActual;
    }

    public Equipo getEquipoActual() {
        return equipoActual;
    }

    public Juego getJuegoActual() {
        return juegoActual;
    }

    public Ficha getFichaActual() {
        return fichaActual;
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    public ConjuntoDeFichas getPozo() {
        return pozo;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    public void setOpcionLetra(String opcionLetra) {
        this.opcionLetra = opcionLetra;
    }

    //Recibe la cantidad de equipos de 2 jugadores cada uno que van a participar en la partida. El valor 1 indica que van a jugar 2 jugadores individuales enfrentados (internamente representa 2 equipos)
    public void setEquipos(int cant){
        equipos = new ArrayList<>();
        ArrayList<Jugador> jugadores;

        if(cant == 1){
            for(int i = 1;i<=2;i++){
                jugadores = new ArrayList<>();
                jugadorActual = new Jugador("",i);
                notificarObservadores(Evento.INGRESARJUGADOR); //Aca muestra y pide el ingreso del nombre de un jugador, y lo asigna al atributo jugadorActual.
                jugadores.add(jugadorActual);
                equipos.add(new Equipo("",0,jugadores));
            }
        }
        else{
            for(int i = 1;i<=cant;i++){
                jugadores = new ArrayList<>();
                equipoActual = new Equipo("",i,jugadores);
                notificarObservadores(Evento.INGRESAREQUIPO); //Aca muestra y pide el ingreso del nombre del equipo, y lo asigna al atributo equipoActual.

                for(int j = 1;j<=2;j++){
                    jugadorActual = new Jugador("",j);
                    notificarObservadores(Evento.INGRESARJUGCONEQUI); //Aca muestra y pide el ingreso del nombre de un jugador asociado a un equipo, y lo asigna al atributo jugadorActual.
                    jugadores.add(jugadorActual);
                }
                equipos.add(equipoActual);
            }
        }
    }

    public void repartirFichas(ArrayList<Equipo> equipos){
        setAtriles(equipos);
        setMuertos(equipos);
    }

    public void setAtriles(ArrayList<Equipo> equipos){
        for(Equipo e : equipos){
            setAtril(e);
        }
    }

    public void setAtril(Equipo equipo){
        Random rand = new Random();
        int indiceAleatorio;
        ArrayList<Jugador> jugadores = equipo.getJugadores();
        for(Jugador j : jugadores){
            for(int i = 1;i<=11;i++){
                indiceAleatorio = rand.nextInt(mazo.getFichas().size());
                indiceAleatorio++;
                j.getAtril().setFicha(mazo.getFicha(indiceAleatorio));
            }
        }
    }

    public void setMuertos(ArrayList<Equipo> equipos){
        Random rand = new Random();
        int indiceAleatorio;
        for(Equipo e : equipos){
            for(int i = 1;i<=11;i++){
                indiceAleatorio = rand.nextInt(mazo.getFichas().size());
                indiceAleatorio++;
                e.getMuerto().setFicha(mazo.getFicha(indiceAleatorio));
            }
        }
    }

    public void iniciarTurno(){
        opcion = 2;
        while (opcion!=3){
            notificarObservadores(Evento.INICIARTURNO); //Muestro el menu del turno con toda la informacion que necesita el jugador actual para jugar. Y le permito seleccionar alguna de las opciones principales.

            if(opcion==1){
                if(!jugadorActual.verificarSiTomoFicha()){
                    opcion = 3;
                    while (opcion != 1 && opcion != 2){
                        notificarObservadores(Evento.TOMARFICHA); //Muestro en pantalla el menu para tomar una ficha y le permito al jugador seleccionar una opcion.

                        if(opcion != 1 && opcion != 2){
                            notificarObservadores(Evento.RESPUESTAINCORRECTA);
                        }
                    }

                    if(opcion == 1){
                        tomarFichas(mazo);
                    }
                    else{
                        tomarFichas(pozo);
                    }

                    if(primerTurno){
                        opcionLetra = "T";
                        while (!opcionLetra.equals("S") && !opcionLetra.equals("N")){

                            notificarObservadores(Evento.TOMAROTRA); //Pregunta en pantalla al jugador si quiere depositar en el pozo la ficha que tomo o no.

                            if(!opcionLetra.equals("S") && !opcionLetra.equals("N")){
                                notificarObservadores(Evento.RESPUESTAINCORRECTA);
                            }
                        }

                        if(opcionLetra.equals("S")){
                            Ficha ficha = jugadorActual.getAtril().getFicha(jugadorActual.getAtril().getFichas().size());
                            pozo.setFicha(ficha);
                            jugadorActual.setTomoFicha(false);
                            notificarObservadores(Evento.MSJTOMEOTRA); //Indica en pantalla que se ha depositado la ficha en el pozo, y se pide al jugador que tome otra.
                        }

                        primerTurno = false;
                    }

                }
                else{notificarObservadores(Evento.MSJYASEHANTOMADO);} //Indica en pantalla que ya se han tomado fichas del mazo o del pozo.
            }
            else if (opcion==2) {
                if(jugadorActual.verificarSiTomoFicha()){
                    Juego juego = armarJuego();

                    if(juego != null){
                        notificarObservadores(Evento.MENUTIPOJUGADA); //Muestro en pantalla el menu para la seleccion del tipo de jugada.

                        if(opcion==1){bajarJuegoNuevo(juego);}
                        else if(opcion==2){completarJuegoExistente(juego);}
                        else{
                            if(juego.getFichas().size() == 1){intercambiarFichas(juego.getFicha(1));}
                            else{
                                notificarObservadores(Evento.MSJJUGADAINV1); //Indico en pantalla que la jugada es invalida porque no se pueden seleccionar varias fichas si se desea intercambiar fichas.
                                for(Ficha ficha : juego.getFichas()){
                                    jugadorActual.getAtril().setFicha(ficha);
                                }
                            }
                        }
                    }

                }
                else{System.out.println("Error!! No se puede armar una jugada si no se ha levantado una ficha del mazo o del pozo");}
            }
            else if (opcion==3){
                if(jugadorActual.verificarSiTomoFicha()){
                    depositarFicha(pozo);
                }
                else{
                    notificarObservadores(Evento.MSJNOSEPUEDEDEPOSITAR); //Indica en pantalla que no se puede depositar la ficha en el pozo.
                    opcion = 1;
                }
            }

            if(jugadorActual.comprobarAtrilVacio()){

                if(!equipoActual.comprobarMuertoVacio()){
                    ConjuntoDeFichas muerto = equipoActual.getMuerto();
                    equipoActual.descargarMuerto(jugadorActual.getAtril());
                    notificarObservadores(Evento.MSJCOMPROMUERTO); //Indica en pantalla que el jugador ha comprado al muerto.
                }
                else{
                    opcion = 3;
                }
            }
            notificarObservadores(Evento.SALTOLINEA);
        }
    }

    public void iniciarRonda(){
        mazo.armarMazo();
        repartirFichas(equipos);
        boolean resp = false;
        int e = 0, pos;

        while(!resp){

            equipoActual = equipos.get(e);
            pos = equipoActual.getPosJugadorActual();
            jugadorActual = equipoActual.getJugadores().get(pos);

            jugadorActual.setTomoFicha(false);
            iniciarTurno();
            resp = jugadorActual.comprobarAtrilVacio();

            e++;
            if(e == equipos.size()){
                e = 0;
            }
        }

        equipoActual.indicarCorto();
        calcularPuntajeRonda();
        reiniciarElementosRonda();

        notificarObservadores(Evento.JUGADORCORTO); //Aca se indica en pantalla que el jugador corto y se muestran los puntajes actuales de cada jugador/equipo.
    }

    public void iniciarPartida(){
        notificarObservadores(Evento.SELECCIONARMODOJ); //Aca mostrarias en pantalla el menu de ingreso para seleccionar modo de juego y tambien guardarias el ingreso en el atributo opcionSeleccionEquipos.
        while(opcion != 1 && opcion != 2){
            if(opcion != 1 && opcion != 2){
                notificarObservadores(Evento.RESPUESTAINCORRECTA); //Aca mostrarias en pantalla el mensaje de una respuesta incorrecta.
            }
        }
        setEquipos(opcion);
        notificarObservadores(Evento.INICIOPARTIDA); //Aca se indica en pantalla que se iniciara una partida.
        boolean finalizo = false;

        while(!finalizo){
            iniciarRonda();
            finalizo = comprobarFinDePartida();

            if (finalizo){
                Equipo equipo = new Equipo("",1,new ArrayList<>());
                for(Equipo e : equipos){
                    if(e.comprobarGano()){
                        equipo = e;
                        if(e.getJugadores().size() == 1){
                            System.out.println("\nPARTIDA FINALIZADA!! EL GANADOR ES " + equipo.getJugadores().getFirst());
                        }
                        else{
                            System.out.println("\nPARTIDA FINALIZADA!! EL GANADOR ES EL EQUIPO " + equipo);
                        }
                    }
                }
                String nombre = "";
                if(equipo.getJugadores().size() == 1){nombre = equipo.getJugadores().getFirst().getNombre();}
                else{nombre = equipo.getNombre();}

                Puntaje p = new Puntaje(equipo.getPuntos(),nombre);

                if(puntajesMasAltos.isEmpty()){
                    puntajesMasAltos.add(p);
                }
                else{
                    int i = 0;
                    boolean bandera = true;
                    while(i<puntajesMasAltos.size() && i<5 && bandera){
                        if (equipo.getPuntos() > puntajesMasAltos.get(i).getPuntos()){
                            puntajesMasAltos.add(i,p);
                            bandera = false;
                        }
                        i++;
                    }
                    if(puntajesMasAltos.size() == 6){puntajesMasAltos.remove(5);}
                }

                System.out.println("\nLISTA DE PUNTAJES MAS ALTOS\n" +
                                    "NOMBRE              PUNTOS");

                for(Puntaje puntj : puntajesMasAltos){
                    System.out.println(puntj.getNombre() + "              " + puntj.getPuntos());
                }
            }
            else{
                System.out.println("\nSE INICIARA UNA NUEVA RONDA!!\n");
            }
        }
    }

    public boolean comprobarFinDePartida(){
        boolean resp = false;
        int i = 0;
        Equipo equipo;
        while(!resp && i<equipos.size()){
            equipo = equipos.get(i);
            if(equipo.getPuntos() >= 2000){
                resp = true;
                equipo.indicarGano();
            }
            i++;
        }
        return resp;
    }

    public void calcularPuntajeRonda(){
        for(Equipo e : equipos){
            e.calcularPuntaje();
        }
    }

    public void reiniciarElementosRonda(){
        mazo = new Mazo();
        pozo = new ConjuntoDeFichas();
        primerTurno = true;
        for(Equipo e : equipos){
            e.reiniciarElementosEquipo();
        }
    }

    //tomarFicha pude recibir por parametro un Mazo o un Pozo.
    public void tomarFichas(ConjuntoDeFichas conjunto){
        if(!conjunto.verificarConjuntoVacio()){

            if(conjunto.getClass() == Mazo.class){
                Random rand = new Random();
                int indiceAleatorio = rand.nextInt(conjunto.getFichas().size());
                indiceAleatorio++;
                Ficha ficha = conjunto.getFicha(indiceAleatorio);
                fichaActual = ficha;
                jugadorActual.getAtril().setFicha(ficha);
            }
            else{
                int cant = (conjunto.getFichas()).size();
                for(int i = cant;i>=1;i--){
                    jugadorActual.getAtril().setFicha(conjunto.getFicha(i));
                }
            }
            jugadorActual.setTomoFicha(true);
            notificarObservadores(Evento.MSJSEHANTOMADO); //Muestro en pantalla que se han tomado las fichas con exito.
        }
        else{
            notificarObservadores(Evento.MSJPILAVACIA); //Muestro en pantalla que no se pueden tomar las fichas porque la pila de fichas seleccionada esta vacia.
        }
    }

    public Juego armarJuego(){
        Juego juego = new Juego();
        ArrayList<Ficha> fichas = new ArrayList<>();
        int cantidad;
        opcion = 6;

        while(opcion != 0 || fichas.isEmpty()){
            cantidad = (jugadorActual.getAtril().getFichas()).size();
            notificarObservadores(Evento.MENUSELECCIONF); //Muestro en pantalla el menu de seleccion de fichas y le permito al usuario seleccionar las fichas que desea.

            if((opcion<1 || opcion>cantidad) && opcion!=0){notificarObservadores(Evento.MSJPOSINGRESADA1);} //Muestro en pantalla que la posicion ingresada no corresponde a una ficha en el atril.
            else if(opcion == 0){
                if(fichas.isEmpty()){
                    notificarObservadores(Evento.MSJDEBESELECCIONARF); //Muestro en pantalla que debe seleccionar al menos una ficha.
                }
            }
            else{
                fichas.add(jugadorActual.getAtril().getFicha(opcion));
            }
        }

        juego.armar(fichas);
        return juego;
    }

    public void depositarFicha(ConjuntoDeFichas pozo){
        int cantidad = (jugadorActual.getAtril().getFichas()).size();
        opcion = 0;
        Ficha ficha;

        while(opcion<1 || opcion>cantidad){
            notificarObservadores(Evento.MENUDEPOSITARF); //Indico en pantalla al jugador que indique la ficha que desea depositar en el pozo.

            if(opcion<1 || opcion>cantidad){notificarObservadores(Evento.MSJPOSINGRESADA1);}
        }

        ficha = jugadorActual.getAtril().getFicha(opcion);
        pozo.setFicha(ficha);
        opcion = 3;
    }

    public void bajarJuegoNuevo(Juego juego){

        if(!juego.verificarJuegoValido()){
            for(Ficha ficha : juego.getFichas()){
                jugadorActual.getAtril().setFicha(ficha);
            }
            notificarObservadores(Evento.MSJJUGADAINV2); //Indico en pantalla que la jugada es invalida.
        }
        else{
            if(juego.verificarCanasta()){equipoActual.indicarHizoCanasta();}

            if(jugadorActual.verficarPuedeBajarJuego(equipoActual)){
                equipoActual.getJuegos().add(juego);
                notificarObservadores(Evento.MSJSEHABAJADO);
            }
            else{
                for(Ficha ficha : juego.getFichas()){
                    jugadorActual.getAtril().setFicha(ficha);
                }
                notificarObservadores(Evento.MSJNOPUEDECORTAR);
                System.out.println();
            }
        }
    }

    public boolean completarJuegoExistente(Juego juego){
        int cantidad = equipoActual.getJuegos().size();
        opcion = -1;
        boolean resp = true;

        while (opcion<1 || opcion>cantidad) {
            notificarObservadores(Evento.JUEGOS1); //Muestra en pantalla los juegos disponibles del jugador actual y le permite seleccionar uno.

            if(opcion<1 || opcion>cantidad){notificarObservadores(Evento.MSJPOSINGRESADA2);} //Muestro en pantalla que la posicion ingresada no corresponde a uno de los juegos del jugador.
        }
        opcion--;
        juegoActual= equipoActual.getJuegos().get(opcion);
        Juego hipotetico = new Juego();

        ArrayList<Ficha> fjuegoSeleccionado = juegoActual.getFichas();
        ArrayList<Ficha> fjuegoNuevo = juego.getFichas();

        cantidad = fjuegoSeleccionado.size();
        opcion = -1;
        notificarObservadores(Evento.SALTOLINEA);

        while (opcion<1 || opcion>cantidad+1) {
            notificarObservadores(Evento.JUEGONUMERADO1); //Muestra en pantalla el juego seleccionado con sus fichas enumerados, y le permite al jugador seleccionar la posicion en el juego donde quiere insertar las fichas.

            if(opcion<1 || opcion>cantidad+1){notificarObservadores(Evento.MSJPOSINGRESADA3);}
        }

        for(int i = fjuegoNuevo.size()-1;i>=0;i--){
            fjuegoSeleccionado.add(opcion-1,fjuegoNuevo.get(i));
        }

        hipotetico.armar(fjuegoSeleccionado);

        if(!hipotetico.verificarJuegoValido()){
            notificarObservadores(Evento.MSJJUGADAINV3);

            for(Ficha ficha : juego.getFichas()){
                jugadorActual.getAtril().setFicha(ficha);
            }
            resp = false;
        }
        else{

            if(hipotetico.verificarCanasta()){equipoActual.indicarHizoCanasta();}

            if(jugadorActual.verficarPuedeBajarJuego(equipoActual)){
                for(int i = fjuegoNuevo.size()-1;i>=0;i--){
                    juegoActual.setFicha(fjuegoNuevo.get(i),opcion);
                }
                notificarObservadores(Evento.MSJSEHACOMPLETADO);
            }
            else{
                for(Ficha ficha : juego.getFichas()){
                    jugadorActual.getAtril().setFicha(ficha);
                }
                notificarObservadores(Evento.MSJNOPUEDECORTAR);
                resp = false;
            }
        }
        return resp;
    }

    public void intercambiarFichas(Ficha ficha){
        int cantidad = equipoActual.getJuegos().size();
        opcion = -1;

        while (opcion<1 || opcion>cantidad) {
            notificarObservadores(Evento.JUEGOS2); //Muestra en pantalla los juegos disponibles del jugador actual y le permite seleccionar uno.

            if(opcion<1 || opcion>cantidad){notificarObservadores(Evento.MSJPOSINGRESADA2);}
        }
        opcion--;
        Juego juegoSeleccionado = equipoActual.getJuegos().get(opcion);
        Juego hipotetico = new Juego();

        ArrayList<Ficha> fjuegoSeleccionado = juegoSeleccionado.getFichas();

        cantidad = fjuegoSeleccionado.size();
        opcion = -1;
        notificarObservadores(Evento.SALTOLINEA);

        while (opcion<1 || opcion>cantidad) {
            notificarObservadores(Evento.JUEGONUMERADO2);

            if(opcion<1 || opcion>cantidad){notificarObservadores(Evento.MSJPOSINGRESADA3);}
        }
        opcion--;
        Ficha comodin = fjuegoSeleccionado.get(opcion);
        fjuegoSeleccionado.remove(opcion);

        if(comodin.comprobarEsComodin()){
            fjuegoSeleccionado.add(opcion,ficha);
        }
        else{
            notificarObservadores(Evento.MSJJUGADAINV4);
            jugadorActual.getAtril().setFicha(ficha);
        }

        hipotetico.armar(fjuegoSeleccionado);

        if(!hipotetico.verificarJuegoValido()){
            notificarObservadores(Evento.MSJJUGADAINV5);
            jugadorActual.getAtril().setFicha(ficha);
        }
        else{
            opcion++;
            juegoSeleccionado.getFicha(opcion);
            juegoSeleccionado.setFicha(ficha,opcion);
            Juego jcomodin = new Juego();
            jcomodin.setFicha(comodin);
            boolean resp = completarJuegoExistente(jcomodin);

            if(!resp){
                notificarObservadores(Evento.MSJNOCOMNIINT);
                jugadorActual.getAtril().getFicha(jugadorActual.getAtril().getFichas().size());
                jugadorActual.getAtril().setFicha(ficha);
                juegoSeleccionado.getFicha(opcion);
                juegoSeleccionado.setFicha(comodin,opcion);
            }
            else{
                notificarObservadores(Evento.MSJSEINTERCAMBIARON);
            }
        }
    }

    @Override
    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    @Override
    public void quitarObservador(Observador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores(Evento evento) {
        for(Observador o : observadores){
            o.actualizar(evento);
        }
    }
}