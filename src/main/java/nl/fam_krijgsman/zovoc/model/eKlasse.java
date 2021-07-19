package nl.fam_krijgsman.zovoc.model;

public enum eKlasse {
    JUNIOR("Junior"), SENIOR("Senior"), PROMOTIE("Promotie"), EREDIVISIE("Eredivisie");
    String naam;

    eKlasse(String naam) {
        this.naam = naam;
    }

    @Override
    public String toString() {
        return naam;
    }
}
