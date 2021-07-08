package nl.fam_krijgsman.zovoc.MVC;

import nl.fam_krijgsman.zovoc.controller.BeheerController;
import nl.fam_krijgsman.zovoc.model.BeheerModel;
import nl.fam_krijgsman.zovoc.view.BeheerView;

public class MVCBeheer {
    public static void startBeheer(String naam) {
        BeheerModel beheerModel = new BeheerModel();
        BeheerView beheerView = new BeheerView(naam);
        BeheerController beheerController = new BeheerController(beheerView, beheerModel);

    }
}
