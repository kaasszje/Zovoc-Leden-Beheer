package nl.fam_krijgsman.zovoc.model;

import nl.fam_krijgsman.zovoc.model.eGeslacht;
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