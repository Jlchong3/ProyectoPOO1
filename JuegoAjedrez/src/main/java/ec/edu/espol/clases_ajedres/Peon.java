/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.clases_ajedres;

import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

/**
 *
 * @author angelozurita
 */
public class Peon extends Pieza {

    private boolean primerMovimiento;

    public Peon(TipoColor color, int xpos, int ypos) {
        super(color, xpos, ypos);
        this.primerMovimiento = true;
        if(color.equals(TipoColor.Blanco))
            this.setImage(new Image("img/whitePawn.png"));   
        else 
            this.setImage(new Image("img/blackPawn.png"));
    }

    
    @Override
    public ArrayList<int[]> movimientos_posibles(Cuadro[][] tablero) {
        ArrayList<int[]> movimientos_posibles = new ArrayList<int[]>();
    if (color.equals(TipoColor.Negro) ) {
        // Mover hacia adelante
        if (this.xpos < 7 && !(tablero[this.xpos+1][this.ypos].isOcupado())){
            movimientos_posibles.add(new int[]{this.xpos+1, this.ypos});
            
            // Mover dos espacios desde la posición inicial
            if (primerMovimiento && !(tablero[this.xpos+2][this.ypos].isOcupado())) {
                movimientos_posibles.add(new int[]{this.xpos+2, this.ypos});
            }
        }
        
        // Captura diagonal
        if (this.ypos > 0 && this.xpos < 7 &&(tablero[this.xpos+1][this.ypos-1].isOcupado()) && ((Pieza)(tablero[this.xpos+1][this.ypos-1]).getChildren().get(1)).getColor().equals(TipoColor.Blanco)) {
            movimientos_posibles.add(new int[]{this.xpos+1, this.ypos-1});
        }
        if ( this.xpos < 7 && this.ypos<7 &&(tablero[this.xpos+1][this.ypos+1].isOcupado()) && ((Pieza)(tablero[this.xpos+1][this.ypos+1]).getChildren().get(1)).getColor().equals(TipoColor.Blanco)) {
            movimientos_posibles.add(new int[]{this.xpos+1, this.ypos+1});
        }
//        if (this.ypos < 7 && this.xpos < 7 && (tablero[this.xpos+1][this.ypos+1].isOcupado()) && ((Pieza)(tablero[this.xpos+1][this.ypos-1]).getChildren().get(1)).getColor().equals(TipoColor.Blanco)) {
//            movimientos_posibles.add(new int[]{this.xpos+1, this.ypos+1});
//        }
    }
    // Para un peón blanco
    else {
        // Mover hacia adelante
        if (this.xpos > 0 && !(tablero[this.xpos-1][this.ypos].isOcupado())) {
            movimientos_posibles.add(new int[]{this.xpos-1, this.ypos});
            
            // Mover dos espacios desde la posición inicial
            if (primerMovimiento && !(tablero[this.xpos-2][this.ypos].isOcupado())) {
                movimientos_posibles.add(new int[]{this.xpos-2, this.ypos});
            }
        }
        
        // Captura diagonal
        if (this.ypos > 0 && this.xpos > 0 && (tablero[this.xpos-1][this.ypos-1].isOcupado()) && ((Pieza)(tablero[this.xpos-1][this.ypos-1]).getChildren().get(1)).getColor().equals(TipoColor.Negro)) {
            movimientos_posibles.add(new int[]{this.xpos-1, this.ypos-1});
        }
        if (this.ypos < 7 && this.xpos > 0 && (tablero[this.xpos-1][this.ypos+1].isOcupado()) && ((Pieza)(tablero[this.xpos-1][this.ypos+1]).getChildren().get(1)).getColor().equals(TipoColor.Negro))  {
            movimientos_posibles.add(new int[]{this.xpos-1, this.ypos+1});
        }
  
    }
        return movimientos_posibles;
 }

    @Override
    public void eliminarPieza(Pieza p, Cuadro [][] tablero) {
        tablero[p.xpos][p.ypos].getChildren().remove(1);
        tablero[this.xpos][this.ypos].getChildren().remove(1);
        tablero[this.xpos][this.ypos].setOcupado(false);
        tablero[p.xpos][p.ypos].getChildren().add(this);
        tablero[p.xpos][p.ypos].setOcupado(true);
        this.xpos = p.xpos;
        this.ypos = p.ypos;
    }
    
    public void mover(int[] mov,Cuadro[][] tablero){
        tablero[this.xpos][this.ypos].getChildren().remove(1);
        tablero[this.xpos][this.ypos].setOcupado(false);
        tablero[mov[0]][mov[1]].getChildren().add(this);
        tablero[mov[0]][mov[1]].setOcupado(true);
        this.xpos = mov[0];
        this.ypos = mov[1];
        this.primerMovimiento = false;
    } 
}
