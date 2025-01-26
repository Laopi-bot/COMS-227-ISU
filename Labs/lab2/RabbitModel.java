package lab2;

/**
 * enables random stuff
 */

/**
 * @author Lawson Port A RabbitModel is used to simulate the growth of a
 *         population of rabbits.
 */

public class RabbitModel {
	// TODO - add instance variables as needed

	/**
	 * defines the population of last year
	 */
	private int lastYear = 1;
	/**
	 * defines population of the year before last year
	 */
	private int yearBefore = 0;
	/**
	 * defines population of current year
	 */
	private int population = 1;

	/**
	 * Constructs a new RabbitModel
	 */
	public RabbitModel() {
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
	 * Updates the population to simulate the passing of one year. documents the yearbefore and
	 * lastYear with previous values of last year and population, and then updates population with the sum
	 * of those numbers to replicate the fibbonacci sequence
	 */
	public void simulateYear() {
		yearBefore = lastYear;
		lastYear = population;
		population = lastYear + yearBefore;
	}

	/**
	 * Sets or resets the state of the model to the initial conditions.
	 */
	public void reset() {
		population = 1;
		lastYear = 1;
		yearBefore = 0;
	}
}
