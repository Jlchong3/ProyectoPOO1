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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Jonanyu 11.1
 */
public class RegistroController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private PasswordField psw;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cancelarRegresar(MouseEvent event) throws IOException {
        name.setText("");
        email.setText("");
        psw.setText("");
        
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) name.getScene().getWindow();
        stage.setScene(scene);  
    }

    @FXML
    private void registrarUsuario(MouseEvent event) {
        String e_mail = email.getText();
        String pasw = psw.getText();
        String name_user=name.getText();
        if ((Usuario.correoValido(e_mail)==false)||(e_mail.equals("")==true)) {
            Alert a =new Alert(Alert.AlertType.ERROR,"El correo Ingresado es INVALIDO");
            a.show();
        }
        
        if ((Usuario.validar_correo(e_mail, "Usuarios.ser")==true)&&!(e_mail.equals(""))) {
            Alert a =new Alert(Alert.AlertType.ERROR,"El correo ya esta registrado");
            a.show();
        }else{
            Usuario user=new Usuario(name_user,e_mail,pasw);
            Usuario.add_user_file(user, "Usuarios.ser");
            email.setText("");
            psw.setText("");
            name.setText("");       
        }
    }
    
}
