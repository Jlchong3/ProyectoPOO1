/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.clases_ajedres;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author josel
 */
public class Pieza extends ImageView{
    
    protected TipoColor color;
    protected int xpos;
    protected int ypos;

    
    public Pieza(TipoColor color,int xpos,int ypos){
        this.color = color;
        this.xpos = xpos;
        this.ypos = ypos;
    }

   
    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    public TipoColor getColor() {
        return color;
    }

    public int getXpos() {
        return xpos;
    }

    public int getYpos() {
        return ypos;
    }
     

   

    public  ArrayList<int[]> movimientos_posibles(){
        return null;
    }
    public  int[] eliminarPieza(Pieza p){
        return null;
    }


}
    
    
    

