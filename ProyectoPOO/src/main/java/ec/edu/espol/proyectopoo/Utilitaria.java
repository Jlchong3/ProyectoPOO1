package ec.edu.espol.proyectopoo;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Utilitaria {
    public static String codificarHash(String clave) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(clave.getBytes(StandardCharsets.UTF_8));
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64)
            hexString.insert(0, '0');
        return hexString.toString();
    }

    public static void enviarConGMail(String remitente, String clave, String destinatario, String infoVe) {

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", clave); // La clave de la cuenta
        props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario)); // Se podrían añadir
                                                                                               // varios de la misma
                                                                                               // manera
            message.setSubject("Oferta del Vehiculo");
            message.setText("Su oferta del Vehiculo " + infoVe + " ha sido aceptada");
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            me.printStackTrace(); // Si se produce un error
        }
    }

    public static boolean validar_clave(String correo, String clave, String nomfile) {
        try (Scanner sc = new Scanner(new File(nomfile))) {
            while (sc.hasNextLine()) {
                String[] info = sc.nextLine().split(",");
                return correo.equals(info[3]) && Utilitaria.codificarHash(clave).equals(info[4]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static Usuario filtrar_usuario(String correo, ArrayList<Usuario> usuarios){
        for (Usuario u : usuarios){
            if (correo.equals(u.getCorreo()))
                return u;
        }
        return null;
    }

    public static void registrarUsuario(int u) {
        Scanner input = new Scanner(System.in);
        String nombres, apellidos, organizacion, email, clave;

        input.useDelimiter("\n");
        input.useLocale(Locale.US);
        System.out.println("Ingrese sus nombres: ");
        nombres = input.next();
        System.out.println("Ingrese sus apellidos: ");
        apellidos = input.next();
        System.out.println("Ingrese la Organización: ");
        organizacion = input.next();
        System.out.println("Ingrese su correo electrónico: ");
        email = input.next();
        System.out.println("Ingrese su clave: ");
        clave = input.next();
        try {
            clave = Utilitaria.codificarHash(clave);
        } catch (NoSuchAlgorithmException e) {}
        ;
        if (u == 2) {
            Usuario usuario = new Vendedor(nombres, apellidos, organizacion, email, clave);
            if (usuario.validar_correo("Vendedores.txt"))
                System.out.println("El correo ya existe");
            else {
                usuario.registrar("Vendedores.txt");
                System.out.println("Se ha registrado exitosamente");
            }
        } else if (u == 1) {
            Usuario usuario = new Comprador(nombres, apellidos, organizacion, email, clave);
            if (usuario.validar_correo("Compradores.txt"))
                System.out.println("El correo ya existe");
            else {
                usuario.registrar("Compradores.txt");
                System.out.println("Se ha registrado exitosamente");
            }
        }
    }

    public static int int_validado_pos(Scanner input) {
        String res;
        do {
            res = input.next();
        } while (!(res.matches("[0-9]+")));
        return Integer.parseInt(res);
    }
    
    public static int int_valido_rango(Scanner input, String rang1,String rang2){
        String rango = "["+rang1+"-"+rang2+"]+";
        String res;
        System.out.println("Ingresa la opción: ");
        res = input.next();
        while (!(res.matches(rango))|| res.length()>1){
        System.out.println("Opción Invalida");
        System.out.println("Ingresa la opción: ");
        res = input.next();}
        return Integer.parseInt(res);
    }
    
    public static double doubleVali(Scanner input) {
        int con;
        String str;
        boolean nega = false;
        do {
            con = 0;
            str = input.next();
            for (char c : str.toCharArray()) {
                if (c == '.' || !Character.isDigit(c)) {
                    con++;
                }
            }
            nega = str.contains("-");
            if (con > 1 || nega == true)
                System.out.println("No es un numero valido");
        } while (con > 1 || nega == true);
        return Double.parseDouble(str);
    }

    public static String validad_Si_No(Scanner input){
        String res2 = input.next().toLowerCase();
        while (!(res2.equals("no")) && !(res2.equals("si"))) {
            System.out.println("Ingrese una respuesta valida");
            res2 = input.next().toLowerCase();
        }
        return res2;
    }

    public static int menu(Scanner sc, int i, ArrayList<Usuario> usuarios, ArrayList<Vehiculo> vehiculos){
        int menu = i;
        if (i == 0){
             
            System.out.println("1. Comprador\n2. Vendedor\n3. Salir");
            menu = Utilitaria.int_valido_rango(sc,"1","3");
            return menu;  
        }
    
        else if(i == 1){
            int op = 0;
                System.out.println("1. Registrar un nuevo comprador\n2. Ofertar por un vehículo\n3. Regresar");
                op = Utilitaria.int_valido_rango(sc,"1","3");
          
            if (op == 1){
                Utilitaria.registrarUsuario(i);
            }

            else if(op == 2){
                    sc.nextLine();
                    System.out.println("Ingrese su correo:");
                    String cor = sc.nextLine();
                    System.out.println("Ingrese su contraseña: ");
                    String contra = sc.nextLine();
                    if (Utilitaria.validar_clave(cor,contra,"Compradores.txt")){
                        Comprador c = (Comprador)Utilitaria.filtrar_usuario(cor, usuarios);
                        c.ofertar_Vehiculo(Comprador.filtrar_Vehiculos(vehiculos));
                    }
                    else{
                        System.out.println("El usuario o la contraseña es incorrecto");
                    }
            }

            else if(op == 3){
                menu = 0;
                return menu;
            }
            return menu;
        }
        else if (i == 2){
            int op = 0;
            
                System.out.println("1. Registrar un nuevo vendedor\n2. Registrar un nuevo vehiculo\n3. Aceptar oferta\n4. Regresar");
                op = Utilitaria.int_valido_rango(sc,"1","4");
            
            if (op == 1){
                Utilitaria.registrarUsuario(i);
            }
            else if(op == 2){
                System.out.println("Ingrese su correo:");
                String cor = sc.next();
                System.out.println("Ingrese su contraseña: ");
                String contra = sc.next();
                if (Utilitaria.validar_clave(cor,contra,"Vendedores.txt")){
                    Vendedor v = (Vendedor)Utilitaria.filtrar_usuario(cor,usuarios);
                    v.nuevoVehiculo(sc);
                }
                else{
                    System.out.println("El usuario o la contraseña es incorrecto");
                }
            }
            else if(op == 3){
                System.out.println("Ingrese su correo:");
                String cor = sc.next();
                System.out.println("Ingrese su contraseña: ");
                String contra = sc.next();
                if (Utilitaria.validar_clave(cor,contra,"Vendedores.txt")){
                    Vendedor v = (Vendedor)filtrar_usuario(cor,usuarios);
                    v.aceptarOferta(sc);
                }
                else{
                    System.out.println("El usuario o la contraseña es incorrecto");
                }
            }
            else{
                menu = 0;
                return menu;
            }
            return menu;
        }
        return -1;
    }
    
    public static void vehiculoBorrado(ArrayList<Vehiculo> vehiculos,String nombre){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nombre)))){
            for (Vehiculo v : vehiculos){
                pw.println(v.toString());
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            }
    }
}