package nl.fam_krijgsman.zovoc.data;

import nl.fam_krijgsman.zovoc.mvc.Password;

import java.util.HashMap;
import java.util.Map;

public class UserLoginData {

    public static Map<String, Password> maakTestUsers() {
        Map<String, Password> userLogins = new HashMap<>();

        // Add test logins
        userLogins.put("zovocuser", new Password("zovocpassword"));
        userLogins.put("admin", new Password("password"));
        userLogins.put("martijn", new Password("test"));
        userLogins.put("test", new Password("test"));
        return userLogins;
    }

}
