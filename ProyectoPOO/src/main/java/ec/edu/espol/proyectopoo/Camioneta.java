package ec.edu.espol.proyectopoo;

public class Camioneta extends Vehiculo {
    private String traccion;
    private String transmision;
    private String vidrios;

    public Camioneta(TipoVehiculo tipo, String placa, String marca, String organizacion, String tipoDeMotor, int año, double recorrido,
            String color, String tipoCombustible, double precio,String transmision, String vidrios, String traccion) {
                
        super(tipo, placa, marca, organizacion, tipoDeMotor, año, recorrido, color, tipoCombustible, precio);

        this.transmision = transmision;
        this.vidrios = vidrios;
        this.traccion = traccion;
    }

    @Override
    public String toString(){
        return super.toString()+","+this.transmision+","+this.vidrios+","+this.traccion;
    }

}