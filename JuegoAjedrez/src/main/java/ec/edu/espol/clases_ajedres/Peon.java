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
public class Peon extends Pieza {

    private boolean primerMovimiento;

    public Peon(TipoColor color, int xpos, int ypos) {
        super(color, xpos, ypos);
        if(color.equals(TipoColor.Blanco))
            this.setImage(new Image("img/whitePawn.png"));   
        else 
            this.setImage(new Image("img/blackPawn.png"));
    }

    @Override
    public ArrayList<int[]> movimientos_posibles() {
        ArrayList<int[]> movimientos_posibles = new ArrayList<int[]>();

        if (super.ypos < 7) {
            movimientos_posibles.add(new int[]{super.xpos, super.ypos + 1});
        }

        // Movimiento inicial
        if (primerMovimiento && super.ypos < 6) {
            movimientos_posibles.add(new int[]{super.xpos, super.ypos + 2});
        }

        // Captura
        if (super.ypos < 7) {
            if (super.xpos > 0) {
                movimientos_posibles.add(new int[]{super.xpos - 1, super.ypos + 1});
            }
            if (super.xpos < 7) {
                movimientos_posibles.add(new int[]{super.xpos + 1, super.ypos + 1});
            }
        }

        return movimientos_posibles;
    }

    @Override
    public int[] eliminarPieza(Pieza p) {
        int[] listPos = {-1,-1};
            int distx = this.xpos - p.xpos;
            int disty = this.ypos - p.ypos;
            if ((distx == 1 || distx == -1) && (disty == 1 || disty == -1)&&(!this.color.equals(p.color))) {
                listPos = new int[]{p.xpos, p.ypos};
            }
        return listPos;
    }

}
