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
public class Alfil extends Pieza{   
    public Alfil(TipoColor color, int xpos, int ypos) {
        super(color, xpos, ypos);
        if(color.equals(TipoColor.Blanco))
            this.setImage(new Image("img/whiteBishop.png"));   
        else 
            this.setImage(new Image("img/blackBishop.png"));
    }
    

    @Override
    public ArrayList<int[]> movimientos_posibles(Cuadro[][] tablero) {
        
        ArrayList<int[]> movimientosPosibles = new ArrayList<int[]>();
        

        for (int i = 1; super.xpos + i <= 7 && super.ypos + i <= 7; i++) {
            int[]mov = {super.xpos + i, super.ypos + i};
            if((tablero[mov[0]][mov[1]]).isOcupado()){
                if(((Pieza)(tablero[mov[0]][mov[1]]).getChildren().get(1)).getColor().equals(this.color))
                    break;
                else if (!(((Pieza)(tablero[mov[0]][mov[1]]).getChildren().get(1)).getColor().equals(this.color)))
                {
                    movimientosPosibles.add(mov);
//                    comerficha();
                    break;
                }}
            movimientosPosibles.add(mov);
        }
       
        for (int i = 1; super.xpos - i >= 0 && super.ypos + i <= 7; i++) {
            int[]mov = {super.xpos - i, super.ypos + i};
            if((tablero[mov[0]][mov[1]]).isOcupado()){
                if(((Pieza)(tablero[mov[0]][mov[1]]).getChildren().get(1)).getColor().equals(this.color))
                    break;
                else if (!(((Pieza)(tablero[mov[0]][mov[1]]).getChildren().get(1)).getColor().equals(this.color)))
                {
                    movimientosPosibles.add(mov);
//                    comerficha();
                    break;
                }}
            movimientosPosibles.add(mov);
        }
            
       
        for (int i = 1; super.xpos + i <= 7 && super.ypos - i >= 0; i++) {
            int[]mov = {super.xpos + i, super.ypos - i};
            if((tablero[mov[0]][mov[1]]).isOcupado()){
                if(((Pieza)(tablero[mov[0]][mov[1]]).getChildren().get(1)).getColor().equals(this.color))
                    break;
                else if (!(((Pieza)(tablero[mov[0]][mov[1]]).getChildren().get(1)).getColor().equals(this.color)))
                {
                    movimientosPosibles.add(mov);
//                    comerficha();
                    break;
                }}
            movimientosPosibles.add(mov);
        }
        
        for (int i = 1; super.xpos - i >= 0 && super.ypos - i >= 0; i++) {
            int[]mov = {super.xpos - i, super.ypos - i};
            if((tablero[mov[0]][mov[1]]).isOcupado()){
                if(((Pieza)(tablero[mov[0]][mov[1]]).getChildren().get(1)).getColor().equals(this.color))
                    break;
                else if (!(((Pieza)(tablero[mov[0]][mov[1]]).getChildren().get(1)).getColor().equals(this.color)))
                {
                    movimientosPosibles.add(mov);
//                    comerficha();
                    break;
                }}
            movimientosPosibles.add(mov);
        }
        
        
        return movimientosPosibles;
    }
    @Override
        public void eliminarPieza(Pieza p, Cuadro [][] tablero) {
       
        int distx = Math.abs(this.xpos - p.xpos);
        int disty = Math.abs(this.ypos - p.ypos);

        if (distx == disty && !this.color.equals(p.color)) {
           tablero[p.xpos][p.ypos].getChildren().remove(1);
                tablero[p.xpos][p.ypos].getChildren().add((Pieza)tablero[this.xpos][this.ypos].getChildren().get(1));
                tablero[this.xpos][this.ypos].getChildren().remove(1);
        }else{
            Alert a =new Alert(Alert.AlertType.CONFIRMATION, "No es posible eliminar la ficha");
            a.show();
        }

     
    }
    
}
