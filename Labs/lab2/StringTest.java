package lab2;

public class StringTest {
	public static void main(String args[]) {
		//first block: calculates the length of the string, 'message'
		String message;
		message = "Hello, world!";
		int theLength = message.length();
		System.out.println(theLength);
		
		//second block: gets char at index 0
		char theChar = message.charAt(0);
		System.out.println(theChar);
		//third block: gets char at index 1
		theChar = message.charAt(1);
		System.out.println(theChar);
		
		//fourth block: prints message in all upper case
		System.out.println(message.toUpperCase());
		System.out.println(message.substring(0,5));
		
	}
}	
