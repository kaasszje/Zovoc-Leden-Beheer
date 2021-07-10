package nl.fam_krijgsman.zovoctester.model.classes;

import nl.fam_krijgsman.zovoc.model.classes.Adres;
import nl.fam_krijgsman.zovoc.model.classes.Lid;
import nl.fam_krijgsman.zovoc.model.classes.Team;
import nl.fam_krijgsman.zovoc.model.enums.eGeslacht;
import nl.fam_krijgsman.zovoc.model.enums.eKlasse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LidTest {
    static Lid lid;

    @Test
    void setTeam() {
        lid = new Lid("Krijgsman", "Martijn", "", "", "", null, 1981, eGeslacht.MAN);
        // Volwassenen mag niet in junioren team
        Team team = new Team("jongens", eKlasse.JUNIOR, eGeslacht.MAN);
        assertFalse(lid.setTeam(team));

        // Junior mag in junioren team
        lid.setGeboorteJaar(2010);
        assertTrue(lid.setTeam(team));

        // Meisjes mogen niet in mannen team
        lid.setGeslacht(eGeslacht.VROUW);
        assertFalse(lid.setTeam(team));

        team = new Team("Mix", eKlasse.SENIOR, eGeslacht.MIX);
        // iedereen mag in een mix team
        assertTrue(lid.setTeam(team));

        lid.setGeboorteJaar(2000);
        assertTrue(lid.setTeam(team));

        lid.setGeslacht(eGeslacht.MAN);
        assertTrue(lid.setTeam(team));
    }

    @Test
    void getAchterNaam() {
        lid = new Lid("Krijgsman", "Martijn", "", "", "", null, 1981, eGeslacht.MAN);
        lid.setAchterNaam("test");
        assertEquals("test", lid.getAchterNaam());
    }

    @Test
    void getVoorNaam() {
        lid = new Lid("Krijgsman", "Martijn", "", "", "", null, 1981, eGeslacht.MAN);
        lid.setVoorNaam("test");
        assertEquals("test", lid.getVoorNaam());
    }

    @Test
    void getTussenVoegsel() {
        lid = new Lid("Krijgsman", "Martijn", "", "", "", null, 1981, eGeslacht.MAN);
        lid.setTussenVoegsel("test");
        assertEquals("test", lid.getTussenVoegsel());
    }

    @Test
    void getTelefoonNummer() {
        lid = new Lid("Krijgsman", "Martijn", "", "", "", null, 1981, eGeslacht.MAN);
        lid.setTelefoonNummer("0626454220");
        assertEquals("0626454220", lid.getTelefoonNummer());
    }

    @Test
    void getEmail() {
        lid = new Lid("Krijgsman", "Martijn", "", "", "", null, 1981, eGeslacht.MAN);
        lid.setEmail("test@example.com");
        assertEquals("test@example.com", lid.getEmail());
    }

    @Test
    void getAdres() {
        lid = new Lid("Krijgsman", "Martijn", "", "", "", null, 1981, eGeslacht.MAN);
        lid.setAdres(new Adres("teststraat", 2, "0000XX", "Dorpstest"));
        assertAll(
                () -> assertEquals("teststraat", lid.getAdres().getStraatNaam()),
                () -> assertEquals(2, lid.getAdres().getHuisNummer()),
                () -> assertEquals("0000XX", lid.getAdres().getPostCode()),
                () -> assertEquals("Dorpstest", lid.getAdres().getPlaats())
        );
    }

    @Test
    void getTeam() {
        lid = new Lid("Krijgsman", "Martijn", "", "", "", null, 1981, eGeslacht.MAN);
        Team team = new Team("testteam", eKlasse.SENIOR, eGeslacht.VROUW );
        lid.setGeslacht(eGeslacht.VROUW);
        lid.setTeam(team);
        assertEquals(team, lid.getTeam());
    }

    @Test
    void getGeboorteJaar() {
        lid = new Lid("Krijgsman", "Martijn", "", "", "", null, 1981, eGeslacht.MAN);
        lid.setGeboorteJaar(1981);
        assertEquals(1981, lid.getGeboorteJaar());
    }

    @Test
    void getGeslacht() {
        lid = new Lid("Krijgsman", "Martijn", "", "", "", null, 1981, eGeslacht.MAN);
        lid.setGeslacht(eGeslacht.VROUW);
        assertEquals(eGeslacht.VROUW, lid.getGeslacht());
    }
}