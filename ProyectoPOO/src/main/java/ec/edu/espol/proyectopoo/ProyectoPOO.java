package ec.edu.espol.proyectopoo;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ProyectoPOO {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        sc.useLocale(Locale.US);
        ArrayList<Oferta> ofertas = Oferta.readfile("Ofertas.txt");
        ArrayList<Vehiculo> vehiculos = Vehiculo.readfile("Vehiculos.txt");
        ArrayList<Usuario> compradores = Comprador.readfile("Compradores.txt");
        ArrayList<Usuario> vendedores = Vendedor.readfile("Vendedores.txt");
        Vehiculo.link(vehiculos, vendedores);
        if (!vehiculos.isEmpty())
            Oferta.link(ofertas, vehiculos, compradores);
        int i = 0;
        do{
            if (i == 1)
                i = Utilitaria.menu(sc, i, compradores, vehiculos, ofertas);
            else if(i == 2)
                i = Utilitaria.menu(sc,i, vendedores,vehiculos, ofertas);
            else
                i = Utilitaria.menu(sc,i,vendedores,vehiculos, ofertas); //da igual que lista se pase 
        }while(i != 3);
    }
    
}


