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
        modelo.agregarObservador(this);
    }

    public void iniciarBurako(){
        int opcion = 2;
        while(opcion!=0){
            vista.mostrarMenuPrincipal();
            vista.obtenerOpcion();

            if(opcion == 1){
                iniciarPartida();
            }
        }
    }

    public void iniciarPartida(){
        vista.mostrarMensajeIngreso("Ingrese la cantidad de Jugadores: ");
        int cantidad = vista.obtenerOpcion();
        modelo.iniciarPartida(cantidad);
    }

    public void turno(){
        int opcion;
        Jugador j = modelo.getJugadorActual();
        vista.mostrarMenuOpcion1(j);
        opcion = vista.obtenerOpcion();
        modelo.setOpcion1(opcion);
    }

    public void menuTomarFicha(){
        int opcion;
        vista.mostrarMenuOpcion2TomarFicha();
        opcion = vista.obtenerOpcion();
        modelo.setOpcion2(opcion);
    }

    public void yaHaTomadoFichas(){
        vista.mostrarMensaje("Error!! Ya se han tomado fichas del mazo o del pozo");
    }

    public void noSeHanTomadoFichas(){
        vista.mostrarMensaje("Error!! La operacion no puede realizarse porque no se han tomado fichas del mazo o del pozo");
    }

    public void pudoTomarFichas(){
        vista.mostrarMensaje("Se han tomado las fichas con exito!!");
    }

    public void elMazoOPozoEstaVacio(){
        vista.mostrarMensaje("ERROR!! La pila seleccionada no tiene fichas.");
    }



    public void menuArmarJuego(){
        int opcion;
        vista.mostrarMenuOpcion2ArmarJuego();
        opcion = vista.obtenerOpcion();
        modelo.setOpcion2(opcion);
    }




    @Override
    public void actualizar() {

    }
}
