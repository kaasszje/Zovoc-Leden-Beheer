package nl.fam_krijgsman.zovoc.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerenigingTest {
    Lid lid;
    Team team;
    Vereniging vereniging;

    @BeforeEach
    void init() {
        this.lid = new Lid("Krijgsman", "Martijn", "", "0612345678", "user@example.com", 1981, eGeslacht.MAN);
        this.team = new Team("Test team", eKlasse.PROMOTIE, eGeslacht.MAN);
        this.vereniging = new Vereniging("test");
    }

    @Test
    @DisplayName("Zoek toegevoegd lid")
    void findLidFound() {
        this.vereniging.addLid(lid);
        assertEquals(lid, this.vereniging.findLid(this.lid.getAchterNaam(), this.lid.getVoorNaam()));
    }

    @Test
    @DisplayName("Zoek niet bestaand lid")
    void FindLidNotFound() {
        assertNull(this.vereniging.findLid("ik besta", "niet"));
    }

    @Test
    @DisplayName("Voeg lid toe wat al bestaat")
    void addLid() {
        //voeg lid toe die al vastaat moet niet kunnen
        this.vereniging.addLid(this.lid);
        //2e keer zelf lid toevoegen
        assertFalse(this.vereniging.addLid(this.lid));
    }

    @Test
    @DisplayName("Verwijder niet bestaand lid")
    void removeLidNotExisting() {
        Assertions.assertAll(
                () -> assertEquals(0, this.vereniging.getLeden().size()),
                () -> assertFalse(this.vereniging.removeLid(lid))
        );
    }

    @Test
    @DisplayName("Verwijder bestaand lid")
    void removeLidExisting() {
        Assertions.assertAll(
                // nieuwe verening zonder leden
                () -> assertEquals(0, this.vereniging.getLeden().size()),
                // voeg lid toe
                () -> assertTrue(this.vereniging.addLid(this.lid)),
                // moet nu 1 lid zijn
                () -> assertEquals(1, this.vereniging.getLeden().size()),
                // verwijder lid
                () -> assertTrue(this.vereniging.removeLid(this.lid)),
                // nu weer 0 leden
                () -> assertEquals(0, this.vereniging.getLeden().size())
        );
    }

    @Test
    @DisplayName("Zoek niet bestaand team")
    void findTeamNotExisting() {
        assertNull(this.vereniging.findTeam("ik besta lekker niet"));
    }

    @Test
    @DisplayName("Zoek bestaand team")
    void findTeamExisting() {
        this.vereniging.addTeam(this.team);
        assertEquals(this.team, this.vereniging.findTeam(this.team.getNaam()));
    }

    @Test
    @DisplayName("Team toevoegen")
    void addTeamExisting() {
        this.vereniging.addTeam(this.team);
        assertFalse(this.vereniging.addTeam(this.team));
    }

    @Test
    @DisplayName("Nieuw team toevoegen")
    void addTeamNew() {
        assertTrue(this.vereniging.addTeam(this.team));
    }

    @Test
    @DisplayName("Verwijderd niet bestaand team")
    void removeTeamNotExisting() {
        assertFalse(this.vereniging.removeTeam(this.team));
    }

    @Test
    @DisplayName("Verwijderd bestaand team")
    void removeTeamExisting() {
        Assertions.assertAll(
                // Nieuwe vereniging dus 0 teams
                () -> assertEquals(0, this.vereniging.getTeams().size()),
                // Voeg 1 team toe
                () -> assertTrue(this.vereniging.addTeam(this.team)),
                // Nu 1 team
                () -> assertEquals(1, this.vereniging.getTeams().size()),
                // Verwijder team
                () -> assertTrue(this.vereniging.removeTeam(this.team)),
                // Nu weer 0 teams
                () -> assertEquals(0, this.vereniging.getTeams().size())
        );
    }

    @Test
    @DisplayName("Aantal leden")
    void aantalLeden() {
        // voeg 10 teams toe
        for (int i = 0; i < 10; i++) {
            this.lid = new Lid("nummer-" + i, "naam-" + i, "", "0612345678", "user@example.com", 1981, eGeslacht.MAN);
            this.vereniging.addLid(lid);
        }
        assertEquals(10, this.vereniging.aantalLeden());
    }


    @Test
    @DisplayName("Valideer naam")
    void getNaam() {
        assertEquals("test", this.vereniging.getNaam());
    }

    @Test
    @DisplayName("Valideer leden")
    void getLeden() {
        for (int i = 0; i < 10; i++) {
            this.lid = new Lid("nummer-" + i, "naam-" + i, "", "0612345678", "user@example.com", 1981, eGeslacht.MAN);
            this.vereniging.addLid(lid);
        }
        assertEquals(10, this.vereniging.getLeden().size());
    }

    @Test
    @DisplayName("Valideer teams")
    void getTeams() {
        for (int i = 0; i < 10; i++) {
            this.team = new Team("team" + i, eKlasse.SENIOR, eGeslacht.VROUW);
            this.vereniging.addTeam(this.team);
        }
        assertEquals(10, this.vereniging.getTeams().size());
    }
}