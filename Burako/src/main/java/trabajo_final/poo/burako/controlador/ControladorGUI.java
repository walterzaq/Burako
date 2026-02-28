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
    //Stage stage;

    public ControladorGUI(Burako modelo,VistaGUI vista) {
        this.vista = vista;
        this.modelo = modelo;
        this.modelo.agregarObservador(this);
    }

    /*
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    */

    public void setEtiqueta() {
        vista.setEtiqueta("MENSAJE");
    }

    /*
    public void cambiarAEscenaJuego() throws IOException {
        FXMLLoader fmxlLoader = new FXMLLoader(AppGUI.class.getResource("escena-juego.fxml"));
        fmxlLoader.setController(vista);

        Scene escena = new Scene(fmxlLoader.load(),640,480);
        stage.setTitle("POO!!!");
        stage.setScene(escena);
        stage.show();
    }

     */

    @Override
    public void actualizar(Evento evento) {

    }
}
