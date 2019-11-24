package crackingthecodinginterview.treesandgraphs;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BstSequencesTest {

  private BstSequences bstSequences = new BstSequences();

  @Test
  void allSeqBst_null() {
    List<int[]> actual = bstSequences.computeAllBstSequences(null);
    assertEquals(0, actual.size());
  }

  @Test
  void allSeqBst_singleNode() {
    BstSequences.Node root = new BstSequences.Node();
    root.value = 3;
    List<int[]> actual = bstSequences.computeAllBstSequences(root);
    List<int[]> expected = new ArrayList<>();
    int[] answer = {3};
    expected.add(answer);
    expected.forEach(element -> assertTrue(
        contains(element, expected),
        "Expected element" + element + " is not inside the actual list")
    );
  }

  @Test
  void allSeqBst_singleWithOnlyLeftChild() {
    BstSequences.Node node2 = new BstSequences.Node();
    node2.value = 2;

    BstSequences.Node node1 = new BstSequences.Node();
    node1.value = 1;
    node2.left = node1;

    List<int[]> actual = bstSequences.computeAllBstSequences(node2);
    List<int[]> expected = new ArrayList<>();
    int[] answer = {2, 1};
    expected.add(answer);
    expected.forEach(element -> assertTrue(
        contains(element, actual),
        "Expected element" + element + " is not inside the actual list")
    );
  }

  @Test
  void allSeqBst_singleWithOnlyRightChild() {
    BstSequences.Node node2 = new BstSequences.Node();
    node2.value = 2;

    BstSequences.Node node3 = new BstSequences.Node();
    node3.value = 3;
    node2.right = node3;

    List<int[]> actual = bstSequences.computeAllBstSequences(node2);
    List<int[]> expected = new ArrayList<>();
    int[] answer = {2, 3};
    expected.add(answer);
    expected.forEach(element -> assertTrue(
        contains(element, actual),
        "Expected element" + element + " is not inside the actual list")
    );
  }

  @Test
  void allSeqBst_2Levels() {
    BstSequences.Node node2 = new BstSequences.Node();
    node2.value = 2;

    BstSequences.Node node3 = new BstSequences.Node();
    node3.value = 3;
    node2.right = node3;

    BstSequences.Node node1 = new BstSequences.Node();
    node1.value = 1;
    node2.left = node1;

    List<int[]> actual = bstSequences.computeAllBstSequences(node2);
    List<int[]> expected = new ArrayList<>();
    int[] answer = {2, 3, 1};
    expected.add(answer);
    answer = new int[]{2, 1, 3};
    expected.add(answer);
    assertEquals(expected.size(), actual.size());
    expected.forEach(element -> assertTrue(
        contains(element, actual),
        "Expected element" + element + " is not inside the actual list")
    );
  }

  private boolean contains(int[] array, List<int[]> arrayList) {
    for (int[] iteratingArray : arrayList) {
      if (array.length != iteratingArray.length) {
        continue;
      }
      for (int i = 0; i < array.length; i++) {
        if (array[i] != iteratingArray[i]) {
          continue;
        }
      }
      return true;
    }
    return false;
  }

  @Test
  void allSeqBst_3Levels() {
    BstSequences.Node node4 = new BstSequences.Node();
    node4.value = 4;

    BstSequences.Node node2 = new BstSequences.Node();
    node2.value = 2;
    node4.left = node2;

    BstSequences.Node node6 = new BstSequences.Node();
    node6.value = 6;
    node4.right = node6;

    BstSequences.Node node1 = new BstSequences.Node();
    node1.value = 1;
    node2.left = node1;

    BstSequences.Node node3 = new BstSequences.Node();
    node3.value = 3;
    node2.right = node3;

    BstSequences.Node node5 = new BstSequences.Node();
    node5.value = 5;
    node6.left = node5;

    BstSequences.Node node7 = new BstSequences.Node();
    node7.value = 7;
    node6.right = node7;

    List<int[]> actual = bstSequences.computeAllBstSequences(node4);
    List<int[]> expected = new ArrayList<>();
    expected.add(new int[]{4, 2, 1, 3, 6, 5, 7});
    expected.add(new int[]{4, 2, 1, 3, 6, 7, 5});
    expected.add(new int[]{4, 2, 3, 1, 6, 5, 7});
    expected.add(new int[]{4, 2, 3, 1, 6, 7, 5});
    expected.add(new int[]{4, 6, 5, 7, 2, 1, 3});
    expected.add(new int[]{4, 6, 5, 7, 2, 3, 1});
    expected.add(new int[]{4, 6, 7, 5, 2, 1, 3});
    expected.add(new int[]{4, 6, 7, 5, 2, 3, 1});
    assertEquals(expected.size(), actual.size());
    expected.forEach(element -> assertTrue(
        contains(element, actual),
        "Expected element" + element + " is not inside the actual list")
    );
  }

  @Test
  void allSeqBst_3LevelsLoopSided() {
  }
}