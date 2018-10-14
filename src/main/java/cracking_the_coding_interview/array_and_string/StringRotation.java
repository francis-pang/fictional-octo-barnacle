package cracking_the_coding_interview.array_and_string;

public class StringRotation {
	public boolean isStringRotationOfAnother(String stringA, String stringB) {
		if (stringA.length() != stringB.length()) {
			return false;
		}
		if (stringA.equals(stringB)) {
			return true;
		}
		String concatStringA = stringA + stringA;
		return (concatStringA.contains(stringB)) ? true : false; //contains is the equivalence of isSubString in String API
	}
}
