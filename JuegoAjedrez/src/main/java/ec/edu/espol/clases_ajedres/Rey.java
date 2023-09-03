/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.clases_ajedres;

import java.util.ArrayList;

/**
 *
 * @author angelozurita
 */
public class Rey extends Pieza {

    public Rey(TipoColor color, int xpos, int ypos) {
        super(color, xpos, ypos);
    }

    @Override
    public ArrayList<int[]> movimientos_posibles() {
        
        int[][] movimientos = {
        {super.xpos + 1, super.ypos}, {super.xpos - 1, super.ypos}, {super.xpos, super.ypos + 1}, {super.xpos, super.ypos - 1},
        {super.xpos + 1, super.ypos + 1}, {super.xpos - 1, super.ypos - 1}, {super.xpos + 1, super.ypos - 1}, { super.xpos- 1, super.ypos + 1}};
        ArrayList<int[]> movimiento_posibles = new ArrayList<int[]>();

        for (int[] movimiento : movimientos) {
            if (movimiento[0] >= 0 && movimiento[0] <= 7 && movimiento[1] >= 0 && movimiento[1] <= 7) {
                movimiento_posibles.add(movimiento);
            }
    }
    return movimiento_posibles;
        
    }
    
    
}
