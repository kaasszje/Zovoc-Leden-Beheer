package nl.fam_krijgsman.zovoc.data;

import java.util.HashMap;
import java.util.Map;

public class UserLoginData {

    public static Map<String, String> maakTestUsers() {
        Map<String,String> userLogins = new HashMap<>();

        // Add test logins
        userLogins.put("zovocuser","zovocpassword");
        userLogins.put("admin","password");
        userLogins.put("martijn","test");
        userLogins.put("edward", "edward");
        return userLogins;
    }

}
