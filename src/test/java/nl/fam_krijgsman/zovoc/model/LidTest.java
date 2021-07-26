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
        assertThrows(IllegalArgumentException.class, () -> lid.setTeam(team));
    }

    @Test
    @DisplayName("Junior mag in junior team")
    void setTeam_Junior_Junior() {
        lid.setGeboorteJaar(2010);
        Team team = new Team("jongens", eKlasse.JUNIOR, eGeslacht.MAN);
    }

    @Test
    @DisplayName("Mannen mogen niet in dames team")
    void setTeam_Woman_Male() {
        Team team = new Team("ladies", eKlasse.SENIOR, eGeslacht.VROUW);
        assertThrows(IllegalArgumentException.class, () -> lid.setTeam(team));
    }

    @Test
    @DisplayName("Dame mag in mix team")
    void setTeam_Woman_MIX() {
        Team team = new Team("jongens", eKlasse.SENIOR, eGeslacht.MIX);
        //assertTrue(lid.setTeam(team));
    }

    @Test
    @DisplayName("Valideer of juiste team terug komt naar zetten")
    void setTeam_getTeam() {
        Team team = new Team("jongens", eKlasse.SENIOR, eGeslacht.MIX);
        lid.setTeam(team);
        assertEquals(team, lid.getTeam());
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
    @DisplayName("Zet lege achternaam")
    void setAchterNaamEmpty() {
        assertThrows(IllegalArgumentException.class, () -> lid.setAchterNaam(""));
    }

    @Test
    @DisplayName("Valideer voornaam")
    void getVoorNaam() {
        lid.setVoorNaam("test");
        assertEquals("test", lid.getVoorNaam());
    }

    @Test
    @DisplayName("Zet lege voornaam")
    void setVoorNaamEmpty() {
        assertThrows(IllegalArgumentException.class, () -> lid.setVoorNaam(""));
    }

    @Test
    @DisplayName("Valideer tussengvoegsel")
    void getTussenVoegsel() {
        lid.setTussenVoegsel("test");
        assertEquals("test", lid.getTussenVoegsel());
    }

    @Test
    @DisplayName("Valideer get telefoonnummer")
    void getTelefoonNummer() {
        String validTelefoonNummer = "0612345678";
        lid.setTelefoonNummer(validTelefoonNummer);
        assertEquals("0612345678", lid.getTelefoonNummer());
    }

    @Test
    @DisplayName("Valideer set met valide telefoonnummer")
    void setTelefoonNummerValid() {
        String validTelefoonNummer = "06-12345678";
        lid.setTelefoonNummer(validTelefoonNummer);
    }

    @Test
    @DisplayName("Valideer set met onvalide telefoonnummer")
    void setTelefoonNummerInValid() {
        String invalidTelefoonNummer = "06-123-4567";
        assertThrows(IllegalArgumentException.class, () -> lid.setTelefoonNummer(invalidTelefoonNummer));
    }

    @Test
    @DisplayName("Valideer set met null waarde voor telefoonnummer")
    void setTelefoonNummerNull() {
        lid.setTelefoonNummer(null);
    }

    @Test
    @DisplayName("Valideer get email-adres")
    void getEmail() {
        String validEmail = "test@example.com"; 
        lid.setEmail(validEmail);
        assertEquals("test@example.com", lid.getEmail());
    }

    @Test
    @DisplayName("Valideer set met valide email")
    void setEmailValid() {
        String validEmail = "test@example.com";
        lid.setEmail(validEmail);
    }

    @Test
    @DisplayName("Valideer set met onvalide email")
    void setEmailInValid() {
        String invalidEmail = "a.b@c";
        assertThrows(IllegalArgumentException.class, () -> lid.setEmail(invalidEmail));
    }

    @Test
    @DisplayName("Valideer set met null waarde voor email")
    void setEmailNull() {
        lid.setEmail(null);
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