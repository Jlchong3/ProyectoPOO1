/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.juegoajedrez;

import ec.edu.espol.clases_ajedres.*;
import java.net.URL;
import java.util.ArrayList;
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
    private Pieza pieza;
    private ArrayList<int[]> movimientosValidos = new ArrayList<>();
    @FXML
    private Label relojNegro;
    @FXML
    private Label relojBlanco;
    private boolean turnoBlanco = true;
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
                llenarTablero(cuadro,r);
                cuadro.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) ->{
                    int[] cuadropos = {cuadro.getXpos(),cuadro.getYpos()};
                    boolean valido = Pieza.inMovimientosValidos(movimientosValidos, cuadropos);
                    if(cuadro.isOcupado()){
                        Pieza actual = (Pieza)cuadro.getChildren().get(1);
                        if (((turnoBlanco && actual.getColor().equals(TipoColor.Blanco)) || (!turnoBlanco && actual.getColor().equals(TipoColor.Negro))) && this.pieza == null){
                            this.pieza = actual;
                            this.movimientosValidos = this.pieza.movimientos_posibles(matriz);
                            for (int[] xd : this.movimientosValidos){
                   
                                System.out.println(xd[0]+","+xd[1]);
                            }
                            Alert a = new Alert(Alert.AlertType.CONFIRMATION,"primera vez");
                            a.show();
                        }
                        else if(cuadro.isOcupado() && this.pieza != null && !actual.getColor().equals(this.pieza.getColor()) && valido){
                            this.pieza.eliminarPieza(actual,matriz);
                            this.pieza = null;
                            this.movimientosValidos.clear();
                            this.turnoBlanco = !this.turnoBlanco;
                        }

                        else if (cuadro.isOcupado() && this.pieza != null && actual.getColor().equals(this.pieza.getColor())){
                            this.pieza = actual;
                            this.movimientosValidos = this.pieza.movimientos_posibles(matriz);
                            for (int[] xd : this.movimientosValidos){
                                System.out.println(xd[0]+","+xd[1]);
                            }
                            Alert a = new Alert(Alert.AlertType.CONFIRMATION,"otra pieza color");
                            a.show();
                        }
                    }
                    
                    else{
                        if (this.pieza != null && valido){
                            this.pieza.mover(cuadropos,matriz);
                            this.pieza = null;
                            this.movimientosValidos.clear();
                            this.turnoBlanco = !this.turnoBlanco;
                        }
                        
                    }
                }
                 
                
                
                );
                count++;
            }
        }
        Reloj h1 = new Reloj(relojNegro,(!turnoBlanco));
        Reloj h2 = new Reloj(relojBlanco,turnoBlanco);
        h1.start();
        h2.start();
        
        
        
    }
	public class Reloj extends Thread{
        private int minutos;
        private int segundos;
        private Label reloj;
        private boolean turno;
        
        public Reloj(Label reloj,boolean turno){
            this.reloj = reloj;
            this.minutos = 10;
            this.segundos = 0;
            this.turno = turno;
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

    public void llenarTablero(Cuadro cuadro,Rectangle r){
        int x = cuadro.getXpos();
        int y = cuadro.getYpos();
        Pieza p = null;
        if(x == 1){
            p = new Peon(TipoColor.Negro,x,y);
        }
        else if(x == 6){
            p = new Peon(TipoColor.Blanco,x,y);
        }
        
        else if (x == 7 && (y == 0 || y == 7)){
            p = new Torre(TipoColor.Blanco,x,y);
        }
        
        else if (x == 0 && (y == 0 || y == 7)){
            p = new Torre(TipoColor.Negro,x,y);
        }
        
        else if (x == 7 && (y == 1 || y == 6)){
            p = new Caballo(TipoColor.Blanco,x,y);
        }
        
        else if (x == 0 && (y == 1 || y == 6)){
            p = new Caballo(TipoColor.Negro,x,y);
        }
        
        else if (x == 7 && (y == 2 || y == 5)){
            p = new Alfil(TipoColor.Blanco,x,y);
        }
        
        else if (x == 0 && (y == 2 || y == 5)){
            p = new Alfil(TipoColor.Negro,x,y);
        }
        else if (x == 7 && y == 3){
            p = new Dama(TipoColor.Blanco,x,y);
        }
        else if (x == 0 && y == 3){
            p = new Dama(TipoColor.Negro,x,y);
        }
        else if (x == 7 && y == 4){
            p = new Rey(TipoColor.Blanco,x,y);
        }
        else if (x == 0 && y == 4){
            p = new Rey(TipoColor.Negro,x,y);
        }
        if (p == null){
            cuadro.getChildren().addAll(r);
            tablero.add(cuadro, y, x);
            matriz[x][y] = cuadro;
        }
        else{
            cuadro.getChildren().addAll(r,p);
            cuadro.setOcupado(true);
            tablero.add(cuadro, y, x);
            matriz[x][y] = cuadro;
        }
    } 
}