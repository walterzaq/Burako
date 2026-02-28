package trabajo_final.poo.burako;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import trabajo_final.poo.burako.controlador.*;
import trabajo_final.poo.burako.modelo.*;
import trabajo_final.poo.burako.vista.*;


public class AppGUI extends Application {
    Burako modelo = new Burako();
    VistaGUI vista = new VistaGUI();
    ControladorGUI controlador = new ControladorGUI(modelo,vista);

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fmxlLoader = new FXMLLoader(AppGUI.class.getResource("fwf2.fxml"));
        fmxlLoader.setController(vista);

        Scene escena = new Scene(fmxlLoader.load(),640,480);
        stage.setTitle("POO!!!");
        stage.setScene(escena);
        stage.show();

        vista.setControlador(controlador);
        //Por lo que vi en la clase, parece necesario que la vista conozca tambien al controlador.
    }
}

