package lab5;

public class Initials {

	private static String initials(String s) {
		String initial = "";
		for(int i = 0; i < s.length(); i++) {
			if(i == 0) {
				initial += s.charAt(i);
			}
			if(s.charAt(i) == ' ') {
				initial += s.charAt(++i);
			}
		}
		return initial;
	}

	private static int areThereVowels(String s) {
		int vowelIndex = -1;
		
		for(int i = 0; i < s.length(); i++) {
			if("aeiouAEIOU".indexOf(s.charAt(i)) >= 0) {
				vowelIndex = i;
				break;
			}
		}
		return vowelIndex;
	}
	
	public static void main(String args[]) {
		
		String balls = "sAdfg";
		System.out.println(areThereVowels(balls));
		
	}
}