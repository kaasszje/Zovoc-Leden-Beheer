package nl.fam_krijgsman.zovoctester.model;

import nl.fam_krijgsman.zovoc.model.UserLoginModel;
import nl.fam_krijgsman.zovoc.data.UserLoginData;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginModelTest {

    @Test
    void isValidLogin() {
        // test 1 niet bestaande gebruiker
        UserLoginModel userLoginModel = new UserLoginModel();
        HashMap<String,String> userLogins = new HashMap<>();
        for (int i=0;i<10;i++){
            userLogins.put("test"+i, "test"+i);
        }
        userLoginModel.setUserLogins(userLogins);


        String user = "test";
        String password = "test";
        assertFalse(userLoginModel.isValidLogin(user, password));

        // test 2 fout wachtwoord
        user = "test1";
        password = "test2";
        assertFalse(userLoginModel.isValidLogin(user, password));

        // test 3 valide inlog
        user = "test1";
        password = "test1";
        assertTrue(userLoginModel.isValidLogin(user, password));

        // test 4 valide inlog
        user = "test3";
        password = "test3";
        assertTrue(userLoginModel.isValidLogin(user, password));
    }
}