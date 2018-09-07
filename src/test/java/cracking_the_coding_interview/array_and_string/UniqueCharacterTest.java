package cracking_the_coding_interview.array_and_string;

import static org.junit.jupiter.api.Assertions.*;

import cracking_the_coding_interview.array_and_string.UniqueCharacter;
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