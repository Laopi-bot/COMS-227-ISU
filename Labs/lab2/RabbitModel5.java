package lab2;

/**
 * enables random stuff
 */
import java.util.Random;

/**
 * @author Lawson Port A RabbitModel is used to simulate the growth of a
 *         population of rabbits.
 */

public class RabbitModel5 {
	// TODO - add instance variables as needed
	/**
	 * Population of the rabbits
	 */
	private int population = 0;
	Random rand = new Random();

	/**
	 * creates new random object
	 */

	/**
	 * Constructs a new RabbitModel.
	 */
	public RabbitModel5() {
		// TODO
	}

	/**
	 * Returns the current number of rabbits.
	 * 
	 * @return current rabbit population
	 */
	public int getPopulation() {
		// TODO - returns a dummy value so code will compile
		return population;
	}

	/**
	 * Updates the population to simulate the passing of one year. Resets when the
	 * population reaches 5
	 */
	public void simulateYear() {
		int RandomValue = rand.nextInt(10);
		population = population + RandomValue;
	}

	/**
	 * Sets or resets the state of the model to the initial conditions.
	 */
	public void reset() {
		population = 0;
	}
}
