package mini;

import java.util.ArrayList;

/**
 * Utility class with static methods for loop practice.
 */
public class LoopsInfinityAndBeyond {

	/**
	 * Private constructor to disable instantiation.
	 */
	private LoopsInfinityAndBeyond() {
	}

	/**
	 * Define a flying saucer as the following string pattern: one ‘(‘, followed by
	 * zero to many ‘=’, followed by one ‘)’. Write a Java method that, given a
	 * string find the first instance of a flying saucer (starting from the left)
	 * and return its length. If no flying saucer exists return 0.
	 * <p>
	 * For example: Given: "(==)" Return: 4
	 * <p>
	 * Given: "***()**(===)" Return: 2
	 * <p>
	 * Given: "****(***)" Return: 0
	 * 
	 * @param source input string
	 * @return the length
	 */
	public static int flyingSaucerLength(String s) {
		int count = 0;
		int start = -1;
		if (s == null) {
			return 0;
		}
		for (int i = 0; i < s.length(); i++) {
			if ((s.charAt(i) == '(') && ((s.charAt(i + 1) == '=') || (s.charAt(i + 1) == ')'))) {
				count = 0;
				start = i;
			} else if (s.charAt(i) == ')') {

				if (count > 0) {
					return i - start + 1;
				}
				if (i != 0) {
					if (s.charAt(i - 1) == '(') {
						return 2;
					}
				}
			} else if (s.charAt(i) == '=') {
				count++;
				if (s.charAt(i + 1) != '=') {
					if (s.charAt(i + 1) != ')' && (i + 1) != (s.length() - 1)) {

						start = 0;
						count = 0;
					}
				}
			}
		}
		return 0;
	}

