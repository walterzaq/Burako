package trabajo_final.poo.burako.controlador;
import trabajo_final.poo.burako.modelo.*;
import trabajo_final.poo.burako.vista.VistaConsola;
import trabajo_final.poo.burako.observer.Observador;

public class ControladorConsola implements Observador {
    private Burako modelo;
    private VistaConsola vista;

    public ControladorConsola(Burako modelo, VistaConsola vista){
        this.modelo = modelo;
        this.vista = vista;
        this.modelo.agregarObservador(this);
    }

    public void iniciarAplicacion(){
        int opcion = 2;
        while(opcion!=0){
            vista.mostrarMenuPrincipal();
            opcion = vista.obtenerOpcionint();

            if(opcion == 1){
                iniciarPartida();
            }
            else if (opcion == 0){
                return;
            }
        }
    }

    public void iniciarPartida(){
        modelo.iniciarPartida();
    }

    public void seleccionarModoDeJuego(){
        int opcion;
        vista.mostrarMenuModoDeJuego();
        vista.mostrarMensajeIngresos();
        opcion = vista.obtenerOpcionint();
        modelo.setOpcion(opcion);
    }

    public void ingresoNombreJugador(){
        String nombre;
        vista.mostrarMensajeGenerico("\nIngrese el nombre del jugador " + modelo.getJugadorActual().getNumero() + ".");
        vista.mostrarMensajeIngresos();
        nombre = vista.obtenerOpcionString();
        modelo.getJugadorActual().setNombre(nombre);
    }

    public void ingresoNombreEquipo(){
        String nombre;
        vista.mostrarMensajeGenerico("\nIngrese el nombre del equipo " + modelo.getEquipoActual().getNumero() + ".");
        vista.mostrarMensajeIngresos();
        nombre = vista.obtenerOpcionString();
        modelo.getEquipoActual().setNombre(nombre);
    }

    public void ingresoNombreJugadorConEquipo(){
        String nombre;
        vista.mostrarMensajeGenerico("\nIngrese el nombre del jugador " + modelo.getJugadorActual().getNumero() + " del equipo " + modelo.getEquipoActual().getNombre() + ".");
        vista.mostrarMensajeIngresos();
        nombre = vista.obtenerOpcionString();
        modelo.getJugadorActual().setNombre(nombre);
    }

    public void mostrarRespuestaIncorrecta(){
        vista.mostrarMensajeGenerico("\nRespuesta incorrecta!! Vuelva a intentarlo.\n");
    }

    public void mostrarInicioPartida(){
        vista.mostrarMensajeGenerico("\nSE INICIARA UNA NUEVA PARTIDA!!");
    }

    public void mostrarJugadorCorto(){
        if(modelo.getEquipoActual().getJugadores().size() == 1){
            vista.mostrarMensajeGenerico(modelo.getJugadorActual() + " HA CORTADO!! A continuacion, los puntajes actuales de cada jugador:\n");
            for(Equipo eq : modelo.getEquipos()){
                System.out.println(eq.getJugadores().getFirst() + ": " + eq.getPuntos());
            }
        }
        else{
            vista.mostrarMensajeGenerico(modelo.getJugadorActual() + " HA CORTADO!! A continuacion, los puntajes actuales de cada equipo:\n");
            for(Equipo eq : modelo.getEquipos()){
                System.out.println(eq + ": " + eq.getPuntos());
            }
        }
    }

    public void menuIniciarTurno(){
        vista.mostrarMensajeGenerico("Es el turno de " + modelo.getJugadorActual() + "!!\n");
        vista.mostrarPozo(modelo.getPozo());
        vista.mostrarDatosTurno(modelo.getJugadorActual(),modelo.getEquipoActual());
        vista.mostrarMensajeIngresos();
        modelo.setOpcion(vista.obtenerOpcionint());
        vista.mostrarMensajeGenerico("\n\n");
    }

    public void menuTomarFicha(){
        vista.mostrarMensajeGenerico("Desea tomar fichas del Mazo o del Pozo?");
        vista.mostrarMensajeGenerico("1) Tomar Ficha del Mazo");
        vista.mostrarMensajeGenerico("2) Tomar Fichas del Pozo");
        vista.mostrarMensajeIngresos();
        modelo.setOpcion(vista.obtenerOpcionint());
    }

    public void menuTomarOtraFicha(){
        vista.mostrarTomarOtraFicha(modelo.getFichaActual());
        vista.mostrarMensajeIngresos();
        modelo.setOpcionLetra(vista.obtenerOpcionString());
    }

    public void mensajeTomarOtraFicha(){
        vista.mostrarMensajeGenerico("\nSe ha depositado la ficha en el pozo, tome otra!!");
    }

