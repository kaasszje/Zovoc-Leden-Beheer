package nl.fam_krijgsman.zovoc.mvc;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class Password {
    private final byte[] hashedPassword;
    private final byte[] passwordSalt;

    public Password(String password) throws NoSuchAlgorithmException {
        this.passwordSalt = getSalt();
        this.hashedPassword = hashPassword(password);
    }

    private byte[] hashPassword(String plainPassword) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(this.passwordSalt);
        return md.digest(plainPassword.getBytes(StandardCharsets.UTF_8));
    }

    public boolean isValidPassword(String password) throws NoSuchAlgorithmException {
        return passwordString(this.hashedPassword).equals(passwordString(hashPassword(password)));
    }

    private String passwordString (byte[] password) {
        StringBuilder sb = new StringBuilder();
        //for(int i=0; i< password.length ;i++)
        for (byte pb: password)
        {
            sb.append(Integer.toString((pb & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    private byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}
