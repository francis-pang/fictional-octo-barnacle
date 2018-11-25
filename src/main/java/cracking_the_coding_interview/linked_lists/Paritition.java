package cracking_the_coding_interview.linked_lists;

import java.util.LinkedList;
import java.util.List;

public class Paritition {
    public List partitionListBasedOnValue(List linkedList, int value) {
        LinkedList resultLinkedList = new LinkedList();
        for (Object element : linkedList) {
            int elementValue = (int) element;
            if (elementValue >= value) {
                resultLinkedList.addLast(element);
            } else {
                resultLinkedList.addFirst(element);
            }
        }
        return resultLinkedList;
    }
}
