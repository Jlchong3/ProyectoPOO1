package ec.edu.espol.proyectopoo;

import java.util.ArrayList;

public class Comprador extends Usuario {
    private ArrayList<Oferta> ofertas;

    public Comprador(String nombres, String apellidos, String email, String clave) {
        super(nombres, apellidos, email, clave);
        this.ofertas=new ArrayList<>();
    }

}