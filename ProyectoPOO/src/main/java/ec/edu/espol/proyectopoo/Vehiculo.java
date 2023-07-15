package ec.edu.espol.proyectopoo;
import java.util.ArrayList;

public class Vehiculo{
    protected TipoVehiculo tipo;
    protected String placa;
    protected String marca;
    protected String modelo;
    protected String tipoDeMotor;
    protected int año;
    protected double recorrido;
    protected String color;
    protected String tipoCombustible;
    protected double precio;
    protected Vendedor vendedor;
    protected ArrayList<Oferta> ofertas;

    public Vehiculo(TipoVehiculo tipo, String placa, String marca, String tipoDeMotor, int año, double recorrido, String color, String tipoCombustible, double precio){
        this.tipo = tipo;
        this.placa = placa;
        this.marca = marca;
        this.tipoDeMotor = tipoDeMotor;
        this.año = año;
        this.recorrido = recorrido;
        this.color = color;
        this.tipoCombustible = tipoCombustible;
        this.precio = precio;
        this.ofertas = new ArrayList<>();
    }
}