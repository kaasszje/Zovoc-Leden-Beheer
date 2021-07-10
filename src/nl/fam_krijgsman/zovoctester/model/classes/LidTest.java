package nl.fam_krijgsman.zovoctester.model.classes;

import nl.fam_krijgsman.zovoc.model.classes.Lid;
import nl.fam_krijgsman.zovoc.model.classes.Team;
import nl.fam_krijgsman.zovoc.model.enums.eGeslacht;
import nl.fam_krijgsman.zovoc.model.enums.eKlasse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LidTest {
    static Lid lid = new Lid("Krijgsman", "Martijn", "", "", "", null, 1981, eGeslacht.MAN);

    @Test
    void setTeam() {
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
}