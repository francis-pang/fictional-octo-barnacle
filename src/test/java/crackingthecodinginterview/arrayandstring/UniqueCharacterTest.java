package crackingthecodinginterview.arrayandstring;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UniqueCharacterTest {

    @Test
    void testUniqueSimpleString() {
        assertEquals(true, UniqueCharacter.isUnique("GTYHBN"));
    }

    @Test
    void testNonUniqueSimpleString() {
        assertEquals(false, UniqueCharacter.isUnique("GTYBBN"));
    }
}