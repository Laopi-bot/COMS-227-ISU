package lab2;

/**
 * @author Lawson Port
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class RabbitModel4
{
  // TODO - add instance variables as needed
	/**
	 * Population of the rabbits
	 */
	private int population = 500;
	
	
	
  /**
   * Constructs a new RabbitModel.
   */
  public RabbitModel4()
  {
    // TODO
  }  
 
  /**
   * Returns the current number of rabbits.
   * @return
   *   current rabbit population
   */
  public int getPopulation()
  {
    // TODO - returns a dummy value so code will compile
    return population;
  }
  
  /**
   * Updates the population to simulate the
   * passing of one year.
   * Resets when the population reaches 5
   */
  public void simulateYear()
  {
    population = population / 2;
    }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
    population = 500;
  }
}
