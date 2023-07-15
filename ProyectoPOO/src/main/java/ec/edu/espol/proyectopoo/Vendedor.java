package ec.edu.espol.proyectopoo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Vendedor extends Usuario {
    private ArrayList<Vehiculo> vehiculos;

    public Vendedor(String nombres, String apellidos, String organizacion, String email, String clave) {
        super(nombres, apellidos, organizacion, email, clave);
        this.vehiculos = new ArrayList<>();
    }

    public void nuevoVehiculo(String email, String clave, String nomfile) {
        String hash;
        try (Scanner sc = new Scanner(new File(nomfile))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] tokens = linea.split(",");
                if (tokens[3].equals(clave)) {
                    Scanner input = new Scanner(System.in);
                    input.useDelimiter("\n");
                    input.useLocale(Locale.US);
                    System.out.println("Ingrese el Tipo de Vehiculo: ");
                    TipoVehiculo tipoVe = TipoVehiculo.valueOf(input.nextLine());
                    System.out.println("Ingrese la placa del vehiculo: ");
                    String placa = input.next();
                    System.out.println("Ingrese la marca del vehiculo: ");
                    String marca = input.next();
                    System.out.println("Ingrese el modelo del vehiculo: ");
                    String modelo = input.next();
                    System.out.println("Ingrese el tipo de motor del vehiculo: ");
                    String motor = input.next();
                    System.out.println("Ingrese el ano del vehiculo: ");
                    int año = input.nextInt();
                    System.out.println("Ingese el recorrido del vehiculo:");
                    double recorrido = input.nextDouble();
                    System.out.println("Ingrese el color del vehiculo: ");
                    String color = input.next();
                    System.out.println("Ingrese tipo Combustible del vehiculo: ");
                    String combustible = input.next();
                    System.out.println("Ingrese el precio del vehiculo:");
                    double precio = input.nextDouble();

                    for (Vehiculo v : this.vehiculos) {
                        if (v.getPlaca() == placa) {
                            System.out.println("El vehiculo ya existe");

                        } else {
                            if (tipoVe.equals(TipoVehiculo.MOTO)) {

                                Vehiculo ve = new Vehiculo(tipoVe, placa, marca, modelo, motor, año, recorrido, color,
                                        combustible, precio);
                                this.vehiculos.add(ve);
                            } else {
                                System.out.println("Ingrese los vidrios:");
                                String vidrios = input.next();
                                System.out.println("Ingrese la Transmicion:");
                                String transmision = input.next();
                                if (tipoVe.equals(TipoVehiculo.CAMIONETA)) {
                                    System.out.println("Ingrese la Traccion:");
                                    String traccion = input.nextLine();
                                    Vehiculo ve = new Camioneta(tipoVe, placa, marca, modelo, motor, año, recorrido,
                                            color, combustible, precio, vidrios, transmision, traccion);
                                    this.vehiculos.add(ve);
                                } else {
                                    Vehiculo ve = new Auto(tipoVe, placa, marca, modelo, motor, año, recorrido, color,
                                            combustible, precio, transmision, vidrios);
                                    this.vehiculos.add(ve);
                                }

                            }

                        }

                    }
                    input.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String toString() {
        return nombres + "," + apellidos + "," + organizacion + "," + email + "," + clave;
    }

    public void aceptarOferta(){
        String[] opciones = {"1. Siguiente Oferta", "2. Anterior Oferta","3. Aceptar Oferta"};
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.println("Ingrese placa: ");
        String placa = sc.next();
        Vehiculo v = filtrarPlaca(placa);
        System.out.println(v.getMarca() + " " + v.getModelo() + "Precio: " + v.getPrecio());
        ArrayList<Oferta> ofertas = v.getOfertas();
        System.out.println("Se han realizado " + ofertas.size() + " ofertas");
        int i = 0;
        int accion = 0;
        do{
            System.out.println("\nOferta " + (i+1));
            System.out.println(ofertas.get(i));
            for (String op : opciones){
                System.out.println(op);
            }

            do{
                System.out.println("Ingrese el numero de su opcion: ");
                accion = sc.nextInt();
            }while((i == 0 && accion == 2) || (i == ofertas.size()-1 && accion == 1) || (accion < 1 || accion > 3));

            if (accion == 1)
                i++;
            else if (accion == 2)
                i--;
        }while(accion != 3);
        Oferta Ofaceptada = ofertas.get(i);
        //correos 
    }

    public Vehiculo filtrarPlaca(String placa) {
        for (Vehiculo v : this.vehiculos) {
            if (v.placa.equals(placa))
                return v;
        }
        return null;
    }
}