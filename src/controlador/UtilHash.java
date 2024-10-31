package controlador;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class UtilHash {

    public static String hashearContrasena(String contrasena) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashContrasena = md.digest(contrasena.getBytes());
            return Base64.getEncoder().encodeToString(hashContrasena);
        } catch (NoSuchAlgorithmException ignored) {

        }
        return  contrasena;
    }
}
