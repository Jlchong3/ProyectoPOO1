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
public class Torre extends Pieza {

    public Torre(TipoColor color, int xpos, int ypos) {
        super(color, xpos, ypos);
    }

    @Override
    public ArrayList<int[]> movimientos_posibles() {

        ArrayList<int[]> movimiento_posibles = new ArrayList<int[]>();
        for (int i = super.ypos + 1; i <= 7; i++) {
            movimiento_posibles.add(new int[]{super.xpos, i});
        }

        for (int i = super.ypos - 1; i >= 0; i--) {
            movimiento_posibles.add(new int[]{super.xpos, i});
        }

        for (int i = super.xpos + 1; i <= 7; i++) {
            movimiento_posibles.add(new int[]{i, super.ypos});
        }

        for (int i = super.xpos - 1; i >= 0; i--) {
            movimiento_posibles.add(new int[]{i, super.ypos});
        }
        return movimiento_posibles;
    }

    @Override
    public int[] eliminarPieza(Pieza p) {
        int[] listPos = {-1, -1};
        int distx = this.xpos - p.xpos;
        int disty =this.ypos - p.ypos;

        if ((distx == 0 || disty == 0) && (!this.color.equals(p.color))) {
            listPos = new int[]{p.xpos, p.ypos};
        }

        return listPos;
    }
}
