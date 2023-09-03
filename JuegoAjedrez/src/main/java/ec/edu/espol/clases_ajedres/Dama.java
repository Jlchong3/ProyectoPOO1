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
public class Dama extends Pieza {

    public Dama(TipoColor color, int xpos, int ypos) {
        super(color, xpos, ypos);
    }

    @Override
    public ArrayList<int[]> movimientos_posibles() {
        ArrayList<int[]> movimientos_posibles = new ArrayList<int[]>();

        // Movimientos en línea recta
        // Movimientos hacia arriba
        for (int i = super.ypos + 1; i <= 7; i++) {
            movimientos_posibles.add(new int[]{super.xpos, i});
        }

        // Movimientos hacia abajo
        for (int i = super.ypos - 1; i >= 0; i--) {
            movimientos_posibles.add(new int[]{super.xpos, i});
        }

        // Movimientos hacia la derecha
        for (int i = super.xpos + 1; i <= 7; i++) {
            movimientos_posibles.add(new int[]{i, super.ypos});
        }

        // Movimientos hacia la izquierda
        for (int i = super.xpos - 1; i >= 0; i--) {
            movimientos_posibles.add(new int[]{i, super.ypos});
        }

        // Movimientos en diagonal
        // Movimientos en diagonal hacia arriba-derecha
        for (int i = 1; super.xpos + i <= 7 && super.ypos + i <= 7; i++) {
            movimientos_posibles.add(new int[]{super.xpos + i, super.ypos + i});
        }

        // Movimientos en diagonal hacia arriba-izquierda
        for (int i = 1; super.xpos - i >= 0 && super.ypos + i <= 7; i++) {
            movimientos_posibles.add(new int[]{super.xpos - i, super.ypos + i});
        }

        // Movimientos en diagonal hacia abajo-derecha
        for (int i = 1; super.xpos + i <= 7 && super.ypos - i >= 0; i++) {
            movimientos_posibles.add(new int[]{super.xpos + i, super.ypos - i});
        }

        // Movimientos en diagonal hacia abajo-izquierda
        for (int i = 1; super.xpos - i >= 0 && super.ypos - i >= 0; i++) {
            movimientos_posibles.add(new int[]{super.xpos - i, super.ypos - i});
        }

        return movimientos_posibles;
    }

    @Override
    public int[] eliminarPieza(Pieza p) {
        int[] listPos = {-1, -1};
        int distx = Math.abs(this.xpos - p.xpos);
        int disty = Math.abs(this.ypos - p.ypos);

        if ((distx == 0 || disty == 0 || distx == disty) && (!this.color.equals(p.color))) {
            listPos = new int[]{p.xpos, p.ypos};
        }

        return listPos;
    }

}
