/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.juegoajedrez;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class JugadoresController implements Initializable {


    @FXML
    private HBox header;
    @FXML
    private ImageView logo;
    @FXML
    private ImageView imv1;
    @FXML
    private ImageView imv2;
    @FXML
    private TextField user1;
    @FXML
    private TextField user2;
    @FXML
    private Button Iniciarjuego;
    private String user_b;
    private String user_n;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Color myColor = Color.web("#042474");;
        header.setBackground(new Background(new BackgroundFill (myColor, null, null)));
        logo.setImage(new Image("logos/logo.png"));
        imv1.setImage(new Image("logos/blancas_user.jpg"));
        imv2.setImage(new Image("logos/negras_user.jpg"));
        Iniciarjuego.getStyleClass().add("beautiful-button");
        user1.getStyleClass().addAll("rounded-text-field","centered-text-field");
        user2.getStyleClass().addAll("rounded-text-field","centered-text-field");

    }    

    @FXML
    private void Jugar(MouseEvent event) throws IOException {
        user_b = user1.getText();
        user_n = user2.getText();
        // Verificar si los campos están vacíos
        if (user_b.isEmpty() || user_n.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Por Favor, ingresa un nombre de usuario: ");
            alert.setTitle("Campos vacíos");
            alert.show();
            return;  // Termina el método aquí y no avanza
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tablero.fxml"));
        Parent root = loader.load();
//        Parent root = FXMLLoader.load(getClass().getResource("tablero.fxml"));
        Scene scene = new Scene(root);
        TableroController controlador = loader.getController(); 
        controlador.setJugadores(user_b, user_n);
        Stage stage = (Stage) imv1.getScene().getWindow();
        scene.getStylesheets().add(getClass().getResource("Styles/style1.css").toExternalForm());
        stage.setScene(scene);
    }
    
}
