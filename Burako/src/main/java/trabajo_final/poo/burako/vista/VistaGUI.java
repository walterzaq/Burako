package trabajo_final.poo.burako.vista;
import trabajo_final.poo.burako.controlador.ControladorGUI;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class VistaGUI{
    private ControladorGUI controlador;
    @FXML
    private Label etiqueta;

    public void setControlador(ControladorGUI controlador) {
        this.controlador = controlador;
    }

    /*
    public void setEtiqueta(String string){
        etiqueta.setText(string);
    }

    public void cambiarEscena() {
        controlador.setEtiqueta();
    }

     */
}
