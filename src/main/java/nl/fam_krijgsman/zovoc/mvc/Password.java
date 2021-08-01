package nl.fam_krijgsman.zovoc.mvc;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class Password {
    private final byte[] hashedPassword;
    private final byte[] passwordSalt;

    public Password(String password) {
        this.passwordSalt = getSalt();
        this.hashedPassword = hashPassword(password);
    }

    private byte[] hashPassword(String plainPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(this.passwordSalt);
            return md.digest(plainPassword.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            // should not happen.
            return null;
        }
    }

    public boolean isValidPassword(String password) {
        return passwordString(this.hashedPassword).equals(passwordString(hashPassword(password)));
    }

    private String passwordString (byte[] password) {
        return new String(password, StandardCharsets.UTF_8);
    }

    private byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}
