package trabajo_final.poo.burako;
import trabajo_final.poo.burako.modelo.*;
import trabajo_final.poo.burako.vista.*;
import trabajo_final.poo.burako.controlador.*;


public class App {
    public static void main(String[] args) {
        Burako burako = new Burako();
        VistaConsola vista = new VistaConsola();
        ControladorConsola controlador = new ControladorConsola(burako,vista);
        controlador.iniciarAplicacion();
    }
}
