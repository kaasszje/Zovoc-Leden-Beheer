package nl.fam_krijgsman.zovoc.mvc;

import nl.fam_krijgsman.zovoc.data.LidData;
import nl.fam_krijgsman.zovoc.data.TeamData;
import nl.fam_krijgsman.zovoc.data.UserLoginData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataTest {

    @Test
    void UserLoginModelTest() {
        UserLoginModel userLoginModel = new UserLoginModel();
        userLoginModel.setUserLogins(UserLoginData.addTestUsers());
        Assertions.assertAll(
                () -> assertFalse(userLoginModel.isValidLogin("martijn", "martijn")),
                () -> assertTrue(userLoginModel.isValidLogin("martijn", "test"))
        );
    }

    @Test
    void TeamandLidDataTest() {
        BeheerModel vereniging = new BeheerModel();
        vereniging.setTeams(TeamData.addTeamData());
        vereniging.setLeden(LidData.addLidData(vereniging.getTeams()));
        Assertions.assertAll(
                () -> assertEquals(9, vereniging.getTeams().size()),
                () -> assertEquals(61, vereniging.getLeden().size())
        );
    }

}
