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
    private boolean enJuego = true;
    private Cuadro[][] matriz;
    private boolean ganador;
    private Pieza pieza;
    private boolean jaque;
    private ArrayList<int[]> movimientosValidos = new ArrayList<>();
    private ArrayList<int[]> allMovesPosibles = new ArrayList<>();
    private Rey ReyBlanco;
    private Rey ReyNegro;
    @FXML
    private Label relojNegro;
    @FXML
    private Label relojBlanco;
    private boolean turnoBlanco = true;
    @FXML
    private Label jugador_blanca;
    @FXML
    private Label jugador_negra;
    private boolean trabas;

    /**
     * Initializes the controller class.
     */
    public void setJugadores(String user_b, String user_n) {
        jugador_blanca.setText(user_b);
        jugador_negra.setText(user_n);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        relojNegro.setText("10:00");
        relojBlanco.setText("10:00");
        this.matriz = new Cuadro[8][8];
        int count = 0;
        for (int i = 0; i < 8; i++) {
            count++;
            for (int j = 0; j < 8; j++) {
                Cuadro cuadro = new Cuadro(i, j);
                Rectangle r = new Rectangle(90, 70);
                if (count % 2 == 0) {
                    r.setFill(Color.rgb(70, 83, 115));
                    cuadro.setColor(Color.rgb(70, 83, 115));
                } else {
                    r.setFill(Color.rgb(223, 230, 245));
                    cuadro.setColor(Color.rgb(223, 230, 245));
                }
                llenarTablero(cuadro, r);
                cuadro.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
                    String relojb = (String)this.relojBlanco.getText();
                    String relojn = (String)this.relojBlanco.getText();
                    Rey rey;
                    if(turnoBlanco){
                        rey = this.ReyBlanco;
                    }    
                    else{
                        rey = this.ReyNegro;
                    }
                    int[] reypos = {rey.getXpos(),rey.getYpos()};
                   
                    if(rey.trabas(matriz, allMovesPosibles)){
                        enJuego = false;
                        trabas = true;
                    }
                    
                    if(Pieza.inMovimientos(this.allMovesPosibles,reypos)){
                        jaque = true;
                    }
                    
                    
                    if(jaque && rey.movimientosRestantes(matriz, allMovesPosibles).isEmpty()){
                        enJuego = false;
                        ganador = !turnoBlanco;
                    } 
                    if(relojn.equals("0:00")){
                        enJuego = false;
                        ganador = true;
                    }
                    if(relojb.equals("0:00")){
                        enJuego = false;
                        ganador = false;
                    }
   
                    if(enJuego){
                        int[] cuadropos = {cuadro.getXpos(), cuadro.getYpos()};
                        boolean valido = Pieza.inMovimientos(movimientosValidos, cuadropos);
                        if(jaque && this.pieza == null){
                            this.pieza = (Pieza)matriz[reypos[0]][reypos[1]].getChildren().get(1);
                            this.movimientosValidos = this.pieza.movimientos_posibles(matriz);
                        }
                        else if (cuadro.isOcupado()){
                            Pieza actual = (Pieza) cuadro.getChildren().get(1);
                            if (((turnoBlanco && actual.getColor().equals(TipoColor.Blanco)) || (!turnoBlanco && actual.getColor().equals(TipoColor.Negro))) && this.pieza == null) {
                                if (jaque){
                                    this.pieza = (Pieza)matriz[reypos[0]][reypos[1]].getChildren().get(1);
                                    Rey king = (Rey) this.pieza;
                                    this.movimientosValidos = king.movimientosRestantes(matriz, allMovesPosibles);
                                }
                                else{
                                    this.pieza = actual;
                                    if (this.pieza instanceof Rey){
                                            Rey king = (Rey)this.pieza;
                                            this.movimientosValidos =  king.movimientosRestantes(matriz, allMovesPosibles);
                                        }
                                    else
                                        this.movimientosValidos = this.pieza.movimientos_posibles(matriz);
                                   
                                }
                                for (int[] m : this.movimientosValidos) {
                                    Rectangle c = (Rectangle) this.matriz[m[0]][m[1]].getChildren().get(0);
                                    c.setFill(Color.rgb(97, 166, 120   ));
                                }
                            } 
                            else if (cuadro.isOcupado() && this.pieza != null && !actual.getColor().equals(this.pieza.getColor()) && valido) {
                                this.pieza.eliminarPieza(actual, matriz);
                                if (this.pieza instanceof Rey){
                                    if(turnoBlanco){
                                        this.ReyBlanco.setXpos(this.pieza.getXpos());
                                        this.ReyBlanco.setYpos( this.pieza.getYpos());
                                    }
                                    else{
                                        this.ReyNegro.setXpos(this.pieza.getXpos());
                                        this.ReyNegro.setYpos(this.pieza.getYpos());
                                    }
                                    if (jaque){
                                        jaque = false;
                                    }
                                }
                                this.pieza = null;
                                for (int[] m : this.movimientosValidos) {

                                    Platform.runLater(() -> {
                                        Color colorCu = this.matriz[m[0]][m[1]].getColor();
                                        Rectangle c = (Rectangle) this.matriz[m[0]][m[1]].getChildren().get(0);
                                        c.setFill(colorCu);
                                    }
                                    );
                                }
                                this.movimientosValidos.clear();
                                this.turnoBlanco = !this.turnoBlanco;
                                this.allMovesPosibles = Pieza.allMovimientosPosibles(matriz, !turnoBlanco);
                            } 
                            else if (cuadro.isOcupado() && this.pieza != null && actual.getColor().equals(this.pieza.getColor())) {
                                for (int[] m : this.movimientosValidos){
                                    Color colorCu = this.matriz[m[0]][m[1]].getColor();
                                    Rectangle c = (Rectangle) this.matriz[m[0]][m[1]].getChildren().get(0);
                                    c.setFill(colorCu);
                                } 
                                if (jaque){
                                    this.pieza = (Pieza)matriz[reypos[0]][reypos[1]].getChildren().get(1);   
                                    Rey king = (Rey) this.pieza;
                                    this.movimientosValidos = king.movimientosRestantes(matriz, allMovesPosibles);
                                }
                                else{
                                    this.pieza = actual;
                                        if (this.pieza instanceof Rey){
                                            Rey king = (Rey)this.pieza;
                                            this.movimientosValidos=  king.movimientosRestantes(matriz, allMovesPosibles);
                                        }
                                        else
                                            this.movimientosValidos = this.pieza.movimientos_posibles(matriz);
                                }
                                for (int[] m : this.movimientosValidos) {
                                    Rectangle c = (Rectangle) this.matriz[m[0]][m[1]].getChildren().get(0);
                                    c.setFill(Color.rgb(97, 166, 120));
                                }
                            }
                        } 
                        else {
                            if (this.pieza != null && valido) {
                                this.pieza.mover(cuadropos, matriz);
                                if (this.pieza instanceof Rey){
                                    if(turnoBlanco){
                                        this.ReyBlanco.setXpos(this.pieza.getXpos());
                                        this.ReyBlanco.setYpos( this.pieza.getYpos());
                                    }
                                    else{
                                        this.ReyNegro.setXpos(this.pieza.getXpos());
                                        this.ReyNegro.setYpos( this.pieza.getYpos());
                                    }
                                    if (jaque){
                                        jaque = false;
                                    }
                                }
                                for (int[] m : this.movimientosValidos) {

                                    Platform.runLater(() -> {
                                        Color colorCu = this.matriz[m[0]][m[1]].getColor();
                                        Rectangle c = (Rectangle) this.matriz[m[0]][m[1]].getChildren().get(0);
                                        c.setFill(colorCu);
                                    }
                                    );
                                }
                                this.pieza = null;
                                this.movimientosValidos.clear();
                                this.turnoBlanco = !this.turnoBlanco;
                                this.allMovesPosibles = Pieza.allMovimientosPosibles(matriz, !turnoBlanco);
                            }

                        }
                    }
                    //solucion temporal (Arreglen esto xd)
                    else{
                        Alert a;
                        if (trabas){
                            a = new Alert(Alert.AlertType.CONFIRMATION,"Empate por rey Ahogado");
                        }
                        else if (ganador){
                            a = new Alert(Alert.AlertType.CONFIRMATION,"Ganaron las Blancas");
                        }
                        else{
                            a = new Alert(Alert.AlertType.CONFIRMATION,"Ganaron las Negras");
                        }
                        a.show();
                    }
                }
                );
                count++;
            }
        }
        this.ReyBlanco = (Rey)matriz[7][4].getChildren().get(1);
        this.ReyNegro = (Rey)matriz[0][4].getChildren().get(1);
        Reloj h1 = new Reloj(relojNegro, relojBlanco);
        h1.start();
    }
