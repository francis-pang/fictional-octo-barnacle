package leetcode;

public class IntegerToEnglishWords {
  public static void main(String[] args) {
    IntegerToEnglishWords englishInt = new IntegerToEnglishWords();
    System.out.println(englishInt.numberToWords(123));
  }

  public String numberToWords(int num) {
    String nStr = Integer.toString(num);
    char[] nArray = nStr.toCharArray();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < nArray.length; i++) {
      int digitP = nArray.length - i;
      char digit = nArray[i];
      int digitValue = Integer.parseInt(Character.toString(digit));
      if (digitP % 3 == 0) {
        sb.append(convertToWord(digitValue) + " Hundred");
      }
      if (digitP % 3 == 1) {
        sb.append(convertToWord(digit));
      }
      if (digitP % 3 == 2) {
        if (digit == 1) {
          char nextDigit = nArray[i + 1];
          int value = digitValue * 10 + Integer.valueOf(nextDigit);
          sb.append(convertToWord(value));
          i++;
          digitP = nArray.length - i;
        } else {
          sb.append(convertToWord(digitValue * 10));
        }
      }
      switch (digitP) {
        case 4:
          sb.append(" Thousand ");
          continue;
        case 7:
          sb.append(" Million ");
          continue;
      }
    }
    return sb.toString();
  }

  private String convertToWord(int digit) {
    switch (digit) {
      case 1:
        return "One";
      case 2:
        return "Two";
      case 3:
        return "Three";
      case 4:
        return "Four";
      case 5:
        return "Five";
      case 6:
        return "Six";
      case 7:
        return "Seven";
      case 8:
        return "Eight";
      case 9:
        return "Nine";
      case 10:
        return "Ten";
      case 11:
        return "Eleven";
      case 12:
        return "Twleve";
      case 13:
        return "Thirteen";
      case 14:
        return "Forteen";
      case 15:
        return "Fifteen";
      case 16:
        return "Sixteen";
      case 17:
        return "Seventeen";
      case 18:
        return "Eighteen";
      case 19:
        return "Ninteen";
      case 20:
        return "Twenty";
      case 30:
        return "Thirty";
      case 40:
        return "Forty";
      case 50:
        return "Fifty";
      case 60:
        return "Sixty";
      case 70:
        return "Seventy";
      case 80:
        return "Eighty";
      case 90:
        return "Ninety";
      default:
        return "";
    }
  }
}