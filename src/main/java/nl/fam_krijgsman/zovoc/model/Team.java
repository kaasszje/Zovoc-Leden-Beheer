package nl.fam_krijgsman.zovoc.model;


import java.time.Year;

public class Team {
    private String naam;
    private eKlasse klasse;
    private eGeslacht geslacht;

    public Team(String naam, eKlasse klasse, eGeslacht geslacht) {
        this.setNaam(naam);
        this.klasse = klasse;
        this.geslacht = geslacht;
    }

    public String getNaam() {
        return naam;
    }

    public eKlasse getKlasse() {
        return klasse;
    }

    public eGeslacht getGeslacht() {
        return geslacht;
    }

    public void setKlasse(eKlasse klasse) {
        this.klasse = klasse;
    }

    public void setGeslacht(eGeslacht geslacht) {
        this.geslacht = geslacht;
    }

    public void setNaam(String naam) {
        if ((naam == null) || (naam.isEmpty())) {
            throw new IllegalArgumentException("Naam mag niet leeg zijn");
        } else {
            this.naam = naam;
        }
    }

    public boolean magInTeam(eGeslacht geslacht, int geboorteJaar) {
        boolean isGoedeLeeftijd = false;
        boolean isGoedeGeslacht = false;
        if (this.klasse.equals(eKlasse.JUNIOR)) {
            // Lid moet jonger zijn dan 18 voor junior klasse
            int currentYear = Year.now().getValue();
            if ((currentYear - geboorteJaar) < 18) {
                isGoedeLeeftijd = true;
            }
        } else {
            isGoedeLeeftijd = true;
        }
        if ((this.geslacht.equals(geslacht)) || this.geslacht.equals(eGeslacht.MIX)) {
            isGoedeGeslacht = true;
        }
        return ((isGoedeGeslacht) && (isGoedeLeeftijd));
    }
}
