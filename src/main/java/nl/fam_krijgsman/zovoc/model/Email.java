package nl.fam_krijgsman.zovoc.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    private String email;

    public Email(String email) {
        setEmail(email);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        if (isValideEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Geen valide email-adres");
        }
    }

    public static boolean isValideEmail(String email){
        if (email == null) return false;
        String emailExpressie = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern emailPatroon = Pattern.compile(emailExpressie);
        Matcher isEmail = emailPatroon.matcher(email);
        return isEmail.matches();
    }
}
