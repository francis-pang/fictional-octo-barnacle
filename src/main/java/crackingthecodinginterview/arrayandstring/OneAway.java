package crackingthecodinginterview.arrayandstring;

public class OneAway {
	public boolean isOneAway(String first, String second) {
		if (first.equals(second)) {
			return true;
		}
		int differenceInLength = first.length() - second.length();
		switch (differenceInLength) {
			case -1:
				return isOneInsertionAway(second, first);
			case 0:
				boolean foundDifference = false;
				for (int i = 0; i < first.length(); i++) {
					if (first.charAt(i) != second.charAt(i)) {
						if (foundDifference) {
							return false;
						} else {
							foundDifference = true;
						}
					}
				}
				return true;
			case 1:
				return isOneInsertionAway(first, second);
			default:
				return false;
		}
	}

	private boolean isOneInsertionAway( String longerString, String shorterString) {
		boolean insertOnce = false;
		for (int i = 0; i < shorterString.length(); i++) {
			if (insertOnce) {
				if (shorterString.charAt(i) != longerString.charAt(i + 1)) {
					return false;
				}
			} else if (shorterString.charAt(i) != longerString.charAt(i)) {
				insertOnce = true;
				if (shorterString.charAt(i) != longerString.charAt(i + 1)) {
					return false;
				}
			}
		}
		return true;
	}
}
