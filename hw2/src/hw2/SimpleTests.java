package hw2;

import static api.BallType.RED;
import static api.BallType.WHITE;
import static api.BallType.YELLOW;
import static api.PlayerPosition.PLAYER_A;
import static api.PlayerPosition.PLAYER_B;

/**
 * Some simple tests that may help you to get started implementing the
 * ThreeCushion class. Warning: these tests do not cover all of the
 * specifications. Perform your own testing in addition to using the
 * specchecker.
 */
public class SimpleTests {
	public static void main(String args[]) {
		ThreeCushion game = new ThreeCushion(PLAYER_B, 3);
		game.lagWinnerChooses(false, WHITE);
		System.out.println("Ball is: " + game.getCueBall() + "expected: yellow");
		
	}
}