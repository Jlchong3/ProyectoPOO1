module ec.edu.espol.juegoajedrez {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.juegoajedrez to javafx.fxml;
    exports ec.edu.espol.juegoajedrez;
    opens ec.edu.espol.clases_ajedres.Piezas to javafx.fxml;
    exports ec.edu.espol.clases_ajedres.Piezas;
}
