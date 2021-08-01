package nl.fam_krijgsman.zovoc.mvc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


class UserLoginModelTest {

    @Test
    void isValidLogin() {
        // test 1 niet bestaande gebruiker
        UserLoginModel userLoginModel = new UserLoginModel();
        HashMap<String,Password> userLogins = new HashMap<>();
        for (int i=0;i<10;i++){
            userLogins.put("test"+i, new Password("test"+i));
        }
        userLoginModel.setUserLogins(userLogins);

        Assertions.assertAll(
                () -> assertFalse(userLoginModel.isValidLogin("test", "test")),
                () -> assertFalse(userLoginModel.isValidLogin("test1", "test2")),
                () -> assertTrue(userLoginModel.isValidLogin("test1", "test1")),
                () -> assertTrue(userLoginModel.isValidLogin("test3", "test3"))
        );
    }

    @Test
    void setUserName() {
        UserLoginModel userLoginModel = new UserLoginModel();
        userLoginModel.setUserName("martijn");
        Assertions.assertAll(
                () -> assertTrue(userLoginModel.getLoggedIn()),
                () -> assertEquals("martijn", userLoginModel.getUserName())
        );
    }
}