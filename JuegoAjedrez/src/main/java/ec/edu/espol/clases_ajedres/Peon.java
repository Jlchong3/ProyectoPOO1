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
public class Peon extends Pieza {
    private boolean primerMovimiento;
    public Peon(TipoColor color, int xpos, int ypos) {
        super(color, xpos, ypos);
    }

    @Override
    public ArrayList<int[]> movimientos_posibles() {
        ArrayList<int[]> movimientos_posibles = new ArrayList<int[]>();
    
        if (super.ypos < 7) {
            movimientos_posibles.add(new int[] {super.xpos, super.ypos + 1});
        }
        
        // Movimiento inicial
        if (primerMovimiento && super.ypos < 6) {
            movimientos_posibles.add(new int[] {super.xpos, super.ypos + 2});
        }
        
        // Captura
        if (super.ypos < 7) {
            if (super.xpos > 0) {
                movimientos_posibles.add(new int[] {super.xpos - 1, super.ypos + 1});
            }
            if (super.xpos < 7) {
                movimientos_posibles.add(new int[] {super.xpos + 1, super.ypos + 1});
            }
        }
    
        return movimientos_posibles;
    }
    
    
}
