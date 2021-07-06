package nl.fam_krijgsman.zovoc.testdata;

import java.util.HashMap;

public class Users {
    private HashMap<String,String> logininfo = new HashMap<>();

    public Users() {
        logininfo.put("zovocuser","zovocpassword");
        logininfo.put("admin","password");
        logininfo.put("martijn","test");
    }

    public boolean checkLogin(String user, String password) {
        try {
            return logininfo.get(user).equals(password);
        } catch (NullPointerException e) {
            return false;
        }
    }
}
