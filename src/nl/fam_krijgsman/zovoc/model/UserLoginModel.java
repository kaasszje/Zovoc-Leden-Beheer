package nl.fam_krijgsman.zovoc.model;

import java.util.HashMap;

public class UserLoginModel {
    private HashMap<String,String> userLogins = new HashMap<>();

    public UserLoginModel() {
        //add test data
        userLogins.put("zovocuser","zovocpassword");
        userLogins.put("admin","password");
        userLogins.put("martijn","test");
    }

    public final boolean isValidLogin(String user, String password) {
        try {
            return userLogins.get(user).equals(password);
        } catch (NullPointerException e) {
            return false;
        }
    }
}
