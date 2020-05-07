package interview.triplebyte;

public class Driver {
  public static void main(String[] args) {
    Spreadsheet test = Spreadsheet.createInstance(4, 3);
    test.updateCell(1, 1, "bob");
    test.updateCell(1, 2, "10");
    test.updateCell(1, 3, "foo");
    test.updateCell(2, 1, "alice");

    test.updateCell(2, 1, "a");

    test.updateCell(2, 2, "5");
    test.prettyPrint();
  }
}
