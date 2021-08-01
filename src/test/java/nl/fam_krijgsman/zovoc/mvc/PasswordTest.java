package nl.fam_krijgsman.zovoc.mvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordTest {
    Password password;
    final String veiligWachtwoord = "NFV3fqeiBR5#T%HW";

    @BeforeEach
    void init() {
    password = new Password(veiligWachtwoord);
    }

    @Test
    @DisplayName("Correct wachtwoord")
    void isValidPasswordCorrect() {
        assertTrue(password.isValidPassword(veiligWachtwoord));
    }

    @Test
    @DisplayName("Foutief wachtwoord")
    void isValidPasswordBad() {
        assertFalse(password.isValidPassword("test"));
    }
}