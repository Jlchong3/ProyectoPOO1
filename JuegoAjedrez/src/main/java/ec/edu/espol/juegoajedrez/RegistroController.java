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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Jonanyu 11.1
 */
public class RegistroController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private TextField psw;
    @FXML
    private BorderPane registro_page;
    @FXML
    private Button b_cancelar;
    @FXML
    private Button b_registrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Color myColor = Color.web("#042474");;
        registro_page.setBackground(new Background(new BackgroundFill (myColor, null, null)));
        email.getStyleClass().addAll("rounded-text-field","centered-text-field");
        psw.getStyleClass().addAll("rounded-text-field","centered-text-field");
        b_cancelar.getStyleClass().add("beautiful-button");
        b_registrar.getStyleClass().add("beautiful-button");
    }    

    @FXML
    private void cancelarRegresar(MouseEvent event) throws IOException {
        email.setText("");
        psw.setText("");
        
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Styles/style1.css").toExternalForm());
        Stage stage = (Stage) psw.getScene().getWindow();
        stage.setScene(scene);  
    }

    @FXML
    private void registrarUsuario(MouseEvent event) throws IOException {
        String e_mail = email.getText();
        String pasw = psw.getText();
        if ((Usuario.correoValido(e_mail)==false)||(e_mail.equals("")==true)) {
            Alert a =new Alert(Alert.AlertType.ERROR,"El correo Ingresado es INVALIDO");
            a.show();
        }
        
        if ((Usuario.validar_correo(e_mail, "Usuarios.ser")==true)&&!(e_mail.equals(""))) {
            Alert a =new Alert(Alert.AlertType.ERROR,"El correo ya esta registrado");
            a.show();
        }else{
            Usuario user=new Usuario(e_mail,pasw);
            Usuario.add_user_file(user, "Usuarios.ser");
            email.setText("");
            psw.setText("");    
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("Styles/style1.css").toExternalForm());
            Stage stage = (Stage) psw.getScene().getWindow();
            stage.setScene(scene);  
        }
    }
    
}
