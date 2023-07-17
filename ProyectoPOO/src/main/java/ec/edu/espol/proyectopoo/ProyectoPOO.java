package ec.edu.espol.proyectopoo;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ProyectoPOO {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        sc.useLocale(Locale.US);
        ArrayList<Oferta> ofertas = Oferta.readfile("Oferta.txt");
        ArrayList<Vehiculo> vehiculos = Vehiculo.readfile("Vehiculos.txt");
        ArrayList<Usuario> comprodores = Usuario.readfile("Comprandorestxt");
        ArrayList<Usuario> vendedores = Usuario.readfile("Vendedores.txt");
    }
}


