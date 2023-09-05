/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.clases_ajedres;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author josel
 */
public class Pieza extends ImageView{
    
    protected TipoColor color;
    protected int xpos;
    protected int ypos;

    
    public Pieza(TipoColor color,int xpos,int ypos){
        this.color = color;
        this.xpos = xpos;
        this.ypos = ypos;
    }

   
    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    public TipoColor getColor() {
        return color;
    }

    public int getXpos() {
        return xpos;
    }

    public int getYpos() {
        return ypos;
    }
     

    public  ArrayList<int[]> movimientos_posibles(Cuadro[][] tablero){
        return null;
    }
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
    }
    
    public static boolean inMovimientos(ArrayList<int[]> movimientos, int[] posicion){
        for (int[] mov: movimientos){
            if (mov[0] == posicion[0] && mov[1] == posicion[1])
                return true;
        }
        
        return false;
    }
    
    public static ArrayList<int[]> allMovimientosPosibles(Cuadro[][] matriz,boolean turno){
        ArrayList<int[]> allMoves = new ArrayList<>();
        if (turno){
            for (Cuadro[] f: matriz){
                for (Cuadro c: f){
                    if (c.isOcupado()){
                        Pieza p = (Pieza)c.getChildren().get(1);
                        if(p.getColor().equals(TipoColor.Blanco)){
                            ArrayList<int[]> moves = p.movimientos_posibles(matriz);
                            allMoves.addAll(moves);
                        }
                    }
                }
            }
        }
        else{
            for (Cuadro[] f: matriz){
                for (Cuadro c: f){
                    if (c.isOcupado()){
                        Pieza p = (Pieza)c.getChildren().get(1);
                        if(p.getColor().equals(TipoColor.Negro)){
                            ArrayList<int[]> moves = p.movimientos_posibles(matriz);
                            allMoves.addAll(moves);
                        }
                    }
                }
            }
        }
        return allMoves;
    }
}
    
    
    

