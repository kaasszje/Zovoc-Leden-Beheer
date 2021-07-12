package nl.fam_krijgsman.zovoc.mvc;

import java.util.HashMap;

public class UserLoginModel {
    private HashMap<String,String> userLogins;

    public UserLoginModel() {
        //add test data
        userLogins = new HashMap<>();
    }

    public final boolean isValidLogin(String user, String password) {
        try {
            return userLogins.get(user).equals(password);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public void setUserLogins(HashMap<String, String> userLogins) {
        this.userLogins = userLogins;
    }
}
