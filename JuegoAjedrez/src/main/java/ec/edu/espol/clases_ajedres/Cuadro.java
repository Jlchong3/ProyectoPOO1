/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.clases_ajedres;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author josel
 */
public class Cuadro extends StackPane{
    private int xpos;
    private int ypos;
    private boolean ocupado;
    private Color color;

    public Cuadro(int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public int getXpos() {
        return xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public boolean isOcupado() {
        return ocupado;
    }

}
