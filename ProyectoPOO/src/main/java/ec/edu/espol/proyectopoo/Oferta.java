package ec.edu.espol.proyectopoo;

import java.util.ArrayList;

public class Oferta {
    private String correoComprador;
    private String correoVendedor;
    private String idVehiculo;
    private Comprador comprador;
    private Vehiculo vehiculo;
    private double precioOfertado;

    public Oferta(String correoComprador,String correoVendedor, String placa, double precioOfertado) {
        this.correoComprador = correoComprador;
        this.correoVendedor = correoVendedor;
        this.idVehiculo = placa;
        this.precioOfertado = precioOfertado;
    }

    public String toString() {
        return "Correo: " + this.correoComprador + "\nPrecio Ofertado: " + this.precioOfertado;
    }

    public String getCorreoComprador(){
        return correoComprador;
    }

    public String getCorreoVendedor(){
        return correoVendedor;
    }

    public String getIdVehiculo(){
        return idVehiculo;
    }
}