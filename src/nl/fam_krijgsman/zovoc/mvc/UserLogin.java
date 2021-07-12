package nl.fam_krijgsman.zovoc.mvc;

public class UserLogin {

    public static void startUserLogin() {
        UserLoginModel userLoginModel = new UserLoginModel();
        UserLoginView userLoginView = new UserLoginView();
        UserLoginController userLoginController = new UserLoginController(userLoginView, userLoginModel);
    }

}
