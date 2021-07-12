package nl.fam_krijgsman.zovoc.mvc;

public class Beheer {
    public static void startBeheer(String naam) {
        BeheerModel beheerModel = new BeheerModel();
        BeheerView beheerView = new BeheerView(naam);
        BeheerController beheerController = new BeheerController(beheerView, beheerModel);

    }
}
