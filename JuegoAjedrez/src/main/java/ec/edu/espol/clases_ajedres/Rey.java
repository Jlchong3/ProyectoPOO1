/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.clases_ajedres;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

/**
 *
 * @author angelozurita
 */
public class Rey extends Pieza {

    public Rey(TipoColor color, int xpos, int ypos) {
        super(color, xpos, ypos);
        if(color.equals(TipoColor.Blanco))
            this.setImage(new Image("img/whiteKing.png"));   
        else 
            this.setImage(new Image("img/blackKing.png"));
    }

    @Override
    public ArrayList<int[]> movimientos_posibles(Cuadro[][] tablero) {
        
        ArrayList<int[]> movimientos = this.getMovimientosSinValidar();
        ArrayList<int[]> movimientoPosibles = new ArrayList<>();

        for (int[] movimiento : movimientos) {
            if (movimiento[0] >= 0 && movimiento[0] <= 7 && movimiento[1] >= 0 && movimiento[1] <= 7) {
                if((tablero[movimiento[0]][movimiento[1]]).isOcupado()){
                    if(((Pieza)(tablero[movimiento[0]][movimiento[1]]).getChildren().get(1)).getColor().equals(this.color))
                        continue;
                    else if (!(((Pieza)(tablero[movimiento[0]][movimiento[1]]).getChildren().get(1)).getColor().equals(this.color)))
                    {
                        movimientoPosibles.add(movimiento);
//                        comerficha();
                        continue;
                    }}
                movimientoPosibles.add(movimiento);                 
            }
        }
        return movimientoPosibles;

    }
    public ArrayList<int[]> getMovimientosSinValidar(){
        ArrayList<int[]> moves = new ArrayList<>();
        int[][] movimientos = {
            {super.xpos + 1, super.ypos}, {super.xpos - 1, super.ypos}, {super.xpos, super.ypos + 1}, {super.xpos, super.ypos - 1},
            {super.xpos + 1, super.ypos + 1}, {super.xpos - 1, super.ypos - 1}, {super.xpos + 1, super.ypos - 1}, {super.xpos - 1, super.ypos + 1}};
        for (int[] m : movimientos){
           moves.add(m); 
        }
        return moves;
    }
    
    public ArrayList<int[]> movimientosRestantes(Cuadro[][] tablero, ArrayList<int[]> movRivales){
        ArrayList<int[]> movJaque = new ArrayList<>();
        ArrayList<int[]> mov = this.movimientos_posibles(tablero);
        for (int[] m : mov){
            if (!Pieza.inMovimientos(movRivales, m))
                movJaque.add(m);
        }
        return movJaque;     
    }
    
    
    public boolean trabas(Cuadro[][] tablero, ArrayList<int[]> movRivales){
        ArrayList<int[]> piezasPos = new ArrayList<>();
        ArrayList<int[]> filtro = new ArrayList<>();
        Pieza p;
        int[] xy = new int[2];       
        for (Cuadro[] z : tablero){
            for (Cuadro c : z){     
                if (c.isOcupado()){
                    p = (Pieza)c.getChildren().get(1);
                    if(p.getColor().equals(this.color)){
                        xy[0] = p.getXpos();
                        xy[1] = p.getYpos();
                        piezasPos.add(xy);
                    }
                }     
            }
        }
        for (int[] m: this.getMovimientosSinValidar()){
            if (!Pieza.inMovimientos(piezasPos, m))
                filtro.add(m);
        }
        if (filtro.isEmpty()){
            return false;
        }
        else{
            ArrayList<int[]> filtro2 = new ArrayList<>();
            for (int[] f: filtro){
                if(!Pieza.inMovimientos(movRivales, f))
                    filtro2.add(f);
            }
            return filtro2.isEmpty();
        }
    }
    
    

//    @Override
//    public void eliminarPieza(Pieza p,Cuadro[][] tablero){
//        int distx = this.xpos - p.xpos;
//        int disty = this.ypos - p.ypos;
//
//        if ((distx == 1 && disty == 1 && distx == 0 && disty == 0) && (!this.color.equals(p.color))) {
//         tablero[p.xpos][p.ypos].getChildren().remove(1);
//                tablero[p.xpos][p.ypos].getChildren().add((Pieza)tablero[this.xpos][this.ypos].getChildren().get(1));
//                tablero[this.xpos][this.ypos].getChildren().remove(1);
//                this.xpos = p.xpos;
//                this.ypos = p.ypos;
//        } else{
//            Alert a =new Alert(Alert.AlertType.CONFIRMATION, "No es posible eliminar la ficha");
//            a.show();
//        }
//
//
//    }

}