    public void mensajeYaSeHanTomadoFichas(){
        vista.mostrarMensajeGenerico("Error!! Ya se han tomado fichas del mazo o del pozo");
    }

    public void mensajeNoSePuedeDepositar(){
        vista.mostrarMensajeGenerico("Error!! No se puede depositar una ficha en el pozo si no se ha levantado una ficha del mazo o del pozo");
    }

    public void mensajeComproMuerto(){
        vista.mostrarMensajeGenerico("\n" + modelo.getJugadorActual() + " ha comprado al muerto!!");
    }

    public void saltoDeLinea(){
        vista.mostrarMensajeGenerico("");
    }

    public void menuTipoDeJugada(){
        vista.mostrarMenuTipoDeJugada();
        vista.mostrarMensajeIngresos();
        modelo.setOpcion(vista.obtenerOpcionint());
    }

    public void mensajeJugadaInvalida1(){
        vista.mostrarMensajeGenerico("\nJugada Invalida!! Solo se puede intercambiar una ficha por otra");
    }

    public void mensajeSeHanTomadoFichas(){
        vista.mostrarMensajeGenerico("\nSe han tomado las fichas con exito!!\n");
    }

    public void mensajePilaVacia(){
        vista.mostrarMensajeGenerico("\nERROR!! La pila de fichas seleccionada esta vacia.");
    }

    public void menuSeleccionDeFichas(){
        vista.mostrarMensajeGenerico("\nSeleccione 1 a 1 las fichas para su jugada. Para finalizar la seleccion, presione 0.");
        vista.mostrarAtrilJugadorActual(modelo.getJugadorActual().getAtril());
        vista.mostrarMensajeIngresos();
        modelo.setOpcion(vista.obtenerOpcionint());
    }

    public void mensajePosicionIngresada1(){
        vista.mostrarMensajeGenerico("ERROR!! La posicion ingresada no corresponde a ninguna ficha del atril");
    }

    public void mensajeDebeSeleccionar(){
        vista.mostrarMensajeGenerico("ERROR!! Debe seleccionar al menos una ficha.");
    }

    public void menuDepositarFicha(){
        vista.mostrarMensajeGenerico("\nSeleccione la ficha del atril que desea depositar en el pozo.");
        vista.mostrarAtrilJugadorActual(modelo.getJugadorActual().getAtril());
        vista.mostrarMensajeIngresos();
        modelo.setOpcion(vista.obtenerOpcionint());
    }

    public void mensajeJugadaInvalida2(){
        vista.mostrarMensajeGenerico("\nJugada Invalida!! No se ha bajado el juego (si se intenta bajar una escalera, recuerde seleccionar las fichas en el orden correspondiente).");
    }

    public void mensajeJuegoBajado(){
        vista.mostrarMensajeGenerico("\nSe ha bajado el juego exitosamente!!");
    }

    public void mensajeNoPuedeCotar(){
        vista.mostrarMensajeGenerico("\nNo se puede cortar!! El jugador aun no hizo una canasta.");
    }

    public void mostrarJuegosDisponibles1(){
        vista.mostrarMensajeGenerico("Por favor, seleccione el juego que desea completar.");
        vista.mostrarJuegos(modelo.getEquipoActual());
        vista.mostrarMensajeIngresos();
        modelo.setOpcion(vista.obtenerOpcionint());
    }

    public void mensajePosicionIngresada2(){
        vista.mostrarMensajeGenerico("\nERROR!! La posicion ingresada no corresponde a uno de los juegos disponibles del jugador");
    }

    public void mostrarJuegoNumerado1(){
        vista.mostrarMensajeGenerico("\nAhora seleccione la posicion en el juego donde desea insertar la/s ficha/s selecionadas.");
        vista.mostrarJuegoNumerado(modelo.getJuegoActual(),0);
        vista.mostrarMensajeIngresos();
        modelo.setOpcion(vista.obtenerOpcionint());
    }

    public void mensajePosicionIngresada3(){
        vista.mostrarMensajeGenerico("\n\nERROR!! La posicion ingresada no existe en este juego");
    }

    public void mensajeJugadaInvalida3(){
        vista.mostrarMensajeGenerico("\nJugada Invalida!! No se ha completado el juego (si se intenta bajar una escalera, recuerde seleccionar las fichas en el orden correspondiente).");
    }

    public void mensajeJuegoCompletado(){
        vista.mostrarMensajeGenerico("\nSe ha completado el juego exitosamente!!");
    }

    public void mostrarJuegosDisponibles2(){
        vista.mostrarMensajeGenerico("Por favor, seleccione el juego donde desea intercambiar la ficha.");
        vista.mostrarJuegos(modelo.getEquipoActual());
        vista.mostrarMensajeIngresos();
        modelo.setOpcion(vista.obtenerOpcionint());
    }

