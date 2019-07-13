package crackingthecodinginterview.linkedlists;

import java.util.LinkedList;
import java.util.List;

public class Partition {
  public List partitionListBasedOnValue(List<Integer> linkedList, int value) {
    LinkedList<Integer> resultLinkedList = new LinkedList<>();
    for (Object element : linkedList) {
      int elementValue = (int) element;
      if (elementValue >= value) {
        resultLinkedList.addLast((Integer) element);
      } else {
        resultLinkedList.addFirst((Integer) element);
      }
    }
    return resultLinkedList;
  }
}
