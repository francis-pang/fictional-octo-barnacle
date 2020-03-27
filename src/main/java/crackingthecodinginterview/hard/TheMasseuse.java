package crackingthecodinginterview.hard;

public class TheMasseuse {
  public int findMaxTime(int[] array) {
    if (array == null || array.length == 0) {
      return -1;
    }
    if (array.length == 1) {
      return array[0];
    }
    int arrayBound = array.length - 1;
    int skipOneCacheMax = array[arrayBound];
    int immediateNextCacheMax = Math.max(array[arrayBound - 1], array[arrayBound]);
    for (int i = arrayBound - 2; i >= 0; i--) {
      int timeWithCurrentSlotTaken = array[i] + skipOneCacheMax;
      skipOneCacheMax = immediateNextCacheMax;
      immediateNextCacheMax = Math.max(timeWithCurrentSlotTaken, immediateNextCacheMax);
    }
    return immediateNextCacheMax;
  }

  public static void main(String[] args) {
    TheMasseuse theMasseuse = new TheMasseuse();
    int answer = theMasseuse.findMaxTime(new int[]{30, 15, 60, 75, 45, 15, 15, 45});
    System.out.println(answer);
  }
}
