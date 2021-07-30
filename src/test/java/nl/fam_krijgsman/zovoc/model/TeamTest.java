package nl.fam_krijgsman.zovoc.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TeamTest {
    Team team;

    @BeforeEach
    void init() {
        this.team = new Team("test team", eKlasse.SENIOR, eGeslacht.MAN);
    }

    @Test
    @DisplayName("Valideer constructor")
    void team(){
        Assertions.assertAll(
                () -> assertEquals("test team", this.team.getNaam()),
                () -> assertEquals(eKlasse.SENIOR, this.team.getKlasse()),
                () -> assertEquals(eGeslacht.MAN, this.team.getGeslacht())
        );
    }

    @Test
    @DisplayName("Valideer teamnaam")
    void getNaam() {
        this.team.setNaam("test2");
        assertEquals("test2", this.team.getNaam());
    }

    @Test
    @DisplayName("Valideer fout bij lege teamnaam")
    void setNaamEmpty() {
        assertThrows(IllegalArgumentException.class, () -> this.team.setNaam(""));
    }

    @Test
    @DisplayName("Valideer fout bij null waarde teamnaam")
    void setNaamNull() {
        assertThrows(IllegalArgumentException.class, () -> this.team.setNaam(null));
    }

    @Test
    @DisplayName("Valideer klasse")
    void getKlasse() {
        team.setKlasse(eKlasse.JUNIOR);
        assertEquals(eKlasse.JUNIOR, this.team.getKlasse());
    }

    @Test
    @DisplayName("Valideer geslacht")
    void getGeslacht() {
        team.setGeslacht(eGeslacht.VROUW);
        assertEquals(eGeslacht.VROUW, this.team.getGeslacht());
    }
}