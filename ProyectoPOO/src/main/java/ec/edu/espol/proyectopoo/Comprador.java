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

    public ArrayList<Oferta> getOfertas(){
        return this.ofertas;
    }

    public static ArrayList<Vehiculo> filtrar_Vehiculos(ArrayList<Vehiculo> vehiculos) {
        ArrayList<Vehiculo> vehiculos_filtrados = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        input.useDelimiter("\n");
        input.useLocale(Locale.US);
        System.out.println("Ingrese los parametros por lo que desea filtrar:");
        System.out.println(
                "1._Tipo de vehículo\n2._ Recorrido (Especificado en un rango)\n3._ Año (Especificado en un rango)\n4._ Precio (Especificado en un rango)");
        System.out.println("Ingrese los numeros correspondientes de los parametros por los que desee filtrar separados por coma ");
        ArrayList<Integer> respuestas = new ArrayList<>();
        String res;

        do {
            res = input.nextLine();
            res = res.replace(",", "").replace(" ","");
            
        } while (!(res.matches("[1-4]+")) || res.length() > 4);
        
        for (String r : res.split("")){
            respuestas.add(Integer.parseInt(r));
            }
        String tipoVe = "";
        int rangorecorrido1=-1, rangorecorrido2=-1, rangoanio1=-1, rangoanio2=-1, rangoprecio1=-1, rangoprecio2=-1; 
        if (respuestas.contains(1)) {
            System.out.println("Ingrese el tipo del vehiculo: ");
            do{
               tipoVe = input.next().toUpperCase();
            }while(!(tipoVe.equals("MOTO")) && !(tipoVe.equals("CAMIONETA")) && !(tipoVe.equals("AUTO")));
        }
        if (respuestas.contains(2)) {
            System.out.println("Ingrese el rango del recorrido: ");
            System.out.println("Ingrese el primer rango del recorrido");
            rangorecorrido1 = Utilitaria.int_validado_pos(input);
            System.out.println("Ingrese el segundo rango del recorrido");
            rangorecorrido2 = Utilitaria.int_validado_pos(input);
        }
        if (respuestas.contains(3)) {
            
            System.out.println("Ingrese el rango del Año: ");
            System.out.println("Ingrese el primer rango del Año");
            rangoanio1 = Utilitaria.int_validado_pos(input);
            System.out.println("Ingrese el segundo rango del Año");
            rangoanio2 = Utilitaria.int_validado_pos(input);
        }
        if (respuestas.contains(4)) {
            System.out.println("Ingrese el rango del Precio: ");
            System.out.println("Ingrese el primer rango del Precio");
            rangoprecio1 = Utilitaria.int_validado_pos(input);
            System.out.println("Ingrese el segundo rango del Precio");
            rangoprecio2 = Utilitaria.int_validado_pos(input);
        }
        if(rangorecorrido1==-1 && rangorecorrido2==-1){
            rangorecorrido1 = Integer.MIN_VALUE;
            rangorecorrido2 = Integer.MAX_VALUE;
        }
        if(rangoanio1==-1 && rangoanio2==-1){
            rangoanio1 = Integer.MIN_VALUE;
            rangoanio2 = Integer.MAX_VALUE;
        }
        if(rangoprecio1== -1 && rangoprecio2== -1){
            rangoprecio1 = Integer.MIN_VALUE;
            rangoprecio2 = Integer.MAX_VALUE;
        }

        for (Vehiculo veh : vehiculos){
            if (tipoVe.equals("") || tipoVe.equals(veh.getTipo().toString()) && veh.getRecorrido() >=rangorecorrido1 && veh.getRecorrido()<=rangorecorrido2 && veh.getAño() >= rangoanio1 && veh.getAño()<= rangoanio2 && veh.getPrecio()>= rangoprecio1 && veh.getPrecio() <= rangoprecio2 )
                    vehiculos_filtrados.add(veh);
            }
            
        return vehiculos_filtrados;
    }

    public void ofertar_Vehiculo(ArrayList<Vehiculo> vehiculos_filtrados, ArrayList<Oferta> ofertass, ArrayList<Vehiculo> vehiculos, ArrayList<Usuario> compradores){
        int cont = 0;
        String res_u;
        Scanner input  = new Scanner(System.in);
        input.useDelimiter("\n");
        input.useLocale(Locale.US);
        Boolean oferta = true;
        do{
            System.out.println("Vehiculo "+(cont+1));
            System.out.println(vehiculos_filtrados.get(0).toString());
            System.out.println("Desea ofertar por este vehiculo?");
            System.out.println("Responda con un: Si o No");
            res_u = Utilitaria.validad_Si_No(input);
            if(res_u.equals("si")){
                System.out.println("Ingrese el monto de la oferta");
                double monto = Utilitaria.doubleVali(input);
                Oferta o1 = new Oferta(this.email,vehiculos_filtrados.get(cont).placa,monto);
                o1.add_ofertatxt();
                ofertas.add(o1);
                ofertass.add(o1);
                Oferta.link(ofertass, vehiculos, compradores);
                oferta = false;
                System.out.println("Oferta realizada con exito");    
            }
            else{
                String res_s;
                if(cont!=0){
                    System.out.println("Ingresa\n1._ Anterior Vehiculo\n2._ Siguiente Vehiculo");
                    
                    res_s = input.next();
                    while (!(res_s.matches("[1-2]+"))){
                        System.out.println("Opcion incorrecta, vuelta intentarlos");
                        System.out.println("Ingresa\n1._ Anterior Vehiculo\n2._ Siguiente Vehiculo");
                        res_s = input.next();}
                    int res_f = Integer.parseInt(res_s);
                    if (res_f==1){
                        cont--;}
                    else{
                        cont++;}
                }
                else if(cont==vehiculos_filtrados.size()-1){
                    System.out.println("Ingresa\n1._ Anterior Vehiculo\n2._Salir");
                    res_s = input.next().strip();
                    while (!(res_s.matches("[1-2]+"))){
                        System.out.println("Opcion incorrecta, vuelta intentarlos");
                        System.out.println("Ingresa\n1._ Anterior Vehiculo\n2._Salir");
                        res_s = input.next();}
                    int res_f = Integer.parseInt(res_s);
                    if(res_f==1)
                        cont++;
                    else
                        oferta = false;
                }
                else {
                    System.out.println("Ingresa\n2._ Siguiente Vehiculo");
                    res_s = input.next().strip();
                    while (!(res_s.equals("2"))){
                        System.out.println("Opcion incorrecta, vuelta intentarlos");
                        System.out.println("Ingresa\n2._ Siguiente Vehiculo");
                        res_s = input.next();}
                    int res_f = Integer.parseInt(res_s);
                    if(res_f==2)
                        cont++;
                }
            }
        }while(oferta);
    }
    
    public static ArrayList<Usuario> readfile(String nomfile){
        ArrayList<Usuario> lista_llena= new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] tokens = linea.split(",");
                Usuario c1 = new Comprador(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4]);
                lista_llena.add(c1);
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lista_llena;
    }
    

}
