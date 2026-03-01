package trabajo_final.poo.burako.controlador;

import javafx.fxml.*;
import trabajo_final.poo.burako.modelo.*;
import trabajo_final.poo.burako.observer.*;
import trabajo_final.poo.burako.vista.*;
import trabajo_final.poo.burako.AppGUI;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ControladorGUI implements Observador {
    VistaGUI vista;
    Burako modelo;

    public ControladorGUI(Burako modelo,VistaGUI vista) {
        this.vista = vista;
        this.modelo = modelo;
        this.modelo.agregarObservador(this);
    }

    public void setEtiqueta() {
        vista.setEtiqueta("MENSAJE");
    }

    @Override
    public void actualizar(Evento evento) {

    }
}

