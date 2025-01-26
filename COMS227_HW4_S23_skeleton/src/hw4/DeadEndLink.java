package hw4;

import api.PositionVector;

/**
 * 
 * class that models a DeadEndLink
 * 
 * @author Lawson Port
 *
 */
public class DeadEndLink extends AbstractLink {

	/**
	 * constructs a DeadEndLink
	 */
	public DeadEndLink() {
	}

	@Override
	public void shiftPoints(PositionVector positionVector) {
		// does nothing. no points to shift to
	}

	@Override
	public int getNumPaths() {
		return 1;
	}
}