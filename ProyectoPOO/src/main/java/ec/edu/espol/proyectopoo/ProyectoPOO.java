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
        ArrayList<Usuario> compradores = Usuario.readfile("Comprandorestxt");
        ArrayList<Usuario> vendedores = Usuario.readfile("Vendedores.txt");
        Vehiculo.link(vehiculos, vendedores);
        Oferta.link(ofertas, vehiculos, compradores);

        int i = 0;
        do{
            if (i == 1)
                i = Utilitaria.menu(sc, i, compradores);
            else if(i == 2)
                i = Utilitaria.menu(sc,i, vendedores);
            else
                i = Utilitaria.menu(sc,i,vendedores);
        }while(i != 3);
    }
    
}


