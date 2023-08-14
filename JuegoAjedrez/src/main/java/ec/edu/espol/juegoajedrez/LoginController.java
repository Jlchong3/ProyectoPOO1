/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.juegoajedrez;

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
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

 
    @FXML
    private void VentanaRegistrar(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Registro.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) email.getScene().getWindow();
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
    private void loginUser(MouseEvent event) {
        
        String e_mail = email.getText();
        String pasw = pwd.getText();
        if (Usuario.correoValido(e_mail)==false) {
            Alert a =new Alert(Alert.AlertType.ERROR,"El correo Ingresado es INVALIDO");
            a.show();
        }
        
        if (Usuario.validar_correo(e_mail, "Usuarios.ser")==false) {
            Alert a =new Alert(Alert.AlertType.ERROR,"Correo No existe, por favor registrese");
            a.show();
        }
        if(Usuario.validar_password(e_mail,pasw, "Usuarios.ser")==false){
            Alert a =new Alert(Alert.AlertType.ERROR,"Contraseña Invalida");
            a.show();
        }
    }
    
    

    
    
}
