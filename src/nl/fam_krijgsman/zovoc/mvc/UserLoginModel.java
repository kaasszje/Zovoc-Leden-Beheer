package nl.fam_krijgsman.zovoc.mvc;

import java.util.HashMap;

public class UserLoginModel {
    private HashMap<String,String> userLogins;
    private Boolean isLoggedIn = false;
    private String userName;

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

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName != null) {
            this.userName = userName;
            this.isLoggedIn = true;
        }

    }
}
