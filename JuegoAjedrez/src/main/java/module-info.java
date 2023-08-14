module ec.edu.espol.juegoajedrez {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.juegoajedrez to javafx.fxml;
    exports ec.edu.espol.juegoajedrez;
}
