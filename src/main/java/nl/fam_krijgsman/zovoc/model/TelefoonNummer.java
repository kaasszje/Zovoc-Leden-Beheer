package nl.fam_krijgsman.zovoc.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelefoonNummer {
    private String telefoonNummer;

    public TelefoonNummer(String telefoonNummer) {
        this.setTelefoonNummer(telefoonNummer);
    }

    public String getTelefoonNummer() {
        return this.telefoonNummer;
    }

    public void setTelefoonNummer(String telefoonNummer) {
        if (isValideTelefoonNummer(telefoonNummer)) {
            this.telefoonNummer = telefoonNummer;
        } else {
            throw new IllegalArgumentException("Dit is geen valide telefoonnummer");
        }
    }

    public static boolean isValideTelefoonNummer(String telefoonNummer) {
        if (telefoonNummer == null) return false;
        String telefoonNummerExpressie = "^((\\+|00(\\s|\\s?-\\s?)?)31(\\s|\\s?-\\s?)?(\\(0\\)[\\-\\s]?)?|0)[1-9]((\\s|\\s?-\\s?)?[0-9])((\\s|\\s?-\\s?)?[0-9])((\\s|\\s?-\\s?)?[0-9])\\s?[0-9]\\s?[0-9]\\s?[0-9]\\s?[0-9]\\s?[0-9]$";
        Pattern telefoonNummerPatroon = Pattern.compile(telefoonNummerExpressie);
        Matcher isTelefoonNummer = telefoonNummerPatroon.matcher(telefoonNummer);
        return isTelefoonNummer.matches();
    }
}
