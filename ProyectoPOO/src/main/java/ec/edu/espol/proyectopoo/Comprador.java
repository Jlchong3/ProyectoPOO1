package ec.edu.espol.proyectopoo;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Comprador extends Usuario {
    private ArrayList<Oferta> ofertas;


    public Comprador(String nombres, String apellidos, String organizacion,String email, String clave) {
        super(nombres, apellidos, organizacion, email, clave);
        this.ofertas=new ArrayList<>();
    }

    public static ArrayList<Vehiculo> filtrar_Vehiculos(){
        ArrayList<Vehiculo> vehiculos_filtrados = new ArrayList<>();
        
        try(Scanner sc = new Scanner(new File("Vehiculos.txt"))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] tokens = linea.split(",");
                Scanner input = new Scanner(System.in);
                input.useDelimiter("\n");
                input.useLocale(Locale.US);
                System.out.println("Ingrese los parametros por lo que desea filtrar:");
                System.out.println("1._Tipo de vehículo\n2._ Recorrido (Especificado en un rango)\n3._ Año (Especificado en un rango)\n4._ Precio (Especificado en un rango)");
                System.out.println("Ingresa los numeros correspondiente de los parametros por los que desees filtrar");
                ArrayList<Integer> respuetas = new ArrayList<>();
                String res,res2;
                int res3;
                
                do{
                    res = input.next();
                }while(!(res.matches("[1-4]+")) || res.length()>1 );
                respuetas.add(Integer.parseInt(res));

                do{
                System.out.println("Deseas Añadir mas filtros");
                System.out.println("1._Si\n2._No");
                res2 = input.next().toLowerCase();
                while(!(res2.equals("no")) && !(res2.equals("si"))){
                    System.out.println("Ingrese una respuesta valida");
                    res2 = input.next().toLowerCase();
                }
                res3 = input.nextInt();
                if (!(respuetas.contains(res3)))
                    respuetas.add(res3);
                
                }while(res2.equals("si"));
                
                
                System.out.println("Ingrese la placa del vehiculo: ");
                TipoVehiculo tipoVe = TipoVehiculo.valueOf(input.nextLine());
                boolean esnum1=false, esnum2=false;
                String strRango1,strRango2;
                do{
                    System.out.println("Ingrese el rango del recorrido: ");
                    System.out.println("Ejemplo: 1000 - 2000");
                    System.out.println("Ingrese el primer rango del recorrido");
                    strRango1 =input.next();
                    System.out.println("Ingrese el segundo rango del recorrido");
                    strRango2 =input.next();
                    esnum1=Utilitaria.validaNum(strRango1);
                    esnum2=Utilitaria.validaNum(strRango2);
                }while(esnum1==false || esnum2==false);
                double rango1Re=Double.parseDouble(strRango1);
                double rango2Re=Double.parseDouble(strRango2);

            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return vehiculos_filtrados;
    }

}