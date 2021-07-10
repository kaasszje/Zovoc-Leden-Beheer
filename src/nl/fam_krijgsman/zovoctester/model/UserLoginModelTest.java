package nl.fam_krijgsman.zovoctester.model;

import nl.fam_krijgsman.zovoc.model.UserLoginModel;
import nl.fam_krijgsman.zovoc.data.UserLoginData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginModelTest {

    @Test
    void isValidLogin() {
        // test 1 niet bestaande gebruiker
        UserLoginModel userLoginModel = new UserLoginModel();
        UserLoginData.addTestUsers(userLoginModel);

        String user = "test";
        String password = "test";
        assertFalse(userLoginModel.isValidLogin(user, password));

        // test 2 fout wachtwoord
        user = "martijn";
        password = "martijn";
        assertFalse(userLoginModel.isValidLogin(user, password));

        // test 3 valide inlog
        user = "martijn";
        password = "test";
        assertTrue(userLoginModel.isValidLogin(user, password));

        // test 4 valide inlog
        user = "zovocuser";
        password = "zovocpassword";
        assertTrue(userLoginModel.isValidLogin(user, password));
    }
}