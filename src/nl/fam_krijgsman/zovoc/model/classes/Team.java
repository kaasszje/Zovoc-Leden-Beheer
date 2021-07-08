package nl.fam_krijgsman.zovoc.model.classes;

import nl.fam_krijgsman.zovoc.model.enums.eKlasse;

public class Team {
    private String naam;
    private eKlasse klasse;

    public Team(String naam, eKlasse klasse) {
        this.naam = naam;
        this.klasse = klasse;
    }

    public String getNaam() {
        return naam;
    }

    public eKlasse getKlasse() {
        return klasse;
    }
}
