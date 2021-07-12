package nl.fam_krijgsman.zovoc.data;

import java.util.HashMap;

public class UserLoginData {

    public static HashMap<String, String> addTestUsers() {
        HashMap<String,String> userLogins = new HashMap<>();

        // Add test logins
        userLogins.put("zovocuser","zovocpassword");
        userLogins.put("admin","password");
        userLogins.put("martijn","test");
        return userLogins;
    }

}
