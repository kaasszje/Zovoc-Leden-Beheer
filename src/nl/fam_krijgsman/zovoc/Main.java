package nl.fam_krijgsman.zovoc;

import nl.fam_krijgsman.zovoc.controller.UserLoginController;
import nl.fam_krijgsman.zovoc.view.UserLoginView;
import nl.fam_krijgsman.zovoc.model.UserLoginModel;

public class Main {

    public static void main(String[] args) {
        UserLoginModel userLoginModel = new UserLoginModel();
        UserLoginView userLoginView = new UserLoginView();

        UserLoginController userLoginController = new UserLoginController(userLoginView, userLoginModel);

        userLoginView.setVisible(true);

    }

}
