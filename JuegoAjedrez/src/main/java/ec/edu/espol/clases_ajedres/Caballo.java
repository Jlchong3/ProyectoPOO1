/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.clases_ajedres;
import ec.edu.espol.clases_ajedres.Pieza;
import ec.edu.espol.clases_ajedres.TipoColor;
import java.util.ArrayList;
import javafx.scene.Node;
/**
 *
 * @author angelozurita
 */
public class Caballo extends Pieza{

    public Caballo(TipoColor color, int xpos, int ypos) {
        super(color, xpos, ypos);
    }

    public ArrayList<int[]> movimientos_posibles() {
        int[][] movimientos = {
            {super.xpos + 2, super.ypos + 1}, {super.xpos + 2, super.ypos - 1},
            {super.xpos - 2, super.ypos + 1}, {super.xpos - 2, super.ypos - 1},
            {super.xpos + 1, super.ypos + 2}, {super.xpos + 1, super.ypos - 2},
            {super.xpos - 1, super.ypos + 2}, {super.xpos - 1, super.ypos - 2}
        };
        
        ArrayList<int[]> movimiento_posibles = new ArrayList<int[]>();
        
        for (int[] movimiento : movimientos) {
            if (movimiento[0] >= 0 && movimiento[0] < 8 && movimiento[1] >= 0 && movimiento[1] < 8) {
                movimiento_posibles.add(movimiento);
            }
        }
        return movimiento_posibles; // falta validar si esta ocupado
    }


}
