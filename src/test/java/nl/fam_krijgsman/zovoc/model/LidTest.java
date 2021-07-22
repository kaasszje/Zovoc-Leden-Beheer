package nl.fam_krijgsman.zovoc.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LidTest {
    Lid lid;

    @BeforeEach
    void init() {
        lid = new Lid("Krijgsman", "Martijn", "van", "0612312345", "jan@example.com", 1981, eGeslacht.MAN);
    }

    @Test
    @DisplayName("Valideer constructor")
    void getConstructor() {
        Assertions.assertAll(
                () -> assertEquals("Krijgsman", lid.getAchterNaam()),
                () -> assertEquals("Martijn", lid.getVoorNaam()),
                () -> assertEquals("van", lid.getTussenVoegsel()),
                () -> assertEquals("0612312345", lid.getTelefoonNummer()),
                () -> assertEquals("jan@example.com", lid.getEmail()),
                () -> assertEquals(1981, lid.getGeboorteJaar()),
                () -> assertEquals(eGeslacht.MAN, lid.getGeslacht())
        );
    }

    @Test
    @DisplayName("Volwassene mag niet in junior team")
    void setTeam_Adult_Junior() {
        // Volwassenen mag niet in junioren team
        Team team = new Team("jongens", eKlasse.JUNIOR, eGeslacht.MAN);
        assertFalse(lid.setTeam(team));
    }

    @Test
    @DisplayName("Junior mag in junior team")
    void setTeam_Junior_Junior() {
        lid.setGeboorteJaar(2010);
        Team team = new Team("jongens", eKlasse.JUNIOR, eGeslacht.MAN);
        assertTrue(lid.setTeam(team));
    }

    @Test
    @DisplayName("Mannen mogen niet in dames team")
    void setTeam_Woman_Male() {
        Team team = new Team("ladies", eKlasse.SENIOR, eGeslacht.VROUW);
        assertFalse(lid.setTeam(team));
    }

    @Test
    @DisplayName("Dame mag in mix team")
    void setTeam_Woman_MIX() {
        Team team = new Team("jongens", eKlasse.SENIOR, eGeslacht.MIX);
        assertTrue(lid.setTeam(team));
    }

    @Test
    @DisplayName("Valideer of juiste team terug komt naar zetten")
    void setTeam_getTeam() {
        Team team = new Team("jongens", eKlasse.SENIOR, eGeslacht.MIX);
        Assertions.assertAll(
                () -> assertTrue(lid.setTeam(team)),
                () -> assertEquals(team, lid.getTeam())
        );
    }

    @Test
    @DisplayName("Valideer of je leeg team krijgt")
    void setTeam_nullTeam() {
        lid.setTeam(null);
        assertNull(lid.getTeam());
    }


    @Test
    @DisplayName("Valideer achternaam")
    void getAchterNaam() {
        lid.setAchterNaam("test");
        assertEquals("test", lid.getAchterNaam());
    }

    @Test
    @DisplayName("Valideer voornaam")
    void getVoorNaam() {
        lid.setVoorNaam("test");
        assertEquals("test", lid.getVoorNaam());
    }

    @Test
    @DisplayName("Valideer tussengvoegsel")
    void getTussenVoegsel() {
        lid.setTussenVoegsel("test");
        assertEquals("test", lid.getTussenVoegsel());
    }

    @Test
    @DisplayName("Valideer telefoonnummer")
    void getTelefoonNummer() {
        lid.setTelefoonNummer("0626454220");
        assertEquals("0626454220", lid.getTelefoonNummer());
    }

    @Test
    @DisplayName("Valideer email-adres")
    void getEmail() {
        lid.setEmail("test@example.com");
        assertEquals("test@example.com", lid.getEmail());
    }

    @Test
    @DisplayName("Valideer team")
    void getTeam() {
        Team team = new Team("testteam", eKlasse.SENIOR, eGeslacht.VROUW );
        lid.setGeslacht(eGeslacht.VROUW);
        lid.setTeam(team);
        assertEquals(team, lid.getTeam());
    }

    @Test
    @DisplayName("Valideer geboortejaar")
    void getGeboorteJaar() {
        lid.setGeboorteJaar(1903);
        assertEquals(1903, lid.getGeboorteJaar());
    }

    @Test
    @DisplayName("Valideer geslacht")
    void getGeslacht() {
        lid.setGeslacht(eGeslacht.VROUW);
        assertEquals(eGeslacht.VROUW, lid.getGeslacht());
    }
}