	/**
	 * Write a Java method that, given a string which many contain a flying saucer
	 * broken into two parts with characters in between, return a string where the
	 * flying is fixed by removing the in between characters. Look for the two parts
	 * of the flying saucer from left to right and fix the saucer with the first
	 * available parts.
	 * <p>
	 * For example: Given: ***(==****===)*** Return: ***(=====)***
	 * <p>
	 * Given: ***(==****)**=)* Return: ***(==)**=)*
	 * <p>
	 * Given: ***(==)** Return: ***(==)**
	 * 
	 * @param s
	 * @return
	 */
	public static String fixFlyingSaucer(String s) {
		if (s == null) {
			return null;
		}
		String fixedSaucer = "";
		int beforeBreakCount = -1;
		int afterBreakCount = 0;
		int start = -1;
		int end = -1;
		int interrupt = -1;
		int resume = -1;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(' && start == -1) {
				beforeBreakCount = 0;
				start = i;
			} else if (s.charAt(i) != '=' && beforeBreakCount != -1 && s.charAt(i) != ')') {
				interrupt = i;
			} else if (s.charAt(i) == '=' && interrupt != -1 && s.charAt(i - 1) != '=') {
				resume = i;
			} else if (s.charAt(i) == ')' && ((resume != -1) || (s.charAt(i - 1) != '='))) {
				end = i;
				break;
			}
			if (s.charAt(i) == '=') {
				if (start != -1 && resume == -1) {
					beforeBreakCount++;
				}
				if (resume != -1) {
					afterBreakCount++;
				}
			}
		}
		if (start != -1 && end != -1) {
			fixedSaucer = s.substring(0, start + 1) + "=".repeat(beforeBreakCount) + "=".repeat(afterBreakCount)
					+ s.substring(end, s.length());
			return fixedSaucer;
		} else {
			return s;
		}
	}

	/**
	 * Write a Java method that, given a string which many contain many flying
	 * saucers, return the number of flying saucers. For this problem a flying
	 * saucer may wrap around from the right side of the string to the left.
	 * <p>
	 * For example: Given: ***(===)*** Return: 1
	 * <p>
	 * Given: =)**(==)**( Return: 2
	 * <p>
	 * Given: ***(=*=)** Return: 0
	 * 
	 * @param s
	 * @return
	 */
	public static int countFlyingSaucers(String s) {
		int count = 0;
		int openCount = 0;
		int closedCount = 0;
		int garboCount = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				for (int j = i + 1; j < s.length(); j++) {
					if (s.charAt(j) != '=' && s.charAt(j) != ')') {
						break;
					}
					if (s.charAt(j) == ')') {
						count++;
						i = j + 1;
					}
				}
			} else if (s.charAt(i) == ')') {
				closedCount++;
			} else if (s.charAt(i) != '=') {
				garboCount++;
			}
			if (closedCount > 0 && garboCount == 0 && openCount == 0) {
				for (int j = s.length() - 1; j >= 0; j--) {
					if (s.charAt(j) != '=' && s.charAt(j) != '(') {
						break;
					}
					if (s.charAt(j) == '(') {
						count++;
					}
				}
				openCount = 0;
				closedCount = 0;
			}
		}
		return count;
	}

	/**
	 * Write a Java method that, given a string which many contain many flying
	 * saucers, shifts all of the saucers one character to the right. For this
	 * problem a flying saucer may wrap around from the right side of the string to
	 * the left. The returned string should have the same number of characters as
	 * the given string. This is achieved by moving the character to the right of a
	 * saucer to its left. It can be assumed that saucers will never be touching
	 * each other (i.e., there will always be at least one character between any two
	 * saucers). Also, a saucer will not touch itself (e.g., "=)(=").
	 * <p>
	 * For example: Given: ***(===)*** Return: ****(===)**
	 * <p>
	 * Given: =)**(==)**( Return: (=)***(==)*
	 * <p>
	 * Given: a()bcde(=*=)fg Return: ab()cde(=*=)fg
	 * 
	 * @param s
	 * @return
	 */
	public static String flyingSaucersFly(String s) {
		int i;
		int j;
		int k;
		int count = 0;
		int openCount = 0;
		int closedCount = 0;
		int garboCount = 0;
		int saucerStart = 0;
		Boolean canBeOverlapped = true;
		String stuffInFrontOfSaucer = "";
		ArrayList<String> arr = new ArrayList<String>();
		for (i = 0; i < s.length(); i++) {
			arr.add(Character.toString(s.charAt(i)));
		}

		for (i = 0; i < arr.size(); i++) {
			if ((arr.get(i)).equals("(")) {
				saucerStart = i;
				for (j = i + 1; j < arr.size(); j++) {
					if (!((arr.get(j)).equals("=")) && !((arr.get(j)).equals(")"))) {
						break;
					}
					if ((arr.get(j)).equals(")")) {
						int saucerEnd = j;
						if (!(saucerEnd + 1 == arr.size())) {
							stuffInFrontOfSaucer = arr.get(saucerEnd + 1);
						}
						// check if saucer is at the end of the string to loop it
						if (arr.get(arr.size() - 1).equals(")")) {
							arr.set(0, arr.get(arr.size() - 1));
							for (k = arr.size() - 1; k > saucerStart - 1; k--) {
								arr.set(k, arr.get(k - 1));
							}
						}
						// make sure that saucer end isn't at the end of the string
						// if it isn't, go on
						else if (!(arr.get(arr.size() - 1).equals(")"))) {
							stuffInFrontOfSaucer = arr.get(saucerEnd + 1);
							// loop through moving all the elements of the saucer to the right one
							for (k = saucerEnd + 1; k >= saucerStart; k--) {
								if (k != 0) {
									arr.set(k, arr.get(k - 1));
								}
							}
							// set stuff that was at the start of the saucer that is now behind it
							// to the stuff that was previously before it
							arr.set(saucerStart, stuffInFrontOfSaucer);
						}
						i = j + 1;
						break;
					}
				}
			} else if ((arr.get(i)).equals(")")) {
				closedCount++;
			} else if (!(arr.get(i)).equals("=")) {
				garboCount++;
			}
			if (closedCount > 0 && garboCount == 0 && openCount == 0) {
				for (j = arr.size() - 1; j >= 0; j--) {
					if (!((arr.get(j)).equals("=")) && !((arr.get(j)).equals("("))) {
						break;
					}
					if (s.charAt(j) == '(') {
						saucerStart = j;
						count++;
					}
				}
			}
			if (count > 0 && canBeOverlapped) {
				arr.set((arr.indexOf(")") + 1), ")");
				if (arr.indexOf(")") != 0) {
					arr.set(arr.indexOf(")"), arr.get((arr.indexOf(")")) - 1));

				}
				for (k = arr.indexOf(")"); k <= 0; k--) {
					if (k > 0) {
						arr.set(k, arr.get(k - 1));
					}
				}
				arr.set(0, arr.get((arr.size()) - 1));
				// shifting top part of array list now
				// fix
				for (k = arr.size() - 1; k >= arr.indexOf("(") - 1; k--) {
					if (k + 1 != arr.size()) {
						arr.set(k + 1, arr.get(k));
					}
					if (arr.get(k).equals("*")) {
						break;
					}
				}
				arr.set(saucerStart, "*");
				canBeOverlapped = false;
			}
		}
		String listToString = "";
		for (i = 0; i < arr.size(); i++) {
			listToString += arr.get(i);
		}
		return listToString;
	}
}
