package hw2;

import api.PlayerPosition;
import api.BallType;
import static api.PlayerPosition.*;
import static api.BallType.*;

/**
 * Class that models the game of three-cushion billiards.
 * 
 * @author Lawson Port
 */
public class ThreeCushion {

	/**
	 * keeps track of the amount of innings in a game
	 */
	private int inningCount = 1;

	/**
	 * number of points required to win
	 */
	private int scoreToWin = 0;

	/**
	 * the player who's turn it is
	 */
	private PlayerPosition currentPlayer = null;

	/**
	 * holds the score of player A
	 */
	private int playerAScore = 0;

	/**
	 * holds the score of player B
	 */
	private int playerBScore = 0;

	/**
	 * current cue ball to be shot by the player
	 */
	private BallType currentCueBall = null;

	/**
	 * determines if the shot is a break shot (1st shot of the game)
	 */
	private boolean isBreakShot;

	/**
	 * boolean to check if a foul has been committed during a shot
	 */
	private boolean isShotValid;

	/**
	 * instance variable to determine if a shot is in progress
	 */
	private boolean isShotInProgress = false;

	/**
	 * instance variable to determine if a player's inning has started
	 */
	private boolean isInningInProgress = false;

	/**
	 * indicates during a shot how many times the cue ball has contacted the red
	 * ball
	 */
	private int redBallContactCount = 0;

	/**
	 * indicates during a shot how many times the cue ball has contacted the other
	 * non red ball
	 */
	private int otherBallContactCount = 0;

	/**
	 * indicates during a shot how many times the cue ball has contacted the
	 * cushions
	 */
	private int cushionContactCount = 0;

	/*
	 * determines if the shot was a bank shot
	 */
	private boolean isBankShot;

	/**
	 * detects if there are three cushions hit before any balls are hit
	 */
	private boolean possibleBankShot;

	/**
	 * knows at all times if the game is running or not
	 */
	private boolean isGameOver;

	/**
	 * used to determine the outcome of a shot
	 */
	private int cushionAfterBalls = 0;

	/**
	 * used to determine if the lag winner has chosen their preferences
	 */
	private boolean hasLagWinnerChosen = false;

	/**
	 * Creates a new game of three-cushion billiards with a given "lag winner" and
	 * predetermined number of points that will win the game once reached
	 * 
	 * @param lagWinner   - person who chooses first (either player a or b)
	 * @param pointsToWin
	 */
	public ThreeCushion(PlayerPosition lagWinner, int pointsToWin) {
		currentPlayer = lagWinner;
		scoreToWin = pointsToWin;
		isBreakShot = true;
		isGameOver = false;
	}

	/**
	 * Sets whether the player that one chooses to take the first shot(break) or
	 * chooses the other player to break if this method is called more than once, it
	 * should have no effect. Lag winner can only choose options once and can't
	 * change their mind afterwards
	 * 
	 * @param selfBreak
	 * @param cueBall
	 */
	public void lagWinnerChooses(boolean selfBreak, BallType cueBall) {
		currentCueBall = cueBall;
		// swaps players and balls if lag winner chooses not to break, but keeps it the
		// same if they choose to break
		if (selfBreak == false) {
			swapPlayers();
			swapBalls();
		}
		hasLagWinnerChosen = true;
	}

	/**
	 * Indicates the cue stick has struck the given ball. (read javadoc for a lot of
	 * special cases)
	 * 
	 * @param ball
	 */
	public void cueStickStrike(BallType ball) {
		if (isGameOver == false) {
			isInningInProgress = true;
			isShotInProgress = true;
			if (ball != currentCueBall) {
				foul();
			}
			// resets if bank shot is true after endShot function is called
			isBankShot = false;
		}
	}

