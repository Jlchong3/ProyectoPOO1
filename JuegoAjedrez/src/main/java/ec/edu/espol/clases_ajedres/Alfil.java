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
public class Alfil extends Pieza{

    public Alfil(TipoColor color, int xpos, int ypos) {
        super(color, xpos, ypos);
    }

    @Override
    public ArrayList<int[]> movimientos_posibles() {
        
        ArrayList<int[]> movimientosValidos = new ArrayList<int[]>();
        

        for (int i = 1; super.xpos + i <= 7 && super.ypos + i <= 7; i++) {
            int[]mov = {super.xpos + i, super.ypos + i};
            movimientosValidos.add(mov);
        }
       
        for (int i = 1; super.xpos - i >= 0 && super.ypos + i <= 7; i++) {
            int[]mov = {super.xpos - i, super.ypos + i};
            movimientosValidos.add(mov);
        }
       
        for (int i = 1; super.xpos + i <= 7 && super.ypos - i >= 0; i++) {
            int[]mov = {super.xpos + i, super.ypos - i};
            movimientosValidos.add(mov);
        }
        
        for (int i = 1; super.xpos - i >= 0 && super.ypos - i >= 0; i++) {
            int[]mov = {super.xpos - i, super.ypos - i};
            movimientosValidos.add(mov);
        }
        
        
        return movimientosValidos;
    }
    @Override
    public int[] eliminarPieza(Pieza p) {
        int[] listPos = {-1, -1};
        int distx = Math.abs(this.xpos - p.xpos);
        int disty = Math.abs(this.ypos - p.ypos);

        if (distx == disty && !this.color.equals(p.color)) {
            listPos = new int[]{p.xpos, p.ypos};
        }

        return listPos;
        
        
        
    }
    
}
