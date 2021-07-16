package nl.fam_krijgsman.zovoc.mvc;

import nl.fam_krijgsman.zovoc.model.Lid;
import nl.fam_krijgsman.zovoc.model.Team;
import nl.fam_krijgsman.zovoc.model.eGeslacht;
import nl.fam_krijgsman.zovoc.model.eKlasse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BeheerModelTest {

    @Test
    void getTeamModel() {
        BeheerModel beheerModel = new BeheerModel();
        ArrayList<Team> teams = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            teams.add(new Team("team " + i, eKlasse.SENIOR, eGeslacht.MAN));
        }
        beheerModel.setTeams(teams);
        BeheerModel.TeamModel teamModel = beheerModel.getTeamModel();
        Assertions.assertAll(
                () -> assertEquals(3, teamModel.getColumnCount()),
                () -> assertEquals("Teamnaam", teamModel.getColumnName(0)),
                () -> assertEquals(10, teamModel.getRowCount()),
                () -> assertEquals(eKlasse.class, teamModel.getColumnClass(1)),
                () -> assertNull(teamModel.getValueAt(0, 5)),
                () -> assertEquals("team 0", teamModel.getValueAt(0, 0)),
                () -> assertEquals(eKlasse.SENIOR, teamModel.getValueAt(3, 1)),
                () -> assertEquals(eGeslacht.MAN, teamModel.getValueAt(5, 2)),
                () -> assertTrue(teamModel.isCellEditable(0, 0)),
                () -> assertTrue(teamModel.removeTeam(1)),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> teamModel.removeTeam(10))
        );
        String teamtest = "teamtest";
        teamModel.setValueAt(teamtest, 2, 0);
        assertEquals(teamtest, teamModel.getValueAt(2, 0));
        teamModel.setValueAt(eGeslacht.VROUW, 1, 2);
        teamModel.setValueAt(eKlasse.JUNIOR, 1, 1);
        assertEquals(eGeslacht.VROUW, teamModel.getValueAt(1, 2));
        assertEquals(eKlasse.JUNIOR, teamModel.getValueAt(1, 1));

    }

    @Test
    void getLedenModel() {
        BeheerModel beheermodel = new BeheerModel();

        ArrayList<Lid> leden = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            leden.add(new Lid("achternaam" + i,
                    "voornaam" + i,
                    "",
                    "0626454220",
                    "test@example.com",
                    1981,
                    eGeslacht.MAN
            ));
        }
        beheermodel.setLeden(leden);
        BeheerModel.LedenModel lidModel = beheermodel.getLedenModel();
        Assertions.assertAll(
                () -> assertEquals(8, lidModel.getColumnCount()),
                () -> assertEquals("Achternaam", lidModel.getColumnName(0)),
                () -> assertEquals(10, lidModel.getRowCount()),
                () -> assertEquals(String.class, lidModel.getColumnClass(1)),
                () -> assertNull(lidModel.getValueAt(0, 12)),
                () -> assertFalse(lidModel.isCellEditable(2, 1)),
                () -> assertTrue(lidModel.removeLid(9))
        );
        lidModel.setValueAt("test", 2, 2);
        assertEquals("test", lidModel.getValueAt(2, 2));


    }

    @Test
    void removeTeamFromLid() {
        BeheerModel beheermodel = new BeheerModel();

        ArrayList<Lid> leden = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            leden.add(new Lid("achternaam" + i,
                    "voornaam" + i,
                    "",
                    "0626454220",
                    "m.s.krijgsman@gmail.com",
                    1981,
                    eGeslacht.MAN
            ));
        }

        Team team = new Team("testteam", eKlasse.SENIOR, eGeslacht.MIX);
        for (Lid lid : leden) {
            lid.setTeam(team);
        }
        beheermodel.setLeden(leden);
        assertEquals("testteam", beheermodel.getLedenModel().getValueAt(0, 7));

        beheermodel.removeTeamFromLid(team);
        assertEquals("", beheermodel.getLedenModel().getValueAt(0, 7));
    }
}