	/**
	 * indicates the player's cue ball has struck the given ball A ball strike
	 * cannot happen before a stick strike. If this method is called before the
	 * start of a shot(cueStickStrike() is called) it should do nothing If this
	 * method is called after the game has ended, it should do nothing
	 * 
	 * @param ball
	 */
	public void cueBallStrike(BallType ball) {
		// make sure stick strike has happened
		if (isInningInProgress) {
			// break shot special calculations
			if (isBreakShot) {
				if ((ball != RED) && (ball != currentCueBall)) {
					otherBallContactCount += 1;
				}
				if ((redBallContactCount == 0) && (otherBallContactCount == 1 || cushionContactCount == 1)) {
					foul();
				}
				if (ball == RED) {
					redBallContactCount += 1;
				}
			}
		}
		// make sure it isn't a break shot
		if (isBreakShot == false) {
			// make sure stick strike has happened
			if (isInningInProgress) {
				if (ball == RED) {
					redBallContactCount += 1;
				}
				if ((ball != RED) && (ball != currentCueBall)) {
					otherBallContactCount += 1;
				}
			}
		}
		// check for possible bank shot
		if ((cushionContactCount == 3) && ((redBallContactCount == 0) && (otherBallContactCount == 1))) {
			possibleBankShot = true;
		}
	}

	/**
	 * indicates the given ball has impacted the given cushion a cushion impact
	 * can't happen before a stick strike. if this method is called before the start
	 * of a shot(cueStickStrike() is called) it does nothing Can't be called after
	 * game has ended
	 */
	public void cueBallImpactCushion() {
		// make sure that the score is invalid if the cue ball hits a cushion after
		// hitting the red ball
		if ((otherBallContactCount >= 1) && (redBallContactCount >= 1) && (cushionContactCount == 2)) {
			cushionAfterBalls += 1;
		}
		// make sure stick strike has happened
		if (isInningInProgress) {
			cushionContactCount += 1;
		}
		// flags the possible bank shot if three cushions are hit before any ball is
		if ((cushionContactCount == 3) && ((redBallContactCount == 0) && (otherBallContactCount == 0))) {
			possibleBankShot = true;
		}

	}

	/**
	 * Indicates that all the balls have stopped motion and scores one point if the
	 * shot was valid and no fouls were committed Shot cannot end before it has
	 * started with a call to cueStickStrike. if this method is called before
	 * cueStickStrike, it should be ignored A shot cannot end before the start of a
	 * shot. If this method is called before the start of a shot(cueStickStrike() is
	 * called) it should do nothing can't be called after game has ended
	 */
	public void endShot() {
		boolean addScore = false;
		// calculate if shot was valid based off of if the foul method was called
		// If it was called then the isinningInProgress would be false
		if (isInningInProgress) {
			isShotValid = true;
		}

		// shot addition rules and addition of points
		if (cushionContactCount >= 3 && cushionAfterBalls == 0) {
			if (redBallContactCount >= 1) {
				if (otherBallContactCount >= 1) {
					addScore = true;
					// add component for bank shot
					if (possibleBankShot) {
						isBankShot = true;
					}
					if (isShotValid) {
						if (currentPlayer == PLAYER_A) {
							playerAScore += 1;
						} else if (currentPlayer == PLAYER_B) {
							playerBScore += 1;
						}
					}
				}
			}
		}

		// resets to do when the shot is over
		isShotInProgress = false;
		isBreakShot = false;
		// reset score determiner values and shot values
		cushionContactCount = 0;
		redBallContactCount = 0;
		otherBallContactCount = 0;

		// stuff to swap players, but wont run if endshot is called after foul
		if (isShotValid) {
			if (addScore == false) {
				inningCount += 1;
				isInningInProgress = false;
				swapPlayers();
				swapBalls();
			}
		}

		// logic to end the game if any player's score = set winning score
		if (playerAScore == scoreToWin || playerBScore == scoreToWin) {
			isGameOver = true;
			isInningInProgress = false;
		}
	}

	/**
	 * A foul immediately ends a players ending, even if the current shot hasn't
	 * ended player doesn't score a point A foul may be also called before the
	 * inning has started. The player whose turn it was to shoot has their inning
	 * forfeited and the inning count is increased by one no foul can be committed
	 * until the lag player has chosen who will break (lagWinnerChooses()) can't be
	 * called after game ends
	 */
	public void foul() {
		if (isGameOver == false) {
			if (hasLagWinnerChosen) {
				// stuff to end the inning
				isShotValid = false;
				inningCount += 1;
				isInningInProgress = false;
				swapBalls();
				swapPlayers();
			}
		}
	}

