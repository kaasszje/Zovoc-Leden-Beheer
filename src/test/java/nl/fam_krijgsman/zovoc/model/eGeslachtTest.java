package nl.fam_krijgsman.zovoc.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(eGeslacht.MAN, eGeslacht.valueOf("MAN"));
    }
}