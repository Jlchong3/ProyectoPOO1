package ec.edu.espol.proyectopoo;

public class Auto extends Vehiculo {
    private String transmision;
    private String vidrios;

    public Auto(TipoVehiculo tipo, String placa, String marca, String organizacion,
            String tipoDeMotor, int año, double recorrido, String color, String tipoCombustible, double precio,
            String transmision, String vidrios) {
        super(tipo, placa, marca, organizacion, tipoDeMotor, año, recorrido, color, tipoCombustible, precio);
        this.transmision = transmision;
        this.vidrios = vidrios;
    }

    @Override
    public String toString(){
        return super.toString()+","+this.transmision+","+this.vidrios;
    }

}