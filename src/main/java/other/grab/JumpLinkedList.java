package other.grab;

public class JumpLinkedList {
  public int solution(int[] A) {
    int currentPostion = 0;
    final int BREAKING_VALUE = -1;
    int length = 1;
    while (A[currentPostion] != BREAKING_VALUE) {
      length++;
      currentPostion = A[currentPostion];
    }
    return length;
  }
}
