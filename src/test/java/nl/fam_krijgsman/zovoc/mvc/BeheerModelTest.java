package nl.fam_krijgsman.zovoc.mvc;

import nl.fam_krijgsman.zovoc.model.Lid;
import nl.fam_krijgsman.zovoc.model.Team;
import nl.fam_krijgsman.zovoc.model.eGeslacht;
import nl.fam_krijgsman.zovoc.model.eKlasse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BeheerModelTest {
    BeheerModel beheerModel;
    BeheerModel.TeamModel teamModel;
    BeheerModel.LedenModel ledenModel;

    @BeforeEach
    void init() {
        beheerModel = new BeheerModel();
        for (int i = 0; i < 10; i++) {
            beheerModel.addTeam(new Team("team " + i, eKlasse.SENIOR, eGeslacht.MAN));
        }
        teamModel = beheerModel.getTeamModel();
        for (int i = 0; i < 10; i++) {
            beheerModel.addLid(new Lid("achternaam" + i,
                    "voornaam" + i,
                    "",
                    "0626454220",
                    "test@example.com",
                    1981,
                    eGeslacht.MAN
            ));
        }
        ledenModel = beheerModel.getLedenModel();
    }

    @Test
    @DisplayName("Team: Valideer kolom aantal")
    void getTeamModelColumnCount() {
        assertEquals(3, teamModel.getColumnCount());
    }

    @Test
    @DisplayName("Team: Valideer kolom namen")
    void getTeamModelColumnNames() {
        Assertions.assertAll(
                () -> assertEquals("Teamnaam", teamModel.getColumnName(0)),
                () -> assertEquals("Klasse", teamModel.getColumnName(1)),
                () -> assertEquals("Geslacht", teamModel.getColumnName(2))
        );
    }

    @Test
    @DisplayName("Team: Valideer rij aantal")
    void getTeamModelRowCount() {
        assertEquals(10, teamModel.getRowCount());
    }

    @Test
    @DisplayName("Team: Valideeer kolom classes")
    void getTeamModelColumnClasses() {
        Assertions.assertAll(
                () -> assertEquals(String.class, teamModel.getColumnClass(0)),
                () -> assertEquals(eKlasse.class, teamModel.getColumnClass(1)),
                () -> assertEquals(eGeslacht.class, teamModel.getColumnClass(2))
        );
    }

    @Test
    @DisplayName("Team: Haal waarde uit niet bestaande kolom")
    void getTeamModelNonExistingColumn() {
        assertNull(teamModel.getValueAt(0, 5));
    }

    @Test
    @DisplayName("Team: Valideer aantal waarden team")
    void getTeamModelValues() {
        Assertions.assertAll(
                () -> assertEquals("team 0", teamModel.getValueAt(0, 0)),
                () -> assertEquals(eKlasse.SENIOR, teamModel.getValueAt(3, 1)),
                () -> assertEquals(eGeslacht.MAN, teamModel.getValueAt(5, 2))
        );
    }

    @Test
    @DisplayName("Team: Is cell aan te passen team")
    void getTeamModelIsCellEditable() {
        assertTrue(teamModel.isCellEditable(0, 0));
    }

    @Test
    @DisplayName("Team: Verwijder team")
    void getTeamModelRemoveTeam() {
        assertTrue(teamModel.removeTeam(1));
    }

    @Test
    @DisplayName("Team: Verwijder niet bestaand team")
    void getTeamModelOutOfBounds() {
        //assertThrows(IndexOutOfBoundsException.class, () -> teamModel.removeTeam(10));
        assertFalse(teamModel.removeTeam(25));
    }

    @Test
    @DisplayName("Team: Team set value")
    void getTeamModelSetValue() {
        String teamtest = "teamtest";
        teamModel.setValueAt(teamtest, 2, 0);
        teamModel.setValueAt(eGeslacht.VROUW, 1, 2);
        teamModel.setValueAt(eKlasse.JUNIOR, 1, 1);
        Assertions.assertAll(
                () -> assertEquals(teamtest, teamModel.getValueAt(2, 0)),
                () -> assertEquals(eGeslacht.VROUW, teamModel.getValueAt(1, 2)),
                () -> assertEquals(eKlasse.JUNIOR, teamModel.getValueAt(1, 1))
        );


    }

    @Test
    @DisplayName("Leden: Valideer kolom aantal")
    void getLedenModelColumnCount() {
        assertEquals(8, ledenModel.getColumnCount());
    }

    @Test
    @DisplayName("Leden: Valideer kolom namen")
    void getLedenModelColumnNames() {
        Assertions.assertAll(
                () -> assertEquals("Achternaam", ledenModel.getColumnName(0)),
                () -> assertEquals("Voornaam", ledenModel.getColumnName(1)),
                () -> assertEquals("TussenVoegsel", ledenModel.getColumnName(2)),
                () -> assertEquals("TelefoonNummer", ledenModel.getColumnName(3)),
                () -> assertEquals("e-mail", ledenModel.getColumnName(4)),
                () -> assertEquals("GeboorteJaar", ledenModel.getColumnName(5)),
                () -> assertEquals("Geslacht", ledenModel.getColumnName(6)),
                () -> assertEquals("Team", ledenModel.getColumnName(7))
        );
    }

    @Test
    @DisplayName("Leden: Valideer kolom classes")
    void getLedenModelColumnClasses() {
        Assertions.assertAll(
                () -> assertEquals(String.class, ledenModel.getColumnClass(0)),
                () -> assertEquals(String.class, ledenModel.getColumnClass(1)),
                () -> assertEquals(String.class, ledenModel.getColumnClass(2)),
                () -> assertEquals(String.class, ledenModel.getColumnClass(3)),
                () -> assertEquals(String.class, ledenModel.getColumnClass(4)),
                () -> assertEquals(Integer.class, ledenModel.getColumnClass(5)),
                () -> assertEquals(eGeslacht.class, ledenModel.getColumnClass(6)),
                () -> assertEquals(Team.class, ledenModel.getColumnClass(7))
        );
    }

    @Test
    @DisplayName("Leden: Valideer leden aantal")
    void getLedenModelRowCount() {
        assertEquals(10, ledenModel.getRowCount());
    }

    @Test
    @DisplayName("Leden: Haal waarde uit niet bestaande kolom")
    void getLedenModelNonExistingColumn() {
        assertNull(ledenModel.getValueAt(0, 12));
    }

    @Test
    @DisplayName("Leden: Haal waarde uit verschillende rijen")
    void getLedenModelValues() {
        Assertions.assertAll(
                () -> assertEquals("achternaam1", ledenModel.getValueAt(1, 0)),
                () -> assertEquals("voornaam2", ledenModel.getValueAt(2, 1)),
                () -> assertEquals("", ledenModel.getValueAt(3, 2)),
                () -> assertEquals("0626454220", ledenModel.getValueAt(4, 3)),
                () -> assertEquals("test@example.com", ledenModel.getValueAt(5, 4)),
                () -> assertEquals(1981, ledenModel.getValueAt(1, 5)),
                () -> assertEquals(eGeslacht.MAN, ledenModel.getValueAt(7, 6))
        );
    }

    @Test
    @DisplayName("Leden: Is cell aan te passen (fout)")
    void getLedenModelIsCellEditableFalse() {
        assertFalse(ledenModel.isCellEditable(0, 0));
    }

    @Test
    @DisplayName("Leden: Is cell aan te passen (goed)")
    void getLedenModelIsCellEditableTrue() {
        assertTrue(ledenModel.isCellEditable(0, 3));
    }

    @Test
    @DisplayName("Leden: Verwijder lid")
    void getLedenModelRemoveLeden() {
        assertTrue(ledenModel.removeLid(1));
    }

    @Test
    @DisplayName("Leden: Verwijder niet bestaand Leden")
    void getLedenModelOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> ledenModel.removeLid(12));
    }

    @Test
    @DisplayName("Leden: Leden set value")
    void getLedenModelSetValue() {
        ledenModel.setValueAt("van", 2, 2);
        ledenModel.setValueAt("0626454220", 4, 3);
        ledenModel.setValueAt(1835, 7, 5);
        ledenModel.setValueAt(eGeslacht.VROUW, 1, 6);
        Assertions.assertAll(
                () -> assertEquals("van", ledenModel.getValueAt(2, 2)),
                () -> assertEquals("0626454220", ledenModel.getValueAt(4, 3)),
                () -> assertEquals(1835, ledenModel.getValueAt(7, 5)),
                () -> assertEquals(eGeslacht.VROUW, ledenModel.getValueAt(1, 6))
        );
    }

    @Test
    @DisplayName("Leden: Verwijder team van lid")
    void removeTeamFromLid() {
        Team team = beheerModel.getTeams().get(0);
        Lid lid = beheerModel.getLeden().get(0);
        team.setNaam("testteam");
        lid.setTeam(team);
        assertEquals("testteam", beheerModel.getLedenModel().getValueAt(0, 7));
        beheerModel.removeTeamFromLid(team);
        assertEquals("", beheerModel.getLedenModel().getValueAt(0, 7));
    }
}