package nl.fam_krijgsman.zovoc.mvc;

import java.util.HashMap;
import java.util.Map;

class UserLoginModel {
    private Map<String, String> userLogins;
    private Boolean isLoggedIn = false;
    private String userName;

    public UserLoginModel() {
        this.userLogins = new HashMap<>();
    }

    public final boolean isValidLogin(String user, String password) {
        try {
            return this.userLogins.get(user).equals(password);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public void setUserLogins(Map<String, String> userLogins) {
        this.userLogins = userLogins;
    }

    public Boolean getLoggedIn() {
        return this.isLoggedIn;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        if (userName != null) {
            this.userName = userName;
            this.isLoggedIn = true;
        }

    }
}
