/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.juegoajedrez;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author angelozurita
 */
public class Usuario implements Serializable {
    
    private String nombre_usuario;
    private String correo;
    private String contraseña;
    private static final long serialVersionUID = 274965647874716638L;

    public Usuario(String nombre_usuario, String correo, String contraseña) {
        this.nombre_usuario = nombre_usuario;
        this.correo = correo;
        this.contraseña = contraseña;
    }
    
    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }
    
    
    public static ArrayList<Usuario> readListFromFileSer(String nombre) 
    {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(nombre)))
        {
            usuarios = (ArrayList<Usuario>)in.readObject();
        }
        catch(IOException ioe)
        {
            
        }
        catch(ClassNotFoundException c )
        {
                
        }
        return usuarios;
    }
    
    public static void saveListToFileSer (String nombre, ArrayList<Usuario> usuarios)
    {
        try(ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream(nombre)))
        {
            out.writeObject(usuarios);
        }
        catch(IOException ioe)
        {
            
        }
    }
    
    public static ArrayList<Usuario>  add_Usertolist (Usuario user,ArrayList<Usuario> usuarios)
    {
        usuarios.add(user);
        return usuarios;
    }
    
    public static void  add_user_file(Usuario user,String nombre){
        ArrayList<Usuario> usuarios =  readListFromFileSer (nombre);
        usuarios.add(user);
        Usuario.saveListToFileSer (nombre,usuarios);
    }
    
    public static boolean validar_correo(String correo_u,String nombre_archivo)
    {
        ArrayList<Usuario> usuarios = Usuario.readListFromFileSer(nombre_archivo);
        ArrayList<String> correos = new ArrayList<>();
        
        for (Usuario usuario1 : usuarios)
            correos.add(usuario1.correo);
        
        if (correos.isEmpty()) {
           return false ; //Me retorna falso, si el correo no se encuentra en el registro de correro de los usuarios
        }
        else if(correos.contains(correo_u)){
            return true ; // Me retorna true,  si el correo se encuentra en el registro de correo de los usuarios
        }
        
        return false;
    }
    
    public static boolean validar_password (String correo, String password, String nombre_archivo)
    {
        ArrayList<Usuario> usuarios = Usuario.readListFromFileSer(nombre_archivo);
        ArrayList<String> correos = new ArrayList<>();
        ArrayList<String> claves = new ArrayList<>();
        
        for (Usuario usuario1 : usuarios){
            correos.add(usuario1.correo);
            claves.add(usuario1.contraseña);
        }
        
        int pos_correo = correos.indexOf(correo);

        //Me retorna true si esa contraseña verdaderamente le pertenece a  ese usuario
        //Retorna false si la contraseña es incorrecta
        return claves.get(pos_correo).equals(password);   
    }

    public static boolean correoValido(String correo){
        int arroba=0;
        for(char carac:correo.toCharArray()){
            if ((carac=='@')) {
                arroba++;
            }
        }
        return !(arroba>1 ||arroba==0);
    }
}
