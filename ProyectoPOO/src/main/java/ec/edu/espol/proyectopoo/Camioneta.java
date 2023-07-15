package ec.edu.espol.proyectopoo;

public class Camioneta extends Vehiculo {
    private String traccion;
    private String transmicion;
    private String vidrios;

    public Camioneta(TipoVehiculo tipo, String placa, String marca, String organizacion, String tipoDeMotor, int año, double recorrido,
            String color, String tipoCombustible, double precio, String vidrios, String transmicion,String traccion) {
        super(tipo, placa, marca, organizacion, tipoDeMotor, año, recorrido, color, tipoCombustible, precio);
        this.traccion = traccion;
        this.transmicion = transmicion;
        this.vidrios = vidrios;
    }

}