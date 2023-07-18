package ec.edu.espol.proyectopoo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Vehiculo {
    protected TipoVehiculo tipo;
    protected String placa;
    protected String correo_vendedor;
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

    public Vehiculo(){};
    
    public Vehiculo(TipoVehiculo tipo, String placa, String correo_vendedor, String marca, String modelo, String tipoDeMotor, int año,
            double recorrido, String color, String tipoCombustible, double precio) {
        this.tipo = tipo;
        this.placa = placa;
        this.correo_vendedor = correo_vendedor;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoDeMotor = tipoDeMotor;
        this.año = año;
        this.recorrido = recorrido;
        this.color = color;
        this.tipoCombustible = tipoCombustible;
        this.precio = precio;
        this.ofertas = new ArrayList<>();
    }

    public int getAño() {
        return año;
    }

    public double getRecorrido() {
        return recorrido;
    }
    
    public TipoVehiculo getTipo(){
        return this.tipo;
    }
    public String getPlaca() {
        return this.placa;
    }

    public ArrayList<Oferta> getOfertas() {
        return this.ofertas;
    }

    public String getModelo(){
        return this.modelo;
    }

    public String getMarca(){
        return this.marca;
    }

    public double getPrecio(){
        return this.precio;
    }

    public void setVendedor(Vendedor vendedor){
        this.vendedor = vendedor;
    }

    public String toString(){
        return this.tipo+","+this.placa+","+this.correo_vendedor+","+this.marca+","+this.modelo+","+this.tipoDeMotor+","+this.año+","+this.recorrido+","+this.color+","+this.tipoCombustible+","+this.precio;
    }

    public void add_vehiculotxt(){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File("Vehiculos.txt"),true))){
            pw.println(this.toString());
        } catch(Exception e){
            System.out.println(e.getMessage());
        }   
    }

    public static ArrayList<Vehiculo> readfile(String nomfile){
        ArrayList<Vehiculo> vehiculos= new ArrayList<>();
        
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] tokens = linea.split(",");
                if("MOTO".equals(tokens[0].toUpperCase())){
                   Vehiculo v1 = new Vehiculo(TipoVehiculo.valueOf(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],tokens[5],Integer.parseInt(tokens[6]),Double.parseDouble(tokens[7]),tokens[8],tokens[9],Double.parseDouble(tokens[10]));
                   vehiculos.add(v1);
                }
                else if("AUTO".equals(tokens[0].toUpperCase())){
                   Vehiculo v1 = new Auto(TipoVehiculo.valueOf(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],tokens[5],Integer.parseInt(tokens[6]),Double.parseDouble(tokens[7]),tokens[8],tokens[9],Double.parseDouble(tokens[10]),tokens[11],tokens[12]);
                   vehiculos.add(v1);
                }
                else{
                    Vehiculo v1 = new Camioneta(TipoVehiculo.valueOf(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],tokens[5],Integer.parseInt(tokens[6]),Double.parseDouble(tokens[7]),tokens[8],tokens[9],Double.parseDouble(tokens[10]),tokens[11],tokens[12],tokens[13]);
                    vehiculos.add(v1);
                }
                
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return vehiculos;
    }

    public static Vehiculo filtrar_vehiculo_placa(String placa,ArrayList<Vehiculo> vehiculos){
        for(Vehiculo v:vehiculos){
            if(v.placa.equals(placa))
                return v;
        }
        return null;
    }

    public static void link(ArrayList<Vehiculo> vehiculos,ArrayList<Usuario> vendedores){
        for(Vehiculo v:vehiculos){
            Vendedor vendedor = (Vendedor)Utilitaria.filtrar_usuario(v.correo_vendedor,vendedores);
            v.setVendedor(vendedor);
            vendedor.getArrayList_Vehiculos().add(v);
        }
    }

}