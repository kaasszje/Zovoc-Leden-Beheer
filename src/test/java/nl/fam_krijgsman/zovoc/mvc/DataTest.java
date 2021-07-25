package nl.fam_krijgsman.zovoc.mvc;

import nl.fam_krijgsman.zovoc.data.LidData;
import nl.fam_krijgsman.zovoc.data.TeamData;
import nl.fam_krijgsman.zovoc.data.UserLoginData;
import nl.fam_krijgsman.zovoc.model.Lid;
import nl.fam_krijgsman.zovoc.model.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        List<Team> teams = TeamData.addTeamData();
        for (Team team: teams) {
            vereniging.addTeam(team);
        }
        List<Lid> leden = LidData.addLidData(vereniging.getTeams());
        for (Lid lid: leden){
            vereniging.addLid(lid);
        }
        Assertions.assertAll(
                () -> assertEquals(9, vereniging.getTeams().size()),
                () -> assertEquals(61, vereniging.getLeden().size())
        );
    }

}
