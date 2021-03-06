package nl.fam_krijgsman.zovoc.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class eKlasseTest {

    @Test
    void values() {
        assertEquals(4, eKlasse.values().length);
    }

    @Test
    void valueOf() {
        assertEquals(eKlasse.JUNIOR, eKlasse.valueOf("JUNIOR"));
    }


    @Test
    void testToString() {
        assertEquals("Junior", eKlasse.JUNIOR.toString());
    }
}