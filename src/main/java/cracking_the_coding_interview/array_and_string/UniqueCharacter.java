package cracking_the_coding_interview.array_and_string;

// Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional data structures?
public class UniqueCharacter {
    public static boolean isUnique(String str){
        int[] characters = new int[127];

        for(char singleChar: str.toCharArray()){
            int charAscii = (int) singleChar;

            if(characters[charAscii] != 0 ){
                return false;
            }
            else {
                characters[charAscii]++;
            }
        }
        return true;
    }
}
