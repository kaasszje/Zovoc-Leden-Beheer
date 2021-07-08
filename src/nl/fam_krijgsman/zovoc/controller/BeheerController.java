package nl.fam_krijgsman.zovoc.controller;

import nl.fam_krijgsman.zovoc.model.BeheerModel;
import nl.fam_krijgsman.zovoc.view.BeheerView;

public class BeheerController {
    BeheerView beheerView;
    BeheerModel beheerModel;

    public BeheerController(BeheerView beheerView, BeheerModel beheerModel) {
        this.beheerView = beheerView;
        this.beheerModel = beheerModel;
    }
}