//	public class Reloj extends Thread{
//        private int minutos;
//        private int segundos;
////        private Label reloj;
////        private boolean turno;
//        
//        
//        public Reloj(Label reloj,boolean turno){
////            this.reloj = reloj;
//            this.minutos = 10;
//            this.segundos = 0;
//            this.turno = turno;
//        }
//        
//        @Override
//        public void run(){
//            while(turnoBlanco)
//            while (minutos > 0 || segundos > 0){
//                 try {
//                     Thread.sleep(1000);
//                 } catch (InterruptedException ex) {
//                 ex.printStackTrace();
//                 }
//                 if (segundos == 0){
//                     minutos--;
//                     segundos = 60;
//                 }
//                 segundos--;
//                 if (segundos < 10){
//                     Platform.runLater(()->{
//                         reloj.setText(minutos+":0"+segundos);
//                     });
//                 }   
//                 else{
//                     Platform.runLater(()->{
//                         reloj.setText(minutos+":"+segundos);
//                     });
//                 }
//             }
//        }
//    }

    public class Reloj extends Thread {

        private Label relojNegro;
        private Label relojBlanco;
        private int minutosNegro;
        private int segundosNegro;
        private int minutosBlanco;
        private int segundosBlanco;

        public Reloj(Label relojNegro, Label relojBlanco) {
            this.relojNegro = relojNegro;
            this.relojBlanco = relojBlanco;
            this.minutosNegro = 10;
            this.minutosBlanco = 10;
            this.segundosBlanco = 0;
            this.segundosNegro = 0;

        }

        @Override
        public void run() {
            while ((minutosNegro > 0 || segundosNegro > 0) && (minutosBlanco > 0 || segundosBlanco > 0)) {
                while (turnoBlanco) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    if (segundosBlanco == 0) {
                        minutosBlanco--;
                        segundosBlanco = 60;
                    }
                    segundosBlanco--;
                    if (segundosBlanco < 10) {
                        Platform.runLater(() -> {
                            relojBlanco.setText(minutosBlanco + ":0" + segundosBlanco);
                        });
                    } else {
                        Platform.runLater(() -> {
                            relojBlanco.setText(minutosBlanco + ":" + segundosBlanco);
                        });
                    }

                    while (!turnoBlanco) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        if (segundosNegro == 0) {
                            minutosNegro--;
                            segundosNegro = 60;
                        }
                        segundosNegro--;
                        if (segundosNegro < 10) {
                            Platform.runLater(() -> {
                                relojNegro.setText(minutosNegro + ":0" + segundosNegro);
                            });
                        } else {
                            Platform.runLater(() -> {
                                relojNegro.setText(minutosNegro + ":" + segundosNegro);
                            });
                        }
                    }

                }
            }
        }
    }

    public void llenarTablero(Cuadro cuadro, Rectangle r) {
        int x = cuadro.getXpos();
        int y = cuadro.getYpos();
        Pieza p = null;
        if (x == 1) {
            p = new Peon(TipoColor.Negro, x, y);
        } else if (x == 6) {
            p = new Peon(TipoColor.Blanco, x, y);
        } else if (x == 7 && (y == 0 || y == 7)) {
            p = new Torre(TipoColor.Blanco, x, y);
        } else if (x == 0 && (y == 0 || y == 7)) {
            p = new Torre(TipoColor.Negro, x, y);
        } else if (x == 7 && (y == 1 || y == 6)) {
            p = new Caballo(TipoColor.Blanco, x, y);
        } else if (x == 0 && (y == 1 || y == 6)) {
            p = new Caballo(TipoColor.Negro, x, y);
        } else if (x == 7 && (y == 2 || y == 5)) {
            p = new Alfil(TipoColor.Blanco, x, y);
        } else if (x == 0 && (y == 2 || y == 5)) {
            p = new Alfil(TipoColor.Negro, x, y);
        } else if (x == 7 && y == 3) {
            p = new Dama(TipoColor.Blanco, x, y);
        } else if (x == 0 && y == 3) {
            p = new Dama(TipoColor.Negro, x, y);
        } else if (x == 7 && y == 4) {
            p = new Rey(TipoColor.Blanco, x, y);
        } else if (x == 0 && y == 4) {
            p = new Rey(TipoColor.Negro, x, y);
        }
        if (p == null) {
            cuadro.getChildren().addAll(r);
            tablero.add(cuadro, y, x);
            matriz[x][y] = cuadro;
        } else {
            cuadro.getChildren().addAll(r, p);
            cuadro.setOcupado(true);
            tablero.add(cuadro, y, x);
            matriz[x][y] = cuadro;
        }
    }
    
}
