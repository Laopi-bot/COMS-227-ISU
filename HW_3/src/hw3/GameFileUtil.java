package hw3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import api.Tile;

/**
 * Utility class with static methods for saving and loading game files.
 */
public class GameFileUtil {
	/**
	 * Saves the current game state to a file at the given file path.
	 * <p>
	 * The format of the file is one line of game data followed by multiple lines of
	 * game grid. The first line contains the: width, height, minimum tile level,
	 * maximum tile level, and score. The grid is represented by tile levels. The
	 * conversion to tile values is 2^level, for example, 1 is 2, 2 is 4, 3 is 8, 4
	 * is 16, etc. The following is an example:
	 * 
	 * <pre>
	 * 5 8 1 4 100
	 * 1 1 2 3 1
	 * 2 3 3 1 3
	 * 3 3 1 2 2
	 * 3 1 1 3 1
	 * 2 1 3 1 2
	 * 2 1 1 3 1
	 * 4 1 3 1 1
	 * 1 3 3 3 3
	 * </pre>
	 * 
	 * @param filePath the path of the file to save
	 * @param game     the game to save
	 */
	public static void save(String filePath, ConnectGame game) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

			//declarations to add to first line string
			int width = game.getGrid().getWidth();
			int height = game.getGrid().getHeight();
			int minTileLevel = game.getMinTileLevel();
			int maxTileLevel = game.getMaxTileLevel();
			long score = game.getScore();
			String firstLine = width + " " + height + " " + minTileLevel + " " + maxTileLevel + " " + score;

			// write first line to file
			writer.write(firstLine);
			writer.write("\n");
			for (int i = 0; i < height; i++) {
				String lineOfValues = "";
				for (int j = 0; j < width; j++) {
					// input lines into file
					lineOfValues += game.getGrid().getTile(j, i).getLevel() + " ";
				}
				writer.write(lineOfValues);
				writer.write("\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads the file at the given file path into the given game object. When the
	 * method returns the game object has been modified to represent the loaded
	 * game.
	 * <p>
	 * See the save() method for the specification of the file format.
	 * 
	 * @param filePath the path of the file to load
	 * @param game     the game to modify
	 * @throws FileNotFoundException
	 */
	public static void load(String filePath, ConnectGame game) throws FileNotFoundException {
		// declarations for input into new game
		File file = new File(filePath);
		Scanner scnr = new Scanner(file);
		int width = scnr.nextInt();
		int height = scnr.nextInt();
		int minTileLevel = scnr.nextInt();
		int maxTileLevel = scnr.nextInt();
		long score = scnr.nextLong();
		Random rand = new Random();

		// declare a new game to overwrite the old game
		ConnectGame loadedGame = new ConnectGame(width, height, minTileLevel, maxTileLevel, rand);
		game = loadedGame;

		// gets values from file and sets a new tile in the new grid to that value
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int levelOldTile = scnr.nextInt();
				game.getGrid().setTile(new Tile(levelOldTile), j, i);
			}
		}
		// update score
		game.setScore(score);

		scnr.close();
	}
}
