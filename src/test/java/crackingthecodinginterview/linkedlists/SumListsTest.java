package crackingthecodinginterview.linkedlists;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

class SumListsTest {
  private static SumLists sumLists;

  @BeforeAll
  static void setUpOnce() {
    sumLists = new SumLists();
  }

  private List<Integer> convertNumberToList(int number) {
    LinkedList<Integer> numberList = new LinkedList<>();
    while (number > 0) {
      numberList.add(number % 10);
      number /= 10;
    }
    return numberList;
  }

  private int convertNumberListToNumber(List<Integer> numberList) {
    int number = 0;
    for (int i = 0; i < numberList.size(); i++) {
      number += numberList.get(i) * 10 * i;
    }
    return number;
  }

  @Test
  void addTwoSimpleNumber() {
    //11 + 11 = 22
    List<Integer> numberList = convertNumberToList(11);
    List<Integer> actualSummationList = sumLists.addTwoNumbersRepresentByLinkedLists(numberList, numberList);
    Assertions.assertEquals(22, convertNumberListToNumber(actualSummationList));
  }

  @Test
  void addSingleDigitNumber() {
    List<Integer> number1List = convertNumberToList(617);
    List<Integer> number2List = convertNumberToList(2);

    List<Integer> actualSummationList = sumLists.addTwoNumbersRepresentByLinkedLists(number1List, number2List);
    Assertions.assertEquals(619, convertNumberListToNumber(actualSummationList));
  }

  @Test
  void addOverSpillingNumberAtThirdDigit() {
    List<Integer> number1List = convertNumberToList(8999);
    List<Integer> number2List = convertNumberToList(200);

    List<Integer> actualSummationList = sumLists.addTwoNumbersRepresentByLinkedLists(number1List, number2List);
    Assertions.assertEquals(9199, convertNumberListToNumber(actualSummationList));
  }

  @Test
  void addOverSpillingNumberAtFirstDigit() {
    List<Integer> number1List = convertNumberToList(9999);
    List<Integer> number2List = convertNumberToList(2);

    List<Integer> actualSummationList = sumLists.addTwoNumbersRepresentByLinkedLists(number1List, number2List);
    Assertions.assertEquals(10001, convertNumberListToNumber(actualSummationList));
  }

  @Test
  void addTwoNumberFromProvidedExamples() {
    List<Integer> number1List = convertNumberToList(617);
    List<Integer> number2List = convertNumberToList(295);

    List<Integer> actualSummationList = sumLists.addTwoNumbersRepresentByLinkedLists(number1List, number2List);
    Assertions.assertEquals(912, convertNumberListToNumber(actualSummationList));
  }
}