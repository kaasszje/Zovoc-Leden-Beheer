package nl.fam_krijgsman.zovoc.MVC;

import nl.fam_krijgsman.zovoc.controller.UserLoginController;
import nl.fam_krijgsman.zovoc.view.UserLoginView;
import nl.fam_krijgsman.zovoc.model.UserLoginModel;

public class MVCUserLogin {

    public static void startUserLogin() {
        UserLoginModel userLoginModel = new UserLoginModel();
        UserLoginView userLoginView = new UserLoginView();
        UserLoginController userLoginController = new UserLoginController(userLoginView, userLoginModel);
    }

}