    public void mostrarJuegoNumerado2(){
        vista.mostrarMensajeGenerico("\nSeleccione el comodin que desea intercambiar.");
        vista.mostrarJuegoNumerado(modelo.getJuegoActual(),1);
        vista.mostrarMensajeIngresos();
        modelo.setOpcion(vista.obtenerOpcionint());
    }

    public void mensajeJugadaInvalida4(){
        vista.mostrarMensajeGenerico("\nJugada Invalida!! Solo se puede intercambiar una ficha por un comodin en el juego seleccionado.");
    }

    public void mensajeJugadaInvalida5(){
        vista.mostrarMensajeGenerico("\nJugada Invalida!! No se han intercambiado las fichas");
    }

    public void mensajeNoCompletoNiIntercambio(){
        vista.mostrarMensajeGenerico("\nNo se ha completado el juego ni intercambiado las fichas!!");
    }

    public void mensajeSeIntercambiaron(){
        vista.mostrarMensajeGenerico("\nSe intercambiaron las fichas exitosamente!!");
    }

    @Override
    public void actualizar(Evento evento) {
        if(evento == Evento.SELECCIONARMODOJ){seleccionarModoDeJuego();}
        else if(evento == Evento.RESPUESTAINCORRECTA){mostrarRespuestaIncorrecta();}
        else if(evento == Evento.INGRESARJUGADOR){ingresoNombreJugador();}
        else if(evento == Evento.INGRESARJUGCONEQUI){ingresoNombreJugadorConEquipo();}
        else if(evento == Evento.INGRESAREQUIPO){ingresoNombreEquipo();}
        else if(evento == Evento.INICIOPARTIDA){mostrarInicioPartida();}
        else if(evento == Evento.JUGADORCORTO){mostrarJugadorCorto();}
        else if(evento == Evento.INICIARTURNO){menuIniciarTurno();}
        else if(evento == Evento.TOMARFICHA){menuTomarFicha();}
        else if(evento == Evento.TOMAROTRA){menuTomarOtraFicha();}
        else if(evento == Evento.MSJTOMEOTRA){mensajeTomarOtraFicha();}
        else if(evento == Evento.MSJYASEHANTOMADO){mensajeYaSeHanTomadoFichas();}
        else if(evento == Evento.MSJNOSEPUEDEDEPOSITAR){mensajeNoSePuedeDepositar();}
        else if(evento == Evento.MSJCOMPROMUERTO){mensajeComproMuerto();}
        else if(evento == Evento.SALTOLINEA){saltoDeLinea();}
        else if(evento == Evento.MENUTIPOJUGADA){menuTipoDeJugada();}
        else if(evento == Evento.MSJJUGADAINV1){mensajeJugadaInvalida1();}
        else if(evento == Evento.MSJSEHANTOMADO){mensajeSeHanTomadoFichas();}
        else if(evento == Evento.MSJPILAVACIA){mensajePilaVacia();}
        else if(evento == Evento.MENUSELECCIONF){menuSeleccionDeFichas();}
        else if(evento == Evento.MSJPOSINGRESADA1){mensajePosicionIngresada1();}
        else if(evento == Evento.MSJDEBESELECCIONARF){mensajeDebeSeleccionar();}
        else if(evento == Evento.MENUDEPOSITARF){menuDepositarFicha();}
        else if(evento == Evento.MSJJUGADAINV2){mensajeJugadaInvalida2();}
        else if(evento == Evento.MSJSEHABAJADO){mensajeJuegoBajado();}
        else if(evento == Evento.MSJNOPUEDECORTAR){mensajeNoPuedeCotar();}
        else if(evento == Evento.JUEGOS1){mostrarJuegosDisponibles1();}
        else if(evento == Evento.MSJPOSINGRESADA2){mensajePosicionIngresada2();}
        else if(evento == Evento.JUEGONUMERADO1){mostrarJuegoNumerado1();}
        else if(evento == Evento.MSJPOSINGRESADA3){mensajePosicionIngresada3();}
        else if(evento == Evento.MSJJUGADAINV3){mensajeJugadaInvalida3();}
        else if(evento == Evento.MSJSEHACOMPLETADO){mensajeJuegoCompletado();}
        else if(evento == Evento.JUEGOS2){mostrarJuegosDisponibles2();}
        else if(evento == Evento.JUEGONUMERADO2){mostrarJuegoNumerado2();}
        else if(evento == Evento.MSJJUGADAINV4){mensajeJugadaInvalida4();}
        else if(evento == Evento.MSJJUGADAINV5){mensajeJugadaInvalida5();}
        else if(evento == Evento.MSJNOCOMNIINT){mensajeNoCompletoNiIntercambio();}
        else if(evento == Evento.MSJSEINTERCAMBIARON){mensajeSeIntercambiaron();}
    }
}
