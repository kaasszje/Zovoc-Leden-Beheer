package nl.fam_krijgsman.zovoc.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {
    Email email;

    @BeforeEach
    void init() {
        this.email = new Email("user@domain.com");
    }

    @Test
    @DisplayName("Valideer gezet email adres")
    void getEmail() {
        assertEquals("user@domain.com", this.email.getEmail());
    }

    @Test
    @DisplayName("Valideer correcte setEmail")
    void setEmailValide() {
        String validEmail = "user.name@domain.com";
        assertDoesNotThrow(() -> this.email.setEmail(validEmail));
    }

    @Test
    @DisplayName("Valideer incorrecte setEmail")
    void setEmailInValid() {
        String invalidEmail = ".username@yahoo.com";
        assertThrows(IllegalArgumentException.class, () -> this.email.setEmail(invalidEmail));
    }

    @ParameterizedTest
    @DisplayName("Geldige email adressen")
    @ValueSource(strings = {"user@domain.com","user@domain.co.in", "user.name@domain.com","user_name@domain.com","username@yahoo.corporate.in" })
    void isValideEmailCorrect(String emailAdres) {
        assertTrue(Email.isValideEmail(emailAdres));
    }

    @ParameterizedTest
    @DisplayName("Ongeldige email adressen")
    @ValueSource(strings = {".username@yahoo.com","username@yahoo.com.","username@yahoo..com", "username@yahoo.c","username@yahoo.corporate", "" })
    void isValideEmailNotCorrect(String emailAdres) {
        assertFalse(Email.isValideEmail(emailAdres));
    }
}