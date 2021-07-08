package nl.fam_krijgsman.zovoc.model;

import nl.fam_krijgsman.zovoc.generic.Helper;
import nl.fam_krijgsman.zovoc.model.classes.Vereniging;

public class BeheerModel extends Vereniging {
    public BeheerModel() {
        super(Helper.getVerenigingNaam());
    }
}
