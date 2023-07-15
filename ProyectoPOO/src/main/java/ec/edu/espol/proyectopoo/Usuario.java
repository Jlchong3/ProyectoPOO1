package ec.edu.espol.proyectopoo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.security.NoSuchAlgorithmException;


public class Usuario {
    protected String nombres;
    protected String apellidos;
    protected String organizacion;
    protected String email;
    protected String clave;

    public Usuario(String nombres, String apellidos,String organizacion, String email, String clave) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
        this.email = email;
        this.clave = clave;
    }
    
    public String getCorreo(){
        return this.email;
    }

    public void registrarUsuario(int u) {
        Scanner input = new Scanner(System.in);
        input.useDelimiter("\n");
        System.out.println("Ingrese sus nombres: ");
        String nombres = input.next();
        System.out.println("Ingrese sus apellidos: ");
        String apellidos = input.next();
        System.out.println("Ingrese la Organización: ");
        String organizacion = input.next();
        System.out.println("Ingrese su correo electrónico: ");
        String email = input.nextLine();
        System.out.println("Ingrese su clave: ");
        String clave = input.nextLine();
        try{
            clave = Utilitaria.codificarHash(clave);
        }
        catch(NoSuchAlgorithmException e){}; 
        if(u==1){
            Vendedor vendedor = new Vendedor(nombres, apellidos, organizacion, email, clave);
            if (vendedor.validar_correo("Vendedores.txt"))
                System.out.println("El correo ya existe");
            else {
                vendedor.registrar("Vendedores.txt");
                System.out.println("Se ha registrado exitosamente");
            }
        }
        else if(u==2){
            Comprador comprador = new Comprador(nombres, apellidos, organizacion, email, clave);
            if (comprador.validar_correo("Compradores.txt"))
                System.out.println("El correo ya existe");
            else {
                comprador.registrar("Compradores.txt");
                System.out.println("Se ha registrado exitosamente");
            }
        }
        input.close();
    }

    public boolean validar_correo(String nomfile) {
        try (Scanner sc = new Scanner(new File(nomfile))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] tokens = linea.split(",");
                if (tokens[3].equals(this.email))
                    return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void registrar(String nomfile) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile), true))) {
            pw.println(this.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}