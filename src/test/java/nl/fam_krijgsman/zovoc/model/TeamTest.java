package nl.fam_krijgsman.zovoc.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {
    Team team;

    @BeforeEach
    void init() {
        team = new Team("test team", eKlasse.SENIOR, eGeslacht.MAN);
    }

    @Test
    @DisplayName("Valideer constructor")
    void team(){
        Assertions.assertAll(
                () -> assertEquals("test team", team.getNaam()),
                () -> assertEquals(eKlasse.SENIOR, team.getKlasse()),
                () -> assertEquals(eGeslacht.MAN, team.getGeslacht())
        );
    }

    @Test
    @DisplayName("Valideer teamnaam")
    void getNaam() {
        team.setNaam("test2");
        assertEquals("test2", team.getNaam());
    }

    @Test
    @DisplayName("Valideer klasse")
    void getKlasse() {
        team.setKlasse(eKlasse.JUNIOR);
        assertEquals(eKlasse.JUNIOR, team.getKlasse());
    }

    @Test
    @DisplayName("Valideer geslacht")
    void getGeslacht() {
        team.setGeslacht(eGeslacht.VROUW);
        assertEquals(eGeslacht.VROUW, team.getGeslacht());
    }
}