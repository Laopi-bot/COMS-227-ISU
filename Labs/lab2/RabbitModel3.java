package lab2;

/**
 * @author Lawson Port
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class RabbitModel3
{
  // TODO - add instance variables as needed
	/**
	 * Population of the rabbits
	 */
	private int population = 0;
	
	
	
  /**
   * Constructs a new RabbitModel.
   */
  public RabbitModel3()
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
    population += 1;
    if(population == 5) {
    	reset();
    }
  }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
    population = 0;
  }
}
