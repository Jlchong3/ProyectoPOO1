package ec.edu.espol.proyectopoo;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Vendedor extends Usuario {

    private ArrayList<Vehiculo> vehiculos;

    public Vendedor(String nombres, String apellidos, String organizacion, String email, String clave) {
        super(nombres, apellidos, organizacion, email, clave);
        this.vehiculos = new ArrayList<>();
    }

    public ArrayList<Vehiculo> getArrayList_Vehiculos() {
        return this.vehiculos;
    }

    public void nuevoVehiculo(Scanner input) {
        String tip, placa, marca, modelo, motor, color, combustible;
        int año;
        double recorrido, precio;
        System.out.println("Ingrese el Tipo de Vehiculo: ");
        tip = input.next();
        tip = tip.toUpperCase();
        TipoVehiculo tipoVe;
        if (tip.equals("MOTO")) {
            tipoVe = TipoVehiculo.MOTO;
        } else if (tip.equals("AUTO")) {
            tipoVe = TipoVehiculo.AUTO;
        } else {
            tipoVe = TipoVehiculo.CAMIONETA;
        }
        System.out.println("Ingrese la placa del vehiculo: ");
        placa = input.next();
        System.out.println("Ingrese la marca del vehiculo: ");
        marca = input.next();
        System.out.println("Ingrese el modelo del vehiculo: ");
        modelo = input.next();
        System.out.println("Ingrese el tipo de motor del vehiculo: ");
        motor = input.next();
        System.out.println("Ingrese el año del vehiculo: ");
        año = input.nextInt();
        System.out.println("Ingese el recorrido del vehiculo:");
        recorrido = input.nextDouble();
        System.out.println("Ingrese el color del vehiculo: ");
        color = input.next();
        System.out.println("Ingrese tipo Combustible del vehiculo: ");
        combustible = input.next();
        System.out.println("Ingrese el precio del vehiculo:");
        precio = input.nextDouble();
        if (this.vehiculos.isEmpty()) {
            llenarVehiculos(tipoVe, placa, this.email, marca, modelo, motor, año, recorrido, color, combustible, precio, input);
        } 
        else {
            for (Vehiculo v : this.vehiculos) {
                if (v.placa.equals(placa)) {
                    System.out.println("El vehiculo ya existe");
                } 
                else {
                    llenarVehiculos(tipoVe, placa, this.email, marca, modelo, motor, año, recorrido, color, combustible, precio, input);
                }
            }
        }
    }

    public void llenarVehiculos(TipoVehiculo tipoVe, String placa, String email, String marca, String modelo, String motor, int año, double recorrido, String color, String combustible, double precio, Scanner input) {
        if (tipoVe.equals(TipoVehiculo.MOTO)) {
            Vehiculo ve = new Vehiculo(tipoVe, placa, this.email, marca, modelo, motor, año, recorrido, color, combustible, precio);
            this.vehiculos.add(ve);
            ve.add_vehiculotxt();
        } else {
            System.out.println("Ingrese los vidrios:");
            String vidrios = input.next();
            System.out.println("Ingrese la Transmicion:");
            String transmision = input.next();
            if (tipoVe.equals(TipoVehiculo.CAMIONETA)) {
                System.out.println("Ingrese la Traccion:");
                String traccion = input.nextLine();
                Vehiculo ve = new Camioneta(tipoVe, placa, this.email, marca, modelo, motor, año, recorrido, color, combustible, precio, transmision, vidrios, traccion);
                this.vehiculos.add(ve);
                ve.add_vehiculotxt();
            } else {
                Vehiculo ve = new Auto(tipoVe, placa, this.email, marca, modelo, motor, año, recorrido, color, combustible, precio, transmision, vidrios);
                this.vehiculos.add(ve);
                ve.add_vehiculotxt();
            }
        }
    }

    public void aceptarOferta(Scanner sc) {
        String[] opciones = {"1. Siguiente Oferta", "2. Anterior Oferta", "3. Aceptar Oferta", "4. Salir"};
        System.out.println("Ingrese placa: ");
        String placa = sc.next();
        Vehiculo v = filtrarPlaca(placa);
        String infoVe = v.getMarca() + " " + v.getModelo();
        System.out.println(infoVe + "Precio: " + v.getPrecio());
        ArrayList<Oferta> ofertas = v.getOfertas();
        System.out.println("Se han realizado " + ofertas.size() + " ofertas");
        int i = 0;
        int accion = 0;
        do {
            System.out.println("\nOferta " + (i + 1));
            System.out.println(ofertas.get(i));
            for (String op : opciones) {
                System.out.println(op);
            }

            do {
                System.out.println("Ingrese el numero de su opcion: ");
                accion = sc.nextInt();
            } while ((i == 0 && accion == 2) || (i == ofertas.size() - 1 && accion == 1) || (accion < 1 || accion > 3));

            if (accion == 1) {
                i++;
            } else if (accion == 2) {
                i--;
            }
        } while (accion != 3 && accion != 4);
        if (accion == 3) {
            Oferta ofAceptada = ofertas.get(i);
            Utilitaria.enviarConGMail(email, clave, ofAceptada.getCorreoComprador(), infoVe);
            this.vehiculos.remove(v);
            Utilitaria.vehiculoBorrado(this.vehiculos, "Vehiculos.txt");
        }
    }

    public Vehiculo filtrarPlaca(String placa) {
        for (Vehiculo v : this.vehiculos) {
            if (v.placa.equals(placa)) {
                return v;
            }
        }
        return null;
    }

    public static ArrayList<Usuario> readfile(String nomfile) {
        ArrayList<Usuario> lista_llena = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(nomfile))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] tokens = linea.split(",");
                Usuario c1 = new Vendedor(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
                lista_llena.add(c1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista_llena;
    }

}
