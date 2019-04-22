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

    private List convertNumberToList(int number) {
        LinkedList numberList = new LinkedList();
        while(number > 0){
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
        List numberList = convertNumberToList(11);
        List actualSummationList = sumLists.addTwoNumbersRepresentByLinkedLists(numberList, numberList);
        Assertions.assertEquals(22, convertNumberListToNumber(actualSummationList));
    }

    @Test
    void addSingleDigitNumber() {
        List number1List = convertNumberToList(617);
        List number2List = convertNumberToList(2);

        List actualSummationList = sumLists.addTwoNumbersRepresentByLinkedLists(number1List, number2List);
        Assertions.assertEquals(619, convertNumberListToNumber(actualSummationList));
    }

    @Test
    void addOverSpillingNumberAtThirdDigit() {
        List number1List = convertNumberToList(8999);
        List number2List = convertNumberToList(200);

        List actualSummationList = sumLists.addTwoNumbersRepresentByLinkedLists(number1List, number2List);
        Assertions.assertEquals(9199, convertNumberListToNumber(actualSummationList));
    }

    @Test
    void addOverSpillingNumberAtFirstDigit() {
        List number1List = convertNumberToList(9999);
        List number2List = convertNumberToList(2);

        List actualSummationList = sumLists.addTwoNumbersRepresentByLinkedLists(number1List, number2List);
        Assertions.assertEquals(10001, convertNumberListToNumber(actualSummationList));
    }

    @Test
    void addTwoNumberFromProvidedExamples() {
        List number1List = convertNumberToList(617);
        List number2List = convertNumberToList(295);

        List actualSummationList = sumLists.addTwoNumbersRepresentByLinkedLists(number1List, number2List);
        Assertions.assertEquals(912, convertNumberListToNumber(actualSummationList));
    }
}