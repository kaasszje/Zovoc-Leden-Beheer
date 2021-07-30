package nl.fam_krijgsman.zovoc.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class LidTest {
    Lid lid;

    @BeforeEach
    void init() {
        this.lid = new Lid("Krijgsman", "Martijn", "van", "0612312345", "jan@example.com", 1981, eGeslacht.MAN);
    }

    @Test
    @DisplayName("Valideer constructor")
    void getConstructor() {
        Assertions.assertAll(
                () -> assertEquals("Krijgsman", this.lid.getAchterNaam()),
                () -> assertEquals("Martijn", this.lid.getVoorNaam()),
                () -> assertEquals("van", this.lid.getTussenVoegsel()),
                () -> assertEquals("0612312345", this.lid.getTelefoonNummer()),
                () -> assertEquals("jan@example.com", this.lid.getEmail()),
                () -> assertEquals(1981, this.lid.getGeboorteJaar()),
                () -> assertEquals(eGeslacht.MAN, this.lid.getGeslacht())
        );
    }

    @Test
    @DisplayName("Volwassene mag niet in junior team")
    void setTeam_Adult_Junior() {
        // Volwassenen mag niet in junioren team
        Team team = new Team("jongens", eKlasse.JUNIOR, eGeslacht.MAN);
        assertThrows(IllegalArgumentException.class, () -> this.lid.setTeam(team));
    }

    @Test
    @DisplayName("Junior mag in junior team")
    void setTeam_Junior_Junior() {
        lid.setGeboorteJaar(2010);
        Team team = new Team("jongens", eKlasse.JUNIOR, eGeslacht.MAN);
        assertDoesNotThrow(() -> this.lid.setTeam(team));
    }

    @Test
    @DisplayName("Mannen mogen niet in dames team")
    void setTeam_Woman_Male() {
        Team team = new Team("ladies", eKlasse.SENIOR, eGeslacht.VROUW);
        assertThrows(IllegalArgumentException.class, () -> this.lid.setTeam(team));
    }

    @Test
    @DisplayName("Dame mag in mix team")
    void setTeam_Woman_MIX() {
        Team team = new Team("jongens", eKlasse.SENIOR, eGeslacht.MIX);
        assertDoesNotThrow(() -> this.lid.setTeam(team));
    }

    @Test
    @DisplayName("Valideer of juiste team terug komt naar zetten")
    void setTeam_getTeam() {
        Team team = new Team("jongens", eKlasse.SENIOR, eGeslacht.MIX);
        this.lid.setTeam(team);
        assertEquals(team, this.lid.getTeam());
    }

    @Test
    @DisplayName("Valideer of je leeg team krijgt")
    void setTeam_nullTeam() {
        this.lid.setTeam(null);
        assertNull(this.lid.getTeam());
    }


    @Test
    @DisplayName("Valideer achternaam")
    void getAchterNaam() {
        this.lid.setAchterNaam("test");
        assertEquals("test", this.lid.getAchterNaam());
    }

    @Test
    @DisplayName("Zet lege achternaam")
    void setAchterNaamEmpty() {
        assertThrows(IllegalArgumentException.class, () -> this.lid.setAchterNaam(""));
    }

    @Test
    @DisplayName("Zet null waarde achternaam")
    void setAchterNaamNull() {
        assertThrows(IllegalArgumentException.class, () -> this.lid.setAchterNaam(null));
    }

    @Test
    @DisplayName("Valideer voornaam")
    void getVoorNaam() {
        this.lid.setVoorNaam("test");
        assertEquals("test", lid.getVoorNaam());
    }

    @Test
    @DisplayName("Zet lege voornaam")
    void setVoorNaamEmpty() {
        assertThrows(IllegalArgumentException.class, () -> this.lid.setVoorNaam(""));
    }

    @Test
    @DisplayName("Zet null waarde voornaam")
    void setVoorNaamNull() {
        assertThrows(IllegalArgumentException.class, () -> this.lid.setVoorNaam(null));
    }

    @Test
    @DisplayName("Valideer tussengvoegsel")
    void getTussenVoegsel() {
        this.lid.setTussenVoegsel("test");
        assertEquals("test", this.lid.getTussenVoegsel());
    }

    @Test
    @DisplayName("Valideer get telefoonnummer")
    void getTelefoonNummer() {
        String validTelefoonNummer = "0612345678";
        this.lid.setTelefoonNummer(validTelefoonNummer);
        assertEquals("0612345678", this.lid.getTelefoonNummer());
    }

    @Test
    @DisplayName("Valideer set met valide telefoonnummer")
    void setTelefoonNummerValid() {
        String validTelefoonNummer = "06-12345678";
        assertDoesNotThrow(() -> this.lid.setTelefoonNummer(validTelefoonNummer));
    }

    @Test
    @DisplayName("Valideer set met onvalide telefoonnummer")
    void setTelefoonNummerInValid() {
        String invalidTelefoonNummer = "06-123-4567";
        assertThrows(IllegalArgumentException.class, () -> this.lid.setTelefoonNummer(invalidTelefoonNummer));
    }

    @Test
    @DisplayName("Valideer set met null waarde voor telefoonnummer")
    void setTelefoonNummerNull() {
        assertDoesNotThrow(() -> this.lid.setTelefoonNummer(null));
    }

    @Test
    @DisplayName("Valideer get email-adres")
    void getEmail() {
        String validEmail = "test@example.com";
        this.lid.setEmail(validEmail);
        assertEquals("test@example.com", this.lid.getEmail());
    }

    @Test
    @DisplayName("Valideer set met valide email")
    void setEmailValid() {
        String validEmail = "test@example.com";
        assertDoesNotThrow(() -> this.lid.setEmail(validEmail));
    }

    @Test
    @DisplayName("Valideer set met onvalide email")
    void setEmailInValid() {
        String invalidEmail = "a.b@c";
        assertThrows(IllegalArgumentException.class, () -> this.lid.setEmail(invalidEmail));
    }

    @Test
    @DisplayName("Valideer set met null waarde voor email")
    void setEmailNull() {
        assertDoesNotThrow(() -> this.lid.setEmail(null));
    }

    @Test
    @DisplayName("Valideer team")
    void getTeam() {
        Team team = new Team("testteam", eKlasse.SENIOR, eGeslacht.VROUW);
        this.lid.setGeslacht(eGeslacht.VROUW);
        this.lid.setTeam(team);
        assertEquals(team, this.lid.getTeam());
    }

    @Test
    @DisplayName("Valideer geboortejaar")
    void getGeboorteJaar() {
        this.lid.setGeboorteJaar(1903);
        assertEquals(1903, this.lid.getGeboorteJaar());
    }

    @Test
    @DisplayName("Probeer volgend jaar toe te voegen als geboorte jaar")
    void setGeboorteJaarToekomst() {
        int volgendJaar = Year.now().getValue() + 1;
        assertThrows(IllegalArgumentException.class, () -> this.lid.setGeboorteJaar(volgendJaar));
    }

    @Test
    @DisplayName("Valideer geslacht")
    void getGeslacht() {
        this.lid.setGeslacht(eGeslacht.VROUW);
        assertEquals(eGeslacht.VROUW, this.lid.getGeslacht());
    }

    @Test
    @DisplayName("Probeer invalide geslacht toe te kennen.")
    void setGeslachtMix() {
        assertThrows(IllegalArgumentException.class, () -> this.lid.setGeslacht(eGeslacht.MIX));
    }
}