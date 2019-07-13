package crackingthecodinginterview.moderate;

public class Rand7FromRand5 {
  public double rand7() {
    return rand5() / 4.0 * 6.0;
  }

  private double rand5() {
    return Math.random() * 4;
  }
}
