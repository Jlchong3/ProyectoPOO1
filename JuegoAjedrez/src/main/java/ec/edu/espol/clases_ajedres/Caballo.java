/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.clases_ajedres;

import ec.edu.espol.clases_ajedres.Pieza;
import ec.edu.espol.clases_ajedres.TipoColor;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

/**
 *
 * @author angelozurita
 */
public class Caballo extends Pieza {

    public Caballo(TipoColor color, int xpos, int ypos) {
        super(color, xpos, ypos);
        if(color.equals(TipoColor.Blanco))
            this.setImage(new Image("img/whiteKnight.png"));   
        else 
            this.setImage(new Image("img/blackKnight.png"));
    }

    public ArrayList<int[]> movimientos_posibles(Cuadro[][] tablero) {
        int[][] movimientos = {
            {super.xpos + 2, super.ypos + 1}, {super.xpos + 2, super.ypos - 1},
            {super.xpos - 2, super.ypos + 1}, {super.xpos - 2, super.ypos - 1},
            {super.xpos + 1, super.ypos + 2}, {super.xpos + 1, super.ypos - 2},
            {super.xpos - 1, super.ypos + 2}, {super.xpos - 1, super.ypos - 2}
        };

        ArrayList<int[]> movimientosPosibles = new ArrayList<int[]>();

        for (int[] movimiento : movimientos) {
            if (movimiento[0] >= 0 && movimiento[0] < 8 && movimiento[1] >= 0 && movimiento[1] < 8) {
                if((tablero[movimiento[0]][movimiento[1]]).isOcupado()){
                    if(((Pieza)(tablero[movimiento[0]][movimiento[1]]).getChildren().get(1)).getColor().equals(this.color))
                        continue;
                    else if (!(((Pieza)(tablero[movimiento[0]][movimiento[1]]).getChildren().get(1)).getColor().equals(this.color)))
                    {
                        movimientosPosibles.add(movimiento);
//                        comerficha();
                        continue;
                    }}
                movimientosPosibles.add(movimiento);                  
            }
        }
        return movimientosPosibles; 
    }

    @Override
        public void eliminarPieza(Pieza p, Cuadro [][] tablero) {
     
        int distx = Math.abs(this.xpos - p.xpos);
        int disty = Math.abs(this.ypos - p.ypos);

       
        if ((distx == 1 && disty == 2) || (distx == 2 && disty == 1)) {
            if (!this.color.equals(p.color)) {
                tablero[p.xpos][p.ypos].getChildren().remove(1);
                tablero[p.xpos][p.ypos].getChildren().add((Pieza)tablero[this.xpos][this.ypos].getChildren().get(1));
                tablero[this.xpos][this.ypos].getChildren().remove(1);
                this.xpos = p.xpos;
                this.ypos = p.ypos;
            }else{
            Alert a =new Alert(Alert.AlertType.CONFIRMATION, "No es posible eliminar la ficha");
            a.show();
        }
        }

    
    }

}
