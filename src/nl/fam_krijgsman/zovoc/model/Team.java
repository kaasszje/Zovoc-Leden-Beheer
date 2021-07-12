package nl.fam_krijgsman.zovoc.model;

import com.sun.istack.internal.NotNull;
import nl.fam_krijgsman.zovoc.generic.Helper;

public class Team {
    private String naam;
    private eKlasse klasse;
    private eGeslacht geslacht;

    public Team(@NotNull String naam, @NotNull eKlasse klasse, @NotNull eGeslacht geslacht) {
        this.naam = Helper.isNotNull(naam);
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
        this.naam = naam;
    }
}
