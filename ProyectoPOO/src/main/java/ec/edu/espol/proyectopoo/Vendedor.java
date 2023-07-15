package ec.edu.espol.proyectopoo;

import java.util.ArrayList;

public class Vendedor extends Usuario {
    private ArrayList<Vehiculo> vehiculos;

    public Vendedor(String nombres, String apellidos, String email, String clave) {
        super(nombres, apellidos, email, clave);
        this.vehiculos = new ArrayList<>();
    }

}