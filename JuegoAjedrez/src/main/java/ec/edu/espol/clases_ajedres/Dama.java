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
public class Dama extends Pieza {

    public Dama(TipoColor color, int xpos, int ypos) {
        super(color, xpos, ypos);
        if(color.equals(TipoColor.Blanco))
            this.setImage(new Image("img/whiteQueen.png"));   
        else 
            this.setImage(new Image("img/blackQueen.png"));
    }

    @Override
    public ArrayList<int[]> movimientos_posibles(Cuadro[][] tablero) {
        ArrayList<int[]> movimientosPosibles = new ArrayList<int[]>();

        // Movimientos en línea recta
        // Movimientos hacia derecha
        for (int i = super.ypos + 1; i <=7; i++) {
//            movimientosPosibles.add(new int[]{super.xpos, i});
            int[]mov = {super.xpos, i};
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
        // Movimientos hacia izquierda
        for (int i = super.ypos - 1; i >= 0; i--) {
//            movimientosPosibles.add(new int[]{super.xpos, i});
            int[]mov = {super.xpos, i};
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

        // Movimientos hacia la abajo
        for (int i = super.xpos + 1; i <= 7; i++) {
//            movimientosPosibles.add(new int[]{i, super.ypos});
            int[]mov = {i, super.ypos};
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

        // Movimientos hacia la arriba
        for (int i = super.xpos - 1; i >= 0; i--) {
//            movimientosPosibles.add(new int[]{i, super.ypos});
            int[]mov = {i, super.ypos};
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

        // Movimientos en diagonal
        // Movimientos en diagonal hacia abajo-derecha
        for (int i = 1; super.xpos + i <= 7 && super.ypos + i <= 7; i++) {
//            movimientosPosibles.add(new int[]{super.xpos + i, super.ypos + i});
            int[]mov = {super.xpos + i, super.ypos + i};
            if((tablero[mov[0]][mov[1]]).isOcupado()){
                if(((Pieza)(tablero[mov[0]][mov[1]]).getChildren().get(1)).getColor().equals(this.color)){
                    break;}
                else if (!(((Pieza)(tablero[mov[0]][mov[1]]).getChildren().get(1)).getColor().equals(this.color)))
                {
                    movimientosPosibles.add(mov);
//                    comerficha();
                    break;
                }}
            movimientosPosibles.add(mov);              
        }

        // Movimientos en diagonal hacia arriba-derecha
        for (int i = 1; super.xpos - i >= 0 && super.ypos + i <= 7; i++) {
//            movimientosPosibles.add(new int[]{super.xpos - i, super.ypos + i});
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

        // Movimientos en diagonal hacia abajo-derecha
        for (int i = 1; super.xpos + i <= 7 && super.ypos - i >= 0; i++) {
//            movimientosPosibles.add(new int[]{super.xpos + i, super.ypos - i});
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

        // Movimientos en diagonal hacia arriba-izquierda
        for (int i = 1; super.xpos - i >= 0 && super.ypos - i >= 0; i++) {
//            movimientosPosibles.add(new int[]{super.xpos - i, super.ypos - i});
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

//    @Override
//        public void eliminarPieza(Pieza p, Cuadro [][] tablero) {
//        int distx = Math.abs(this.xpos - p.xpos);
//        int disty = Math.abs(this.ypos - p.ypos);
//
//        if ((distx == 0 || disty == 0 || distx == disty) && (!this.color.equals(p.color))) {
//            tablero[p.xpos][p.ypos].getChildren().remove(1);
//                tablero[p.xpos][p.ypos].getChildren().add((Pieza)tablero[this.xpos][this.ypos].getChildren().get(1));
//                tablero[this.xpos][this.ypos].getChildren().remove(1);
//                this.xpos = p.xpos;
//                this.ypos = p.ypos;
//        }else{
//            Alert a =new Alert(Alert.AlertType.CONFIRMATION, "No es posible eliminar la ficha");
//            a.show();
//        }
//      
//    }
//
}
