package nl.fam_krijgsman.zovoc.model.classes;

import nl.fam_krijgsman.zovoc.model.enums.eKlasse;

import java.time.Year;
import java.util.Date;

public class Lid {
    private String achterNaam, voorNaam, tussenVoegsel, telefoonNummer, email;
    private Adres adres;
    private Team team;
    private int geboorteJaar;

    public Lid(String achterNaam, String voorNaam, String tussenVoegsel, String telefoonNummer, String email, Adres adres, int geboorteJaar) {
        this.achterNaam = achterNaam;
        this.voorNaam = voorNaam;
        this.tussenVoegsel = tussenVoegsel;
        this.telefoonNummer = telefoonNummer;
        this.email = email;
        this.adres = adres;
        this.geboorteJaar = geboorteJaar;
    }

    public String getAchterNaam() {
        return achterNaam;
    }

    public String getVoorNaam() {
        return voorNaam;
    }

    public String getTussenVoegsel() {
        return tussenVoegsel;
    }

    public String getTelefoonNummer() {
        return telefoonNummer;
    }

    public String getEmail() {
        return email;
    }

    public Adres getAdres() {
        return adres;
    }

    public Team getTeam() {
        return team;
    }

    public int getGeboorteJaar() {
        return geboorteJaar;
    }

    public void setTeam(Team team) {
        if (team.getKlasse().equals(eKlasse.JUNIOR)) {
            // Lid moet jonger zijn dan 18 voor junior klasse
            int currentYear = Year.now().getValue();
            if ((currentYear - this.geboorteJaar) < 18) {
                this.team = team;
            } else {
                System.out.println("This is not allowed");
            }
        } else {
            this.team = team;
        }
    }
}
