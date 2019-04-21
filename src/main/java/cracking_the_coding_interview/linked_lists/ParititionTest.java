package cracking_the_coding_interview.linked_lists;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ParititionTest {
    private static Paritition paritition;

    @BeforeAll
    static void setUpOnce() {
        paritition = new Paritition();
    }

    @Test
    void testExampleCase() {
        // Create testing linked list
        Integer[] testNumbers = {3, 5, 8, 5, 10 , 2 ,1};
        List<Integer> testList = Arrays.asList(testNumbers);
        // Create expected linked list
        Integer[] expectedNumbers = {1, 2, 3, 5, 8, 5, 10};
        List<Integer> expectedList = Arrays.asList(expectedNumbers);
        List<Integer> actualList = paritition.partitionListBasedOnValue(testList, 5);
        assertArrayEquals(expectedList.toArray(), actualList.toArray());
    }

    @Test
    void testSimpleCase() {
        // Create testing linked list
        Integer[] testNumbers = {6, 8, 3};
        List<Integer> testList = Arrays.asList(testNumbers);
        // Create expected linked list
        Integer[] expectedNumbers = {3, 6, 8};
        List<Integer> expectedList = Arrays.asList(expectedNumbers);
        List<Integer> actualList = paritition.partitionListBasedOnValue(testList, 5);
        assertArrayEquals(expectedList.toArray(), actualList.toArray());
    }

    @Test
    void testSameInputOutputCase() {
        // Create testing linked list
        Integer[] testNumbers = {1, 2, 3};
        List<Integer> testList = Arrays.asList(testNumbers);
        // Create expected linked list
        Integer[] expectedNumbers = {1, 2, 3};
        List<Integer> expectedList = Arrays.asList(expectedNumbers);
        List<Integer> actualList = paritition.partitionListBasedOnValue(testList, 2);
        assertArrayEquals(expectedList.toArray(), actualList.toArray());
    }
}