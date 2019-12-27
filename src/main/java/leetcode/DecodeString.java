package leetcode;

public class DecodeString {
    public String decodeString(String string) {
        int currentPosition = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (currentPosition < string.length()) {
            char c = string.charAt(currentPosition);
            if (!Character.isDigit(c)) {
                stringBuilder.append(c);
                currentPosition++;
            } else {
                Next next = new Next(currentPosition);
                stringBuilder.append(resolveEncoding(string, currentPosition, next));
                currentPosition = next.value + 1;
            }
        }
        return stringBuilder.toString();
    }

    private String resolveEncoding(String string, int currentPosition, Next next) {
        char digit = string.charAt(currentPosition);
        int multiplier = 0;
        do {
            int num = Character.getNumericValue(digit);
            if (multiplier == 0) {
                multiplier = num;
            } else {
                multiplier *= 10;
                multiplier += num;
            }
            currentPosition++;
            digit = string.charAt(currentPosition);
        } while (Character.isDigit(digit));

        StringBuilder sb = new StringBuilder();
        currentPosition++;
        char c = string.charAt(currentPosition);
        do {
            if (Character.isDigit(c)) {
                sb.append(resolveEncoding(string, currentPosition, next));
                currentPosition = next.value + 1;
            } else {
                sb.append(c);
                currentPosition++;
            }
            if (currentPosition == string.length()) {
                c = ']';
            } else {
                c = string.charAt(currentPosition);
            }
        } while(c != ']');

        String single = sb.toString();
        for (int i = 1; i < multiplier; i++) {
            sb.append(single);
        }
        next.value = currentPosition;
        return sb.toString();
    }

    public class Next {
        public int value;

        public Next(int v) {
            value = v;
        }
    }
}
