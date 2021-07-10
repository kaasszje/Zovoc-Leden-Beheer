package nl.fam_krijgsman.zovoctester.model.enums;

import nl.fam_krijgsman.zovoc.model.enums.eGeslacht;
import nl.fam_krijgsman.zovoc.model.enums.eKlasse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class eGeslachtTest {

    @Test
    void testToString() {
        assertEquals("mix", eGeslacht.MIX.toString());
    }

    @Test
    void values() {
        assertEquals(3, eGeslacht.values().length);
    }

    @Test
    void valueOf() {

    }
}