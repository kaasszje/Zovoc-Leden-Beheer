package nl.fam_krijgsman.zovoc.model.classes;

import com.sun.istack.internal.NotNull;
import nl.fam_krijgsman.zovoc.generic.Helper;
import nl.fam_krijgsman.zovoc.model.enums.eGeslacht;
import nl.fam_krijgsman.zovoc.model.enums.eKlasse;

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
}
