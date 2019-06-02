package crackingthecodinginterview.moderate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SmallestDifferenceTest {
  private static SmallestDifference smallestDifference;

  @BeforeAll
  static void setUpOnce() {
    smallestDifference = new SmallestDifference();
  }

  @Test
  void computeSmallestDifferencePair_2EmptyList() {
  }

  @Test
  void computeSmallestDifferencePair_1EmptyList1NonEmptyList() {
  }

  @Test
  void computeSmallestDifferencePair_SameSizeList() {
    // 6 elements
    List<Integer> listA = new ArrayList<>();
    listA.add(224);
    listA.add(68);
    listA.add(23);
    listA.add(84);
    listA.add(119);
    listA.add(148);

    List<Integer> listB = new ArrayList<>();
    listB.add(69);
    listB.add(12);
    listB.add(188);
    listB.add(30);
    listB.add(131);
    listB.add(187);
    listB.add(250);
    listB.add(131);

    SmallestDifference.Pair smallestPair = new SmallestDifference.Pair(69, 68);
    System.out.println(smallestDifference.computeSmallestDifferencePair(listA, listB));
    assertEquals(smallestPair, smallestDifference.computeSmallestDifferencePair(listA, listB),
        "Not the same + " + smallestDifference.computeSmallestDifferencePair(listA, listB).toString());
  }

  @Test
  void computeSmallestDifferencePair_DifferentSizeList() {

  }

  @Test
  void computeSmallestDifferencePair_1ListAllSmallerThanBigList() {

  }

  @Test
  void computeSmallestDifferencePair_IdenticalList() {

  }
}