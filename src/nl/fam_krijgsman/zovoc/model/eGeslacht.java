package nl.fam_krijgsman.zovoc.model;

public enum eGeslacht {
    MAN("man"), VROUW("vrouw"), MIX("mix");

    String schrijfNaam;

    eGeslacht(String schrijfNaam) {
        this.schrijfNaam = schrijfNaam;
    }

    @Override
    public String toString() {
        return this.schrijfNaam;
    }
}
