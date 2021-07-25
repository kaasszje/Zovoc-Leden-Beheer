package nl.fam_krijgsman.zovoc.model;

public class Lid {
    private String achterNaam, voorNaam, tussenVoegsel;
    private Email email;
    private TelefoonNummer telefoonNummer;
    private Team team;
    private int geboorteJaar;
    private eGeslacht geslacht;

    public Lid(String achterNaam, String voorNaam, String tussenVoegsel, String telefoonNummer, String email, Integer geboorteJaar, eGeslacht geslacht) {
        if ((achterNaam.isEmpty()) || voorNaam.isEmpty()) {
            throw new IllegalArgumentException("Waarde mag niet leeg zijn");
        }
        this.achterNaam = achterNaam;
        this.voorNaam = voorNaam;
        this.tussenVoegsel = tussenVoegsel;
        this.telefoonNummer = new TelefoonNummer(telefoonNummer);
        this.email = new Email(email);
        this.geboorteJaar = geboorteJaar;
        this.geslacht = geslacht;
    }

    public String getAchterNaam() {
        return this.achterNaam;
    }

    public String getVoorNaam() {
        return this.voorNaam;
    }

    public String getTussenVoegsel() {
        return this.tussenVoegsel;
    }

    public String getTelefoonNummer() {
        return this.telefoonNummer.getTelefoonNummer();
    }

    public String getEmail() {
        return this.email.getEmail();
    }

    public Team getTeam() {
        return this.team;
    }

    public int getGeboorteJaar() {
        return this.geboorteJaar;
    }

    public void setTeam(Team team) {
        if (team == null) {
            this.team = null;
            return;
        }
        if (team.magInTeam(this.geslacht, this.geboorteJaar)) {
            this.team = team;
        } else {
            throw new IllegalArgumentException("Geen valide team voor dit lid");
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
        if (this.telefoonNummer != null) {
            this.telefoonNummer.setTelefoonNummer(telefoonNummer);
        } else {
            this.telefoonNummer = new TelefoonNummer(telefoonNummer);
        }
    }

    public void setEmail(String email) {
        if (this.email != null) {
            this.email.setEmail(email);
        } else {
            this.email = new Email(email);
        }
    }

    public void setGeboorteJaar(int geboorteJaar) {
        this.geboorteJaar = geboorteJaar;
    }

    public eGeslacht getGeslacht() {
        return this.geslacht;
    }

    public void setGeslacht(eGeslacht geslacht) {
        this.geslacht = geslacht;
    }
}
