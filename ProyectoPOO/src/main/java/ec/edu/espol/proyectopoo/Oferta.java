package ec.edu.espol.proyectopoo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Oferta {
    private String correoComprador;
    private String idVehiculo;
    private Comprador comprador;
    private Vehiculo vehiculo;
    private double precioOfertado;

    public Oferta(String correoComprador, String placa, double precioOfertado) {
        this.correoComprador = correoComprador;
        this.idVehiculo = placa;
        this.precioOfertado = precioOfertado;
    }

    public String toString() {
        return "Correo: " + this.correoComprador + "\nPrecio Ofertado: " + this.precioOfertado;
    }

    public String totxt(){
        return this.correoComprador+","+this.idVehiculo+","+this.precioOfertado;
    }
    public String getCorreoComprador(){
        return correoComprador;
    }

    public String getIdVehiculo(){
        return idVehiculo;
    }
    
    public void setComprador(Comprador comprador){
        this.comprador = comprador;
    }

    public void setVehiculo(Vehiculo vehiculo){
        this.vehiculo = vehiculo;
    }
    public void add_ofertatxt(){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File("Oferta.txt"),true))){
            pw.println(this.totxt());
        } catch(Exception e){
            System.out.println(e.getMessage());
        }   
    }

    public static ArrayList<Oferta> readfile(String nomfile){
        ArrayList<Oferta> ofertas = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] tokens = linea.split(",");
                Oferta of = new Oferta(tokens[0],tokens[1],Double.parseDouble(tokens[2]));
                ofertas.add(of);
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ofertas;
    }
    public static void link(ArrayList<Oferta> ofertas,ArrayList<Vehiculo> vehiculos, ArrayList<Usuario> compradores) {
        for(Oferta f:ofertas) {
            Comprador comprador = (Comprador)Utilitaria.filtrar_usuario(f.correoComprador,compradores);
            Vehiculo vehiculo = Vehiculo.filtrar_vehiculo_placa(f.idVehiculo,vehiculos);
            f.setComprador(comprador);
            f.setVehiculo(vehiculo);
            comprador.getOfertas().add(f);
            vehiculo.getOfertas().add(f);

        }
    }
}