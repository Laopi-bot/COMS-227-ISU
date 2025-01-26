package hw4;

import api.Point;
import api.PointPair;

/**
 * class that models a MultiSwitchLink
 * 
 * @author Lawson Port
 *
 */
public class MultiSwitchLink extends MultiLinks {

	/**
	 * an array of the connections a MultiSwitchLink has
	 */
	private PointPair[] connections = null;
	
	/**
	 * boolean for if the train is crossing or not
	 */
	private boolean isTrainCrossing = false;

	/**
	 * constructs a MultiSwitchLink
	 * @param connections
	 */
	public MultiSwitchLink(PointPair[] connections) {
		this.connections = connections;
	}

	/**
	 * swaps the point pair with the one given in the method call at a 
	 * specified index
	 * @param pointPair
	 * @param index
	 */
	public void switchConnection(PointPair pointPair, int index) {
		// turn can only be modified when train isn't crossing
		if (isTrainCrossing == false) {
			// sets given point A to new point a value
			connections[index].setPointA(pointPair.getPointA());
			// same for B
			connections[index].setPointB(pointPair.getPointB());
			
			//update connections in parent class 
			setConnections(connections);
		}
	}

	@Override
	public Point getConnectedPoint(Point point) {
		// in the case that the train goes through the same MultiLink several times
		// we need to update the point pairs in the MultiFixedLink parent class with the
		// ones stored in this instance of a MultiFixedLink
		setConnections(connections);
		return super.getConnectedPoint(point);
	}

	@Override
	public void trainEnteredCrossing() {
		isTrainCrossing = true;
	}

	@Override
	public void trainExitedCrossing() {
		isTrainCrossing = false;
	}
}
