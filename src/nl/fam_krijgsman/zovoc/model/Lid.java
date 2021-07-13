package nl.fam_krijgsman.zovoc.model;

import nl.fam_krijgsman.zovoc.generic.Helper;
import org.jetbrains.annotations.NotNull;

import java.time.Year;

public class Lid {
    private String achterNaam, voorNaam, tussenVoegsel, telefoonNummer, email;
    private Adres adres;
    private Team team;
    private int geboorteJaar;
    private eGeslacht geslacht;

    public Lid(@NotNull String achterNaam, @NotNull String voorNaam, String tussenVoegsel, String telefoonNummer, String email, Adres adres, @NotNull Integer geboorteJaar, @NotNull eGeslacht geslacht) {

        this.achterNaam = Helper.isNotNull(achterNaam);
        this.voorNaam = Helper.isNotNull(voorNaam);

        this.voorNaam = voorNaam;
        this.tussenVoegsel = tussenVoegsel;

        //Controlle op valide telefoonnummer
        this.setTelefoonNummer(telefoonNummer);
        //Controlle op valide email
        this.setEmail(email);

        this.adres = adres;
        this.geboorteJaar = geboorteJaar;
        this.geslacht = geslacht;
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

    public boolean setTeam(Team team) {
        boolean isGoedeLeeftijd = false;
        boolean isGoedeGeslacht = false;
        if (team.getKlasse().equals(eKlasse.JUNIOR)) {
            // Lid moet jonger zijn dan 18 voor junior klasse
            int currentYear = Year.now().getValue();
            if ((currentYear - this.geboorteJaar) < 18) {
                isGoedeLeeftijd = true;
            }
        } else {
            isGoedeLeeftijd = true;
        }
        switch (team.getGeslacht()) {
            case MAN:
                if (this.geslacht == eGeslacht.MAN) {
                    isGoedeGeslacht = true;
                }
                break;
            case VROUW:
                if (this.geslacht == eGeslacht.VROUW) {
                    isGoedeGeslacht = true;
                }
                break;
            default:
                isGoedeGeslacht = true;
        }
        if ((isGoedeGeslacht) && (isGoedeLeeftijd)) {
            this.team = team;
            return true;
        } else {
            return false;
        }
    }

    public void setAchterNaam(String achterNaam) {
        if (achterNaam.length() >= 1) {
            this.achterNaam = achterNaam;
        }
    }

    public void setVoorNaam(String voorNaam) {
        if (voorNaam.length() >= 1) {
            this.voorNaam = voorNaam;
        }
    }

    public void setTussenVoegsel(String tussenVoegsel) {
        this.tussenVoegsel = tussenVoegsel;
    }

    public void setTelefoonNummer(String telefoonNummer) {
        if (Helper.checkPhoneNumber(telefoonNummer)) {
            this.telefoonNummer = telefoonNummer;
        } else {
            this.telefoonNummer = null;
        }
    }

    public boolean setEmail(String email) {
        if (Helper.checkEmail(email)) {
            this.email = email;
            return true;
        } else {
            //this.email = null;
            return false;
        }
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public void setGeboorteJaar(int geboorteJaar) {
        this.geboorteJaar = geboorteJaar;
    }
    public eGeslacht getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(eGeslacht geslacht) {
        this.geslacht = geslacht;
    }
}
