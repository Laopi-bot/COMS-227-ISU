package hw4;

import api.Point;
import api.PointPair;

/**
 * class that models a MultiFixedLink
 * 
 * @author Lawson Port
 *
 */
public class MultiFixedLink extends MultiLinks {

	/**
	 * an array of the connections a MultiFixedLink has
	 */
	private PointPair[] connections = null;

	/**
	 * constructs a MultiFixedLink
	 * @param connections
	 */
	public MultiFixedLink(PointPair[] connections) {
		this.connections = connections;
	}

	/**
	 * sets the parent class with the current path connections
	 * and then calls the getConnectedPoint method in the parent class
	 */
	@Override
	public Point getConnectedPoint(Point point) {
		// in the case that the train goes through the same MultiLink several times
		// we need to update the point pairs in the MultiFixedLink parent class with the
		// ones stored in this instance of a MultiFixedLink
		setConnections(connections);
		return super.getConnectedPoint(point);
	}
}