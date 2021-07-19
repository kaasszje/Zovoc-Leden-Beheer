package nl.fam_krijgsman.zovoc.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    @Test
    void getNaam() {
        Team team = new Team("test team", eKlasse.SENIOR, eGeslacht.MAN);
        assertEquals("test team", team.getNaam());
        team.setNaam("test2");
        assertEquals("test2", team.getNaam());
    }

    @Test
    void getKlasse() {
        Team team = new Team("test team", eKlasse.SENIOR, eGeslacht.MAN);
        assertEquals(eKlasse.SENIOR, team.getKlasse());
        team.setKlasse(eKlasse.JUNIOR);
        assertEquals(eKlasse.JUNIOR, team.getKlasse());
    }

    @Test
    void getGeslacht() {
        Team team = new Team("test team", eKlasse.SENIOR, eGeslacht.MAN);
        assertEquals(eGeslacht.MAN, team.getGeslacht());
        team.setGeslacht(eGeslacht.VROUW);
        assertEquals(eGeslacht.VROUW, team.getGeslacht());
    }
}