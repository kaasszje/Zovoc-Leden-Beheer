package nl.fam_krijgsman.zovoc.model;

import nl.fam_krijgsman.zovoc.model.Lid;
import nl.fam_krijgsman.zovoc.model.Team;
import nl.fam_krijgsman.zovoc.model.Vereniging;
import nl.fam_krijgsman.zovoc.model.eGeslacht;
import nl.fam_krijgsman.zovoc.model.eKlasse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerenigingTest {
    static Lid lid = new Lid("Krijgsman", "Martijn", "", "", "", null, 1981, eGeslacht.MAN);
    static Team team = new Team("Test team", eKlasse.PROMOTIE, eGeslacht.MAN);
    static Vereniging vereniging;

    @Test
    void findLid() {
        vereniging = new Vereniging("test");
        vereniging.addLid(lid);
        assertEquals(lid, vereniging.findLid(lid.getAchterNaam(), lid.getVoorNaam()));
        assertNull(vereniging.findLid("ik besta", "niet"));
    }

    @Test
    void addLid() {
        vereniging = new Vereniging("test");
        //voeg lid toe die al vastaat moet niet kunnen
        vereniging.addLid(lid);
        //2e keer zelf lid toevoegen
        assertFalse(vereniging.addLid(lid));
    }
    @Test
    void removeLid() {
        vereniging = new Vereniging("test");
        // vereniging mag nog geen leden bevatten
        assertEquals(0,vereniging.getLeden().size());
        assertTrue(vereniging.addLid(lid));
        //na toevoeging moet er 1 lid zijn
        assertEquals(1, vereniging.getLeden().size());
        assertTrue(vereniging.removeLid(lid));
        // nu wederom 0 leden
        assertEquals(0, vereniging.getLeden().size());

        // een nietbestaand lid kan niet worden verwijderd
        assertFalse(vereniging.removeLid(lid));
    }

    @Test
    void findTeam() {
        vereniging = new Vereniging("test");
        vereniging.addTeam(team);
        assertEquals(team, vereniging.findTeam(team.getNaam()));
        assertNull(vereniging.findTeam("niet bestaand team"));
    }

    @Test
    void addTeam() {
        vereniging = new Vereniging("test");
        vereniging.addTeam(team);
        assertFalse(vereniging.addTeam(team));
    }

    @Test
    void removeTeam() {
        vereniging = new Vereniging("test");
        assertEquals(0, vereniging.getTeams().size());

        assertTrue(vereniging.addTeam(team));
        assertEquals(1, vereniging.getTeams().size());

        assertTrue(vereniging.removeTeam(team));
        assertEquals(0, vereniging.getTeams().size());

        // Een niet bestand team kan niet worden verwijderd
        assertFalse(vereniging.removeTeam(team));

    }

    @Test
    void aantalLeden() {
        vereniging = new Vereniging("test");
        for (int i=0;i<10;i++) {
            lid = new Lid("nummer-" + i, "naam-" + i, "", "", "", null, 1981, eGeslacht.MAN);
            vereniging.addLid(lid);
        }
        assertEquals(10, vereniging.aantalLeden());
    }


    @Test
    void getNaam() {
        vereniging = new Vereniging("test");
        assertEquals("test", vereniging.getNaam());
    }

    @Test
    void getLeden() {
        vereniging = new Vereniging("test");
        for (int i=0;i<10;i++) {
            lid = new Lid("nummer-" + i, "naam-" + i, "", "", "", null, 1981, eGeslacht.MAN);
            vereniging.addLid(lid);
        }
        assertEquals(10, vereniging.getLeden().size());
    }

    @Test
    void getTeams() {
        vereniging = new Vereniging("test");
        for (int i=0; i<10;i++) {
            team = new Team("team" + i,eKlasse.SENIOR, eGeslacht.VROUW);
            vereniging.addTeam(team);
        }
        assertEquals(10, vereniging.getTeams().size());
    }
}