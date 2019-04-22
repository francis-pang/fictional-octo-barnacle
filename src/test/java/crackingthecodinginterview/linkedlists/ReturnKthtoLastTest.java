package crackingthecodinginterview.linkedlists;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class ReturnKthtoLastTest {
    private static ReturnKthtoLast returnKthtoLast;

    @BeforeAll
    static void setUpOnce() {
        returnKthtoLast = new ReturnKthtoLast();
    }

    @Test
    void testNormalCase() {
        List<Integer> testList = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            testList.add(i);
        }
        assertEquals(Integer.valueOf(7), returnKthtoLast.getKToLastElement(testList, 3));
    }

    @Test
    void testFirstIsLastKCase() {
        List<Integer> testList = new ArrayList<>();
        for (int i = 4; i > 0; i-- ) {
            testList.add(i);
        }
        assertEquals(Integer.valueOf(4), returnKthtoLast.getKToLastElement(testList, 4));
    }

    @Test
    void testLastCase() {
        List<Integer> testList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            testList.add(i * 6);
        }
        assertEquals(Integer.valueOf(24), returnKthtoLast.getKToLastElement(testList, 0));
    }
}