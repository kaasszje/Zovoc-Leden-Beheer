package nl.fam_krijgsman.zovoc.data;

import nl.fam_krijgsman.zovoc.model.UserLoginModel;

import java.util.HashMap;

public class UserLoginData {

    public static void addTestUsers(UserLoginModel userLoginModel) {
        HashMap<String,String> userLogins = new HashMap<>();

        // Add test logins
        userLogins.put("zovocuser","zovocpassword");
        userLogins.put("admin","password");
        userLogins.put("martijn","test");
        userLoginModel.setUserLogins(userLogins);
    }

}
