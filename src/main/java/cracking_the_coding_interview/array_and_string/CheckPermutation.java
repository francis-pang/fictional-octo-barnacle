package cracking_the_coding_interview.array_and_string;

import java.util.Hashtable;

/**
 * Check Permutation: Given two strings, write a method to decide if one is a permutation of the other.
 * @author franc
 *
 */
public class CheckPermutation {
	public boolean isStringPermutable (String stringA, String stringB){
		if (stringA == null || stringB == null) {
			return false;
		}		
		
		// Quick win - length check
		if(stringA.length() != stringB.length()){
			return false;
		}

		// Quick win - exact match
		if(stringA.equals(stringB)){
			return true;
		}

		Hashtable<Character,Integer> stringHashtable = insertToHashtable(stringA);
		return isStringInsideStringHashtable(stringB, stringHashtable);
	}

	private Hashtable<Character,Integer> insertToHashtable(String string){
		Hashtable<Character,Integer> stringHashtable = new Hashtable<Character,Integer>();

		for(int i = 0; i < string.length(); i++) {
			char strChar = string.charAt(i);
			if(stringHashtable.containsKey(strChar)){
				int currentValue = stringHashtable.get(strChar);
				stringHashtable.put(strChar,
					++currentValue);
			}
			else {
				stringHashtable.put(strChar, 1);
			}
		}
		return stringHashtable;
	}

	private boolean isStringInsideStringHashtable(String string, Hashtable<Character,Integer> stringHashtable){
		for(int i = 0; i < string.length(); i++) {
			char strChar = string.charAt(i);
			if(stringHashtable.containsKey(strChar) &&
			   stringHashtable.get(strChar) > 0){
				int currentValue = stringHashtable.get(strChar);
				stringHashtable.put(strChar,
					--currentValue);
			}
			else {
				return false;
			}
		}
		return true;
	}
}