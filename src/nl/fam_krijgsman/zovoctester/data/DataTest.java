package nl.fam_krijgsman.zovoctester.data;

import nl.fam_krijgsman.zovoc.data.LidData;
import nl.fam_krijgsman.zovoc.data.TeamData;
import nl.fam_krijgsman.zovoc.data.UserLoginData;
import nl.fam_krijgsman.zovoc.mvc.UserLoginModel;
import nl.fam_krijgsman.zovoc.model.Vereniging;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DataTest {

    @Test
    void UserLoginDataTest() {
        UserLoginModel userLoginModel = new UserLoginModel();
        UserLoginData.addTestUsers(userLoginModel);
        assertFalse(userLoginModel.isValidLogin("martijn", "martijn"));
        assertTrue(userLoginModel.isValidLogin("martijn", "test"));
    }

    @Test
    void TeamandLidDataTest() {
        Vereniging vereniging = new Vereniging("test");
        TeamData.addTeamData(vereniging);
        LidData.addLidData(vereniging);
        assertEquals(9, vereniging.getTeams().size());
        assertEquals(45, vereniging.getLeden().size());
    }

}
