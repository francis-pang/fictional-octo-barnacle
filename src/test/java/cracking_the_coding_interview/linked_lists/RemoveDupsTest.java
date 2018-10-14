package cracking_the_coding_interview.linked_lists;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class RemoveDupsTest {
	private static RemoveDups removeDups;

	@BeforeAll
	static void SetUpOnce() {
		removeDups = new RemoveDups();
	}

	@Test
	void removeEmptyList() {
		List testLinkedList = new LinkedList();
		assertEquals(testLinkedList, removeDups.removeDuplicate(testLinkedList));
		assertEquals(testLinkedList, removeDups.removeDuplicateNoBuffer(testLinkedList));
	}

	@Test
	void removeSingleItemList() {
		List<Integer> testLinkedList = new LinkedList<Integer>();
		testLinkedList.add(5);
		assertEquals(testLinkedList, removeDups.removeDuplicate(testLinkedList));
		assertEquals(testLinkedList, removeDups.removeDuplicateNoBuffer(testLinkedList));
	}

	@Test
	void remove2OutOf5SortedList() {
		List<Integer> testLinkedList = new LinkedList<Integer>();
		testLinkedList.add(1);
		testLinkedList.add(2);
		testLinkedList.add(2);
		testLinkedList.add(3);
		testLinkedList.add(3);

		List<Integer> bufferedTestList = testLinkedList.stream().collect(Collectors.toList());

		List<Integer> expectedLinkedList = new LinkedList<Integer>();
		expectedLinkedList.add(1);
		expectedLinkedList.add(2);
		expectedLinkedList.add(3);

		assertEquals(expectedLinkedList, removeDups.removeDuplicate(testLinkedList));

		assertEquals(expectedLinkedList, removeDups.removeDuplicateNoBuffer(bufferedTestList));
	}

	@Test
	void remove3DupUnsortedList() {
		List<Integer> testLinkedList = new LinkedList<Integer>();
		testLinkedList.add(5);
		testLinkedList.add(6);
		testLinkedList.add(5);
		testLinkedList.add(7);
		testLinkedList.add(4);
		testLinkedList.add(6);
		testLinkedList.add(3);
		testLinkedList.add(3);
		testLinkedList.add(1);

		List<Integer> bufferedTestList = testLinkedList.stream().collect(Collectors.toList());

		List<Integer> expectedLinkedList = new LinkedList<Integer>();
		expectedLinkedList.add(5);
		expectedLinkedList.add(6);
		expectedLinkedList.add(7);
		expectedLinkedList.add(4);
		expectedLinkedList.add(3);
		expectedLinkedList.add(1);

		assertEquals(expectedLinkedList, removeDups.removeDuplicate(testLinkedList));
		assertEquals(expectedLinkedList, removeDups.removeDuplicateNoBuffer(bufferedTestList));
	}
}
