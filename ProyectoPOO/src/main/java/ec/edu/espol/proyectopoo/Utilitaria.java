package ec.edu.espol.proyectopoo;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public boolean validar_clave(String correo, String clave, String nomfile) {
        try (Scanner sc = new Scanner(new File(nomfile))) {
            while (sc.hasNextLine()) {
                String[] info = sc.nextLine().split(",");
                if (correo.equals(info[3]) && Utilitaria.codificarHash(clave).equals(info[4]))
                    return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void registrarUsuario(int u) {
        Scanner input = new Scanner(System.in);
        String nombres, apellidos, organizacion, email, clave;

        input.useDelimiter("\n");
        System.out.println("Ingrese sus nombres: ");
        nombres = input.next();
        System.out.println("Ingrese sus apellidos: ");
        apellidos = input.next();
        System.out.println("Ingrese la Organización: ");
        organizacion = input.next();
        System.out.println("Ingrese su correo electrónico: ");
        email = input.nextLine();
        System.out.println("Ingrese su clave: ");
        clave = input.nextLine();
        try {
            clave = Utilitaria.codificarHash(clave);
        } catch (NoSuchAlgorithmException e) {
        }
        ;
        // clave = hash(clave);
        if (u == 1) {
            Vendedor vendedor = new Vendedor(nombres, apellidos, organizacion, email, clave);
            if (vendedor.validar_correo("Vendedores.txt"))
                System.out.println("El correo ya existe");
            else {
                vendedor.registrar("Vendedores.txt");
                System.out.println("Se ha registrado exitosamente");
            }
        } else if (u == 2) {
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

    public static int int_validado_pos(Scanner input) {
        String res;
        do {
            res = input.next();
        } while (!(res.matches("[0-9]+")));
        input.close();
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
}
