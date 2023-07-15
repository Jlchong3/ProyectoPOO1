package ec.edu.espol.proyectopoo;

import java.util.ArrayList;

public class Comprador extends Usuario {
    private ArrayList<Oferta> ofertas;

    public Comprador(String nombres, String apellidos, String organizacion,String email, String clave) {
        super(nombres, apellidos, organizacion, email, clave);
        this.ofertas=new ArrayList<>();
    }

    public void nuevoComprador(){
        
    }

}