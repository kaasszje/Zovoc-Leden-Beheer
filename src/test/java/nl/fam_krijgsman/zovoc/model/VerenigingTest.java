package nl.fam_krijgsman.zovoc.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VerenigingTest {
    Lid lid;
    Team team;
    Vereniging vereniging;

    @BeforeEach
    void init() {
        lid = new Lid("Krijgsman", "Martijn", "", "", "", 1981, eGeslacht.MAN);
        team = new Team("Test team", eKlasse.PROMOTIE, eGeslacht.MAN);
        vereniging = new Vereniging("test");
    }

    @Test
    @DisplayName("Zoek toegevoegd lid")
    void findLidFound() {
        vereniging.addLid(lid);
        assertEquals(lid, vereniging.findLid(lid.getAchterNaam(), lid.getVoorNaam()));
    }

    @Test
    @DisplayName("Zoek niet bestaand lid")
    void FindLidNotFound() {
        //assertNull(vereniging.findLid("ik besta", "niet"));
    }

    @Test
    @DisplayName("Voeg lid toe wat al bestaat")
    void addLid() {
        //voeg lid toe die al vastaat moet niet kunnen
        vereniging.addLid(lid);
        //2e keer zelf lid toevoegen
        assertFalse(vereniging.addLid(lid));
    }

    @Test
    @DisplayName("Verwijder niet bestaand lid")
    void removeLidNotExisting() {
        Assertions.assertAll(
                () -> assertEquals(0, vereniging.getLeden().size()),
                () -> assertFalse(vereniging.removeLid(lid))
        );
    }

    @Test
    @DisplayName("Verwijder bestaand lid")
    void removeLidExisting() {
        Assertions.assertAll(
                // nieuwe verening zonder leden
                () -> assertEquals(0, vereniging.getLeden().size()),
                // voeg lid toe
                () -> assertTrue(vereniging.addLid(lid)),
                // moet nu 1 lid zijn
                () -> assertEquals(1, vereniging.getLeden().size()),
                // verwijder lid
                () -> assertTrue(vereniging.removeLid(lid)),
                // nu weer 0 leden
                () -> assertEquals(0, vereniging.getLeden().size())
        );
    }

    @Test
    @DisplayName("Zoek niet bestaand team")
    void findTeamNotExisting() {
        assertNull(vereniging.findTeam("ik besta lekker niet"));
    }

    @Test
    @DisplayName("Zoek bestaand team")
    void findTeamExisting() {
        vereniging.addTeam(team);
        assertEquals(team, vereniging.findTeam(team.getNaam()));
    }

    @Test
    @DisplayName("Team toevoegen")
    void addTeamExisting() {
        vereniging.addTeam(team);
        assertFalse(vereniging.addTeam(team));
    }

    @Test
    @DisplayName("Nieuw team toevoegen")
    void addTeamNew() {
        assertTrue(vereniging.addTeam(team));
    }

    @Test
    @DisplayName("Verwijderd niet bestaand team")
    void removeTeamNotExisting() {
        assertFalse(vereniging.removeTeam(team));
    }

    @Test
    @DisplayName("Verwijderd bestaand team")
    void removeTeamExisting() {
        Assertions.assertAll(
                // Nieuwe vereniging dus 0 teams
                () -> assertEquals(0, vereniging.getTeams().size()),
                // Voeg 1 team toe
                () -> assertTrue(vereniging.addTeam(team)),
                // Nu 1 team
                () -> assertEquals(1, vereniging.getTeams().size()),
                // Verwijder team
                () -> assertTrue(vereniging.removeTeam(team)),
                // Nu weer 0 teams
                () -> assertEquals(0, vereniging.getTeams().size())
        );
    }

    @Test
    @DisplayName("Aantal leden")
    void aantalLeden() {
        // voeg 10 teams toe
        for (int i = 0; i < 10; i++) {
            lid = new Lid("nummer-" + i, "naam-" + i, "", "", "", 1981, eGeslacht.MAN);
            vereniging.addLid(lid);
        }
        assertEquals(10, vereniging.aantalLeden());
    }


    @Test
    @DisplayName("Valideer naam")
    void getNaam() {
        assertEquals("test", vereniging.getNaam());
    }

    @Test
    @DisplayName("Valideer leden")
    void getLeden() {
        for (int i = 0; i < 10; i++) {
            lid = new Lid("nummer-" + i, "naam-" + i, "", "", "", 1981, eGeslacht.MAN);
            vereniging.addLid(lid);
        }
        assertEquals(10, vereniging.getLeden().size());
    }

    @Test
    @DisplayName("Valideer teams")
    void getTeams() {
        for (int i = 0; i < 10; i++) {
            team = new Team("team" + i, eKlasse.SENIOR, eGeslacht.VROUW);
            vereniging.addTeam(team);
        }
        assertEquals(10, vereniging.getTeams().size());
    }
}