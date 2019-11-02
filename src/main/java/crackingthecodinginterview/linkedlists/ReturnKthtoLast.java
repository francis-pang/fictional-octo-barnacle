package crackingthecodinginterview.linkedlists;

import java.util.Objects;
import java.util.StringJoiner;

public class ReturnKthtoLast {
  // This problem with this we do not know the ending of the linked list. If we know the end index, or the size of
  // the linked list, we can just iterating through the linked list to that particular (size/end - k) index.
  // The native solution is simply to get the size of the linked list through one iteration of the linked list. Then
  // I traverse to the (size - k)th index. This will solve my problem. However, this will mean that I need to
  // traverse through the linked list twice separately. So the strict time complexity is O(2n) at worse, if k = 1. I
  // can solve this limitation by introducing a 2nd pointer. I will let the 1st pointer to run through k element
  // before the 2nd pointer starts. So the 2nd pointer is always k steps behind the 1st pointer. When the 1st pointer
  // reaches the end of the linked list, the 2nd pointer will reach the (size - k)th index. And I will just return
  // that item. This will reduce the time complexity to O(n).
  public Node getKToLastElement(Node root, int k) {
    Node frontPointer = root;

    // Run the 1st pointer for k time so that the 2nd pointer will be k steps behind
    for (int i = 0; i < k; i++) {
      if (frontPointer != null) {
        frontPointer = frontPointer.next;
      } else {
        return null; // Return null if the size linked list is less than k
      }
    }

    Node backPointer = root;
    while (frontPointer != null) {
      frontPointer = frontPointer.next;
      backPointer = backPointer.next;
    }
    return backPointer;
  }

  public static class Node {
    public int value;
    public Node next;

    public Node(int value) {
      this.value = value;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Node)) return false;
      Node node = (Node) o;
      return value == node.value &&
          Objects.equals(next, node.next);
    }

    @Override
    public int hashCode() {
      return Objects.hash(value, next);
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
          .add("value=" + value)
          .toString();
    }
  }
}
