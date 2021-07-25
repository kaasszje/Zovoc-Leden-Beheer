package nl.fam_krijgsman.zovoc.model;

public enum eKlasse {
    JUNIOR("Junior"), SENIOR("Senior"), PROMOTIE("Promotie"), EREDIVISIE("Eredivisie");
    private final String schrijfNaam;

    eKlasse(String schrijfNaam) {
        this.schrijfNaam = schrijfNaam;
    }

    @Override
    public String toString() {
        return schrijfNaam;
    }
}
