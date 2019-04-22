package crackingthecodinginterview.linkedlists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SumLists {
  public List<Integer> addTwoNumbersRepresentByLinkedLists(List<Integer> numberOne, List<Integer> numberTwo) {
    Iterator<Integer> numberOneIterator = numberOne.iterator();
    Iterator<Integer> numberTwoIterator = numberTwo.iterator();
    List<Integer> sumList = new ArrayList<>();
    while (numberOneIterator.hasNext() && numberOneIterator.hasNext()) {
      Integer digitOne = numberOneIterator.next();
      Integer digitTwo = numberTwoIterator.next();
      sumList.add(digitOne + digitTwo);
    }
    while (numberOneIterator.hasNext()) {
      Integer digitOne = numberOneIterator.next();
      sumList.add(digitOne);
    }
    while (numberTwoIterator.hasNext()) {
      Integer digitTwo = numberTwoIterator.next();
      sumList.add(digitTwo);
    }
    return sumList;
  }
}
