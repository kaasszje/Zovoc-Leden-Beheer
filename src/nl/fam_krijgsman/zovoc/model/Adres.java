package nl.fam_krijgsman.zovoc.model;

public class Adres {
    private String straatNaam;
    private int huisNummer;
    private String postCode;
    private String plaats;

    public Adres(String straatNaam, int huisNummer, String postCode, String plaats) {
        this.straatNaam = straatNaam;
        this.huisNummer = huisNummer;
        this.postCode = postCode;
        this.plaats = plaats;
    }

    public String getStraatNaam() {
        return straatNaam;
    }

    public int getHuisNummer() {
        return huisNummer;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getPlaats() {
        return plaats;
    }
}
