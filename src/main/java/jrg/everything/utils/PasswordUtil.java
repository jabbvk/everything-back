package jrg.everything.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordUtil {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static byte[] hashPassword(String password) {
        String hashedPassword = passwordEncoder.encode(password); // Genera el hash de la contraseña
        return hashedPassword.getBytes(); // Convierte el hash en un array de bytes
    }
}
