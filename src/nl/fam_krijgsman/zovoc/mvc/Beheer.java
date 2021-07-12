package nl.fam_krijgsman.zovoc.mvc;

public class Beheer {
    public static void startBeheer(UserLoginModel userLoginModel) {
        if (userLoginModel.getLoggedIn()) {
            BeheerModel beheerModel = new BeheerModel();
            BeheerView beheerView = new BeheerView(userLoginModel.getUserName());
            BeheerController beheerController = new BeheerController(beheerView, beheerModel);
        } else {
            System.exit(0);
        }

    }
}
