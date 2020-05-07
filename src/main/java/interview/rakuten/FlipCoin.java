package interview.rakuten;

public class FlipCoin {
  private final int HEAD = 0;
  private final int TAIL = 1;

  public int solution(int[] coins) {
    // Start with head
    int startWithHeadCurrentSide = HEAD;
    int numberOfFlipIfStartWithHead = 0;
    // Start with tail
    int startWithTailCurrentSide = TAIL;
    int numberOfFlipIfStartWithTail = 0;

    for (int i = 0; i < coins.length; i++) {
      int coin = coins[i];
      if (coin != startWithHeadCurrentSide) {
        numberOfFlipIfStartWithHead++;
      }
      startWithHeadCurrentSide = flip(startWithHeadCurrentSide);

      if (coin != startWithTailCurrentSide) {
        numberOfFlipIfStartWithTail++;
      }
      startWithTailCurrentSide = flip(startWithHeadCurrentSide);
    }
    return Math.min(numberOfFlipIfStartWithHead, numberOfFlipIfStartWithTail);
  }

  private int flip(int side) {
    return (side == HEAD) ? TAIL : HEAD;
  }
}