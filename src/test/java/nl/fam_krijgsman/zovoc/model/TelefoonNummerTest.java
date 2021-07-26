package nl.fam_krijgsman.zovoc.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class TelefoonNummerTest {
    TelefoonNummer telefoonNummer;

    @BeforeEach
    void init() {
        telefoonNummer = new TelefoonNummer("0791234567");
    }


    @Test
    void getTelefoonNummer() {
        assertEquals("0791234567", telefoonNummer.getTelefoonNummer());
    }

    @Test
    void setTelefoonNummerValid() {
        String validTelefoonNummer = "0612345678";
        assertDoesNotThrow(() -> telefoonNummer.setTelefoonNummer(validTelefoonNummer));
    }

    @Test
    @DisplayName("Zet invalide telefoonnummer")
    void setTelefoonNummerInValid() {
        String invalidTelefoonNummer = "1234567";
        assertThrows(IllegalArgumentException.class, () -> telefoonNummer.setTelefoonNummer(invalidTelefoonNummer));
    }

    @ParameterizedTest
    @DisplayName("Valide nationale telefoonnummers")
    @ValueSource(strings = {"0101234567", "010-1234567", "010 - 123 45 67", "010 1234 567", "06-12345678", "06 123 456 78", "0111-123456", "0111 123456"})
    void isValideTelefoonNummerNational(String nummer) {
        assertTrue(TelefoonNummer.isValideTelefoonNummer(nummer));
    }

    @ParameterizedTest
    @DisplayName("Valide internationale telefoonnummers")
    @ValueSource(strings = {"+31101234567", "0031101234567", "+31(0) 10123 4567", "+3110-1234567", "003110 1234567", "+316 123 456 78", "+31(0)6 123 45678", "+31111-123456", "0031111-123456"})
    void isValideTelefoonNummerInternational(String nummer) {
        assertTrue(TelefoonNummer.isValideTelefoonNummer(nummer));
    }

    @ParameterizedTest
    @DisplayName("Foute telefoonnummers")
    @ValueSource(strings = {"06-1234-5678", //An extra dash is not allowed
            "06 123456789", //To long
            "06 1234567", //To short
            "+31(06) 123 45678", //Invalid optional declaration
            "1234567", //(without regional number)
            "112", //(emergency number)"
            "0900-1234", //(paid service numbers)
            "0906-12345", //(paid service numbers)"
            "0800-5555",
            ""})
        //Leeg telefoonnummer
    void isNotValideTelefoonNummer(String nummer) {
        assertFalse(TelefoonNummer.isValideTelefoonNummer(nummer));
    }
}
