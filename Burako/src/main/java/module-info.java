module trabajo_final.poo.burako {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    exports trabajo_final.poo.burako;
    opens trabajo_final.poo.burako to javafx.fxml;
}