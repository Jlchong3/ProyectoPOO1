/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.juegoajedrez;

import ec.edu.espol.clases_ajedres.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jonanyu 11.1
 */
public class LoginController implements Initializable {

    @FXML
    private PasswordField pwd;
    @FXML
    private TextField email;
    @FXML
    private BorderPane principal;
    @FXML
    private Label logan;
    @FXML
    private Button Registrar;
    @FXML
    private Button Ingresar;
    @FXML
    private Button Cancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Color myColor = Color.web("#042474");;
        principal.setBackground(new Background(new BackgroundFill (myColor, null, null)));
        Color Color2 = Color.WHITE;
        CornerRadii cornerRadii = new CornerRadii(10); // El número representa los píxeles del radio de las esquinas. Ajusta según tus necesidades.
        logan.setBackground(new Background(new BackgroundFill(Color2, cornerRadii, null)));
        email.getStyleClass().addAll("rounded-text-field","centered-text-field");
        pwd.getStyleClass().addAll("rounded-text-field","centered-text-field");
        Color color_button = Color.web("#1a2a34");
        Registrar.getStyleClass().add("beautiful-button");
        Ingresar.getStyleClass().add("beautiful-button");
        Cancelar.getStyleClass().add("beautiful-button");
   
    }

    @FXML
    private void VentanaRegistrar(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("registro.fxml"));

        Scene scene = new Scene(root); // a donde quiere ir
        Stage stage = (Stage) email.getScene().getWindow(); // donde estoy
        scene.getStylesheets().add(getClass().getResource("Styles/style1.css").toExternalForm());
        stage.setScene(scene);
    }

    @FXML
    private void cancelar(MouseEvent event) {
        email.setText("");
        pwd.setText("");
        Stage stage = (Stage) email.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void loginUser(MouseEvent event) throws IOException {
        String e_mail = email.getText();
        String pasw = pwd.getText();
        if ((Usuario.correoValido(e_mail) == false) || (e_mail.equals(" ") == true)) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Las credenciales son Invalidos");
            a.show();
        } else if ((Usuario.validar_correo(e_mail, "Usuarios.ser") == false)
                || (Usuario.validar_password(e_mail, pasw, "Usuarios.ser") == false)) {
            Alert a = new Alert(Alert.AlertType.ERROR, "El Correo o Contraseña no es el correcto");
            a.show();
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("jugadores.fxml"));

            Scene scene = new Scene(root);
            Stage stage = (Stage) email.getScene().getWindow();
            scene.getStylesheets().add(getClass().getResource("Styles/style1.css").toExternalForm());
            
            stage.setScene(scene);
        }
    }
}
