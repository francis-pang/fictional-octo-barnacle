package crackingthecodinginterview.bitmanipulation;

public class BinaryToString {
	public String binaryToString(double number){
		if (number < 0 ||
			number >= 1){
			return "ERROR";
		}
		final int MAXIMUM_CHARACTERS = 32;
		StringBuilder binaryRepresentation = new StringBuilder("0.");
		int i = -1;
		do {
			if(number - Math.pow(2, i) >= 0){
				binaryRepresentation.append('1');
				number -= Math.pow(2, i);
			}
			else {
				binaryRepresentation.append('0');
			}
			i--;
		} while(number != 0 && binaryRepresentation.length() < MAXIMUM_CHARACTERS);
		return (number == 0 ? binaryRepresentation.toString() : "ERROR");
	}
}
