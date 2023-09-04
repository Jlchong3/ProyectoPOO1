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

        int[][] movimientos = {
            {super.xpos + 1, super.ypos}, {super.xpos - 1, super.ypos}, {super.xpos, super.ypos + 1}, {super.xpos, super.ypos - 1},
            {super.xpos + 1, super.ypos + 1}, {super.xpos - 1, super.ypos - 1}, {super.xpos + 1, super.ypos - 1}, {super.xpos - 1, super.ypos + 1}};
        ArrayList<int[]> movimientoPosibles = new ArrayList<int[]>();

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
