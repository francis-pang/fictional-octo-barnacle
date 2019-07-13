package crackingthecodinginterview.moderate;

/**
 * Number Swapper: Write a function to swap a number in place (that is, without temporary variables).
 */
public class NumberSwapper {
  public void swap(int number1, int number2){
    // This answer ignore overflow issue
    number1 += number2;
    number2 -= number1;
  }
}