	/**
	 * when called, swaps the current players
	 */
	private void swapPlayers() {
		if (currentPlayer == PLAYER_A) {
			currentPlayer = PLAYER_B;
		} else if (currentPlayer == PLAYER_B) {
			currentPlayer = PLAYER_A;
		}
	}

	/**
	 * when called, swaps the cue balls
	 */
	private void swapBalls() {
		if (currentCueBall == WHITE) {
			currentCueBall = YELLOW;
		} else if (currentCueBall == YELLOW) {
			currentCueBall = WHITE;
		}
	}

	/**
	 * Gets the number of points scored by Player A
	 * 
	 * @return number of points
	 */
	public int getPlayerAScore() {
		return playerAScore;
	}

	/**
	 * Gets the number of points scored by Player B
	 * 
	 * @return number of points
	 */
	public int getPlayerBScore() {
		return playerBScore;
	}

	/**
	 * Gets the inning number. The inning count starts at 1
	 * 
	 * @return inning number
	 */
	public int getInning() {
		return inningCount;
	}

	/**
	 * Gets the cue ball of the current player. If this method is called in between
	 * innings, the cue ball should be for the player of the upcoming inning.
	 * doesn't work if lag winner hasn't chosen a cue ball
	 * 
	 * @return the player's cue ball
	 */
	public BallType getCueBall() {
		return currentCueBall;
	}

	/**
	 * Gets the current player. in between innings, current player = player of
	 * upcoming inning. method called before lag winner has broken, current player
	 * is undefined
	 * 
	 * @return the current player
	 */
	public PlayerPosition getInningPlayer() {
		return currentPlayer;
	}

	/**
	 * returns true iff this is the break shot
	 * 
	 * @return true if this is the break shot, false if otherwise
	 */
	public boolean isBreakShot() {
		return isBreakShot;
	}

	/**
	 * Returns true iff the most recently completed shot was a bank shot bank shot =
	 * cue ball impacts the cushions at least three times and then strikes both
	 * object balls
	 * 
	 * @return true if shot was a bank shot, false otherwise
	 */
	public boolean isBankShot() {
		return isBankShot;
	}

	/**
	 * returns true if a shot has been taken cueStickStrike() was called but endShot
	 * not called
	 * 
	 * @return true if the shot has been started, false if otherwise
	 */
	public boolean isShotStarted() {
		return isShotInProgress;
	}

	/**
	 * Returns true if the shooting player has taken their first shot of the inning
	 * inning starts at the beginning of the shot
	 * 
	 * @return true if the inning has started, false if otherwise
	 */
	public boolean isInningStarted() {
		return isInningInProgress;
	}

	/**
	 * Returns true if the game is over (one of the players reached the designated
	 * amount of points to win)
	 * 
	 * @return true if game is over, false if otherwise
	 */
	public boolean isGameOver() {
		return isGameOver;
	}

	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * <p>
	 * <tt>Player A*: X Player B: Y, Inning: Z</tt>
	 * <p>
	 * The asterisks next to the player's name indicates which player is at the
	 * table this inning. The number after the player's name is their score. Z is
	 * the inning number. Other messages will appear at the end of the string.
	 * 
	 * @return one-line string representation of the game state
	 */
	public String toString() {
		String fmt = "Player A%s: %d, Player B%s: %d, Inning: %d %s%s";
		String playerATurn = "";
		String playerBTurn = "";
		String inningStatus = "";
		String gameStatus = "";
		if (getInningPlayer() == PLAYER_A) {
			playerATurn = "*";
		} else if (getInningPlayer() == PLAYER_B) {
			playerBTurn = "*";
		}
		if (isInningStarted()) {
			inningStatus = "started";
		} else {
			inningStatus = "not started";
		}
		if (isGameOver()) {
			gameStatus = ", game result final";
		}
		return String.format(fmt, playerATurn, getPlayerAScore(), playerBTurn, getPlayerBScore(), getInning(),
				inningStatus, gameStatus);
	}
}