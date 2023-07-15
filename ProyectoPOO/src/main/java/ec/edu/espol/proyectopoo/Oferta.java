package ec.edu.espol.proyectopoo;

import java.util.ArrayList;

public class Oferta {
    private Comprador comprador;
    private Vehiculo vehiculo;
    private double precioOfertado;

    public Oferta(Comprador comprador, Vehiculo vehiculo, double precioOfertado) {
        this.comprador = comprador;
        this.vehiculo = vehiculo;
        this.precioOfertado = precioOfertado;
    }

    public String toString() {
        return "Correo: " + this.comprador.getCorreo() + "\nPrecio Ofertado: " + this.precioOfertado;
    }

}