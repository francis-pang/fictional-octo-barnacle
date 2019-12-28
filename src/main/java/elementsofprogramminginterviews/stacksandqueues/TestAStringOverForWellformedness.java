package elementsofprogramminginterviews.stacksandqueues;

public class TestAStringOverForWellformedness {
  public boolean isWellForm(String s) {
    int curlyCount = 0;
    int roundCount = 0;
    int squareCount = 0;
    for (int i = 0; i < s.length(); i++) {
      char op = s.charAt(i);
      switch (op) {
        case '{':
          curlyCount++;
          break;
        case '}':
          curlyCount--;
          if (curlyCount < 0) {
            return false;
          }
          break;
        case '(':
          roundCount++;
          break;
        case ')':
          roundCount--;
          if (roundCount < 0) {
            return false;
          }
          break;
        case '[':
          squareCount++;
          break;
        case ']':
          squareCount--;
          if (squareCount < 0) {
            return false;
          }
          break;
      }
    }
    return (curlyCount == 0) && (squareCount == 0) && (roundCount == 0);
  }
}
