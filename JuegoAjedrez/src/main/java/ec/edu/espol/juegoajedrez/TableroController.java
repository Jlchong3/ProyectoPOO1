/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.juegoajedrez;

import ec.edu.espol.clases_ajedres.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author josel
 */
public class TableroController implements Initializable {

    @FXML
    private GridPane tablero;
    private Cuadro[][] matriz;
    private ImageView imv;
    private int[] xy = {-1,-1};
    @FXML
    private Label relojNegro;
    @FXML
    private Label relojBlanco;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.matriz = new Cuadro[8][8];
        int count = 0;
        for (int i = 0; i < 8; i++) {
            count++;
            for (int j = 0; j < 8; j++) {
                Cuadro cuadro = new Cuadro(i,j);
                Rectangle r = new Rectangle(90,70);
                if (count % 2 == 0)
                    r.setFill(Color.rgb(70,83,115));
                else
                    r.setFill(Color.rgb(223,230,245));
                if (i == 1 ){
//                    ImageView img = new ImageView();
//                    img.setImage(new Image("img/blackPawn.png"));
                    Peon p = new Peon(TipoColor.Negro,i,j);
                    cuadro.setOcupado(true);
                    cuadro.getChildren().addAll(r,p);
                }else if( i==6){
//                    ImageView img = new ImageView();
//                    img.setImage(new Image("img/whitePawn.png"));
                    Peon p = new Peon(TipoColor.blanco,i,j);
                    cuadro.getChildren().addAll(r,p);
                    cuadro.setOcupado(true);
                    cuadro.getChildren().addAll(r,img);
                }
                else
                    cuadro.getChildren().add(r);
                tablero.add(cuadro, j, i);
                matriz[i][j] = cuadro; 
                cuadro.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) ->{
                    if (cuadro.isOcupado()){
                        this.imv = (ImageView)cuadro.getChildren().get(1);
                        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                        xy[0] = cuadro.getXpos();
                        xy[1] = cuadro.getYpos();
                        a.show();
                    }
                    else if(!(cuadro.isOcupado()) && xy[0] != -1){
                        Alert a = new Alert(Alert.AlertType.CONFIRMATION, ""+xy[0]+xy[1]+" "+ cuadro.getXpos() + cuadro.getYpos());
                        matriz[xy[0]][xy[1]].getChildren().remove(1);
                        matriz[xy[0]][xy[1]].setOcupado(false);
                        cuadro.getChildren().add(imv);
                        cuadro.setOcupado(true);
                        imv = null;
                        xy[0] = -1;
                        xy[1] = -1;
                    }
                });
                count++;
            }
        }
        Reloj h1 = new Reloj(relojNegro);
        Reloj h2 = new Reloj(relojBlanco);
        h1.start();
        h2.start();
    }
    public class Reloj extends Thread{
        private int minutos;
        private int segundos;
        private Label reloj;
        
        public Reloj(Label reloj){
            this.reloj = reloj;
            this.minutos = 10;
            this.segundos = 0;
        }
        
        
        @Override
        public void run(){
            while (minutos > 0 || segundos > 0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                if (segundos == 0){
                    minutos--;
                    segundos = 60;
                }
                segundos--;
                if (segundos < 10){
                    Platform.runLater(()->{
                        reloj.setText(minutos+":0"+segundos);
                    });
                }   
                else{
                    Platform.runLater(()->{
                        reloj.setText(minutos+":"+segundos);
                    });
                }
            }
            
        }
    }
}
