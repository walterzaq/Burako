package trabajo_final.poo.burako.vista;
import trabajo_final.poo.burako.controlador.ControladorGUI;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class VistaGUI{
    private ControladorGUI controlador;
    @FXML
    private Label etiqueta;

    public void setControlador(ControladorGUI controlador) {
        this.controlador = controlador;
    }

    public void setEtiqueta(String string){
        etiqueta.setText(string);
    }

    public void modificarEtiqueta() {
        controlador.setEtiqueta();
    }


}
