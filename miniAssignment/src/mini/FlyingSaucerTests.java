package mini;

import java.util.ArrayList;

public class FlyingSaucerTests {

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
		for(i = 0; i < arr.size(); i++) {
			listToString += arr.get(i);
		}
		return listToString;
	}

	public static void main(String[] args) {
		String s = "==)*(==";
		System.out.println(flyingSaucersFly(s));

	}
}
