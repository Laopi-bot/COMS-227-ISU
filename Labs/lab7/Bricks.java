package lab7;

public class Bricks {

	
	public static void main (String args[]) {
		System.out.println(countPatterns(5));	
	}
	
	public static int countPatterns(int n) {
		//base case
		if(n == 0) {
			return 1;
		}
		if(n < 0) {
			return 0;
		}
		//recursion
		int ways = 0;
		ways += countPatterns(n - 1);
		ways += countPatterns(n - 3);
		return ways;
	}
}
