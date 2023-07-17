package ec.edu.espol.proyectopoo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
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
    public String toString() {
        return nombres + "," + apellidos + "," + organizacion + "," + email + "," + clave;
    }
    public static ArrayList<Usuario> readfile(String nomfile){
        ArrayList<Usuario> lista_llena= new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] tokens = linea.split(",");
                Comprador c1 = new Comprador(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4]);
                lista_llena.add(c1);
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lista_llena;
    }
}