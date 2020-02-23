package crackingthecodinginterview.linkedlists;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static crackingthecodinginterview.linkedlists.SumLists.ListNode;

class SumListsTest {
  private static SumLists sumLists;

  @BeforeAll
  static void setUpOnce() {
    sumLists = new SumLists();
  }

  @Test
  void addTwoSimpleNumber() {
    //11 + 11 = 22
    ListNode listNode1 = sumLists.convertNumberToLinkedList(11);
    ListNode listNode2 = sumLists.convertNumberToLinkedList(11);
    ListNode actualSummationList = sumLists.addTwoNumbers(listNode1, listNode2);
  }

  @Test
  void addSingleDigitNumber() {
    ListNode listNode1 = sumLists.convertNumberToLinkedList(617);
    ListNode listNode2 = sumLists.convertNumberToLinkedList(2);
    ListNode actualSummationList = sumLists.addTwoNumbers(listNode1, listNode2);
  }

  @Test
  void addOverSpillingNumberAtThirdDigit() {
    ListNode listNode1 = sumLists.convertNumberToLinkedList(8999);
    ListNode listNode2 = sumLists.convertNumberToLinkedList(200);
    ListNode actualSummationList = sumLists.addTwoNumbers(listNode1, listNode2);
  }

  @Test
  void addOverSpillingNumberAtFirstDigit() {
    ListNode listNode1 = sumLists.convertNumberToLinkedList(9999);
    ListNode listNode2 = sumLists.convertNumberToLinkedList(2);
    ListNode actualSummationList = sumLists.addTwoNumbers(listNode1, listNode2);
  }

  @Test
  void addTwoNumberFromProvidedExamples() {
    ListNode listNode1 = sumLists.convertNumberToLinkedList(617);
    ListNode listNode2 = sumLists.convertNumberToLinkedList(295);
    ListNode actualSummationList = sumLists.addTwoNumbers(listNode1, listNode2);
  }
}