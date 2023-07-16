package ec.edu.espol.proyectopoo;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Comprador extends Usuario {
    private ArrayList<Oferta> ofertas;

    public Comprador(String nombres, String apellidos, String organizacion, String email, String clave) {
        super(nombres, apellidos, organizacion, email, clave);
        this.ofertas = new ArrayList<>();
    }

    public static ArrayList<Vehiculo> filtrar_Vehiculos() {
        ArrayList<Vehiculo> vehiculos_filtrados = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        input.useDelimiter("\n");
        input.useLocale(Locale.US);
        System.out.println("Ingrese los parametros por lo que desea filtrar:");
        System.out.println(
                "1._Tipo de vehículo\n2._ Recorrido (Especificado en un rango)\n3._ Año (Especificado en un rango)\n4._ Precio (Especificado en un rango)");
        System.out.println("Ingresa los numeros correspondiente de los parametros por los que desees filtrar");
        ArrayList<Integer> respuestas = new ArrayList<>();
        String res, res2;
        int res3;

        do {
            res = input.next();
        } while (!(res.matches("[1-4]+")) || res.length() > 1);
        respuestas.add(Integer.parseInt(res));

        do {
            System.out.println("Deseas Añadir mas filtros");
            System.out.println("1._Si\n2._No");
            res2 = input.next().toLowerCase();
            while (!(res2.equals("no")) && !(res2.equals("si"))) {
                System.out.println("Ingrese una respuesta valida");
                res2 = input.next().toLowerCase();
            }
            res3 = input.nextInt();
            if (!(respuestas.contains(res3)))
                respuestas.add(res3);

        } while (res2.equals("si"));
        String tipoVe="";
        int Rangorecorrido1=-1, Rangorecorrido2=-1, Rangoanio1=-1, Rangoanio2=-1, Rangoprecio1=-1, Rangoprecio2=-1; 
        for (int i : respuestas) {
            if (respuestas.contains(1)) {
                System.out.println("Ingrese el tipo del vehiculo: ");
                tipoVe = input.next();
            }
            if (respuestas.contains(2)) {
                System.out.println("Ingrese el rango del recorrido: ");
                System.out.println("Ejemplo: 1000 - 2000");
                System.out.println("Ingrese el primer rango del recorrido");
                Rangorecorrido1 = Utilitaria.int_validado_pos(input);
                System.out.println("Ingrese el segundo rango del recorrido");
                Rangorecorrido2 = Utilitaria.int_validado_pos(input);
            }
            if (respuestas.contains(3)) {
                
                System.out.println("Ingrese el rango del Año: ");
                System.out.println("Ejemplo: 2012 - 2020");
                System.out.println("Ingrese el primer rango del Año");
                Rangoanio1 = Utilitaria.int_validado_pos(input);
                System.out.println("Ingrese el segundo rango del Año");
                Rangoanio2 = Utilitaria.int_validado_pos(input);
            }
            if (respuestas.contains(4)) {

                System.out.println("Ingrese el rango del Precio: ");
                System.out.println("Ejemplo: 1000 - 2000");
                System.out.println("Ingrese el primer rango del Precio");
                Rangoprecio1 = Utilitaria.int_validado_pos(input);
                System.out.println("Ingrese el segundo rango del Precio");
                Rangoprecio2 = Utilitaria.int_validado_pos(input);
            }
        }
        if(Rangorecorrido1==-1 && Rangorecorrido2==-1){
            Rangorecorrido1 = Integer.MIN_VALUE;
            Rangorecorrido2 = Integer.MAX_VALUE;
        }
        if(Rangoanio1==-1 && Rangoanio2==-1){
            Rangoanio1 = Integer.MIN_VALUE;
            Rangoanio2 = Integer.MAX_VALUE;
        }
        if(Rangoprecio1== -1 && Rangoprecio2== -1){
            Rangoprecio1 = Integer.MIN_VALUE;
            Rangoprecio2 = Integer.MAX_VALUE;
        }

        input.close();
        Vehiculo v1 = new Vehiculo();
        try (Scanner sc = new Scanner(new File("Vehiculos.txt"))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] tokens = linea.split(",");
                if(TipoVehiculo.MOTO.equals(TipoVehiculo.valueOf(tokens[0]))){
                    v1 = new Vehiculo(TipoVehiculo.valueOf(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],Integer.parseInt(tokens[5]),Double.parseDouble(tokens[6]),tokens[7],tokens[8],Double.parseDouble(tokens[9]));
                }
                else if(TipoVehiculo.AUTO.equals(TipoVehiculo.valueOf(tokens[0]))){
                    v1 = new Auto(TipoVehiculo.valueOf(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],Integer.parseInt(tokens[5]),Double.parseDouble(tokens[6]),tokens[7],tokens[8],Double.parseDouble(tokens[9]),tokens[10],tokens[11]);
                }
                else{
                    v1 = new Camioneta(TipoVehiculo.valueOf(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],Integer.parseInt(tokens[5]),Double.parseDouble(tokens[6]),tokens[7],tokens[8],Double.parseDouble(tokens[9]),tokens[10],tokens[11],tokens[12]);
                }
                double recorrido =  Integer.parseInt(tokens[6]);
                int anio = Integer.parseInt(tokens[5]);
                double precio = Double.parseDouble(tokens[9]);
                if ((tipoVe==null || tipoVe.equals(tokens[0])) && recorrido >=Rangorecorrido1 && recorrido<=Rangorecorrido2 && anio >= Rangoanio1 && anio<= Rangoanio2 && precio>= Rangoprecio1 && precio <= Rangoprecio2 )
                    vehiculos_filtrados.add(v1);
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return vehiculos_filtrados;
    }
}