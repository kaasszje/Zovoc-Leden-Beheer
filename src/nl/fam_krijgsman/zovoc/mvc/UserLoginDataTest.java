package nl.fam_krijgsman.zovoc.mvc;

import nl.fam_krijgsman.zovoc.data.LidData;
import nl.fam_krijgsman.zovoc.data.TeamData;
import nl.fam_krijgsman.zovoc.data.UserLoginData;
import nl.fam_krijgsman.zovoc.model.Vereniging;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginDataTest {

    @Test
    void UserLoginModelTest() {
        UserLoginModel userLoginModel = new UserLoginModel();
        userLoginModel.setUserLogins(UserLoginData.addTestUsers());
        assertFalse(userLoginModel.isValidLogin("martijn", "martijn"));
        assertTrue(userLoginModel.isValidLogin("martijn", "test"));
    }

    @Test
    void TeamandLidDataTest() {
        BeheerModel vereniging = new BeheerModel();
        vereniging.setTeams(TeamData.addTeamData());
        vereniging.setLeden(LidData.addLidData(vereniging.getTeams()));
        assertEquals(9, vereniging.getTeams().size());
        assertEquals(61, vereniging.getLeden().size());
    }

}
