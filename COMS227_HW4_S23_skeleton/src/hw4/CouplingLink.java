package hw4;

import api.Point;

/**
 * class that models a CouplingLink
 * 
 * @author Lawson Port
 *
 */
public class CouplingLink extends AbstractLink {

	/**
	 * endpoint of the current path
	 */
	private Point thisPathEndPoint = null;

	/**
	 * starting point of the next path
	 */
	private Point nextPathStartPoint = null;

	/**
	 * constructs a CouplingLink
	 * @param endpoint1
	 * @param endpoint2
	 */
	public CouplingLink(Point endpoint1, Point endpoint2) {
		thisPathEndPoint = endpoint1;
		nextPathStartPoint = endpoint2;
	}

	@Override
	public Point getConnectedPoint(Point point) {
		// if the point index is equal to the index of the point on the path
		// return the next path's point that it is linked to
		if (point.getPointIndex() == thisPathEndPoint.getPointIndex()) {
			return nextPathStartPoint;
		}
		return null;
	}

	@Override
	public int getNumPaths() {
		// coupling link will always be two because only two paths are involved at once
		return 2;
	}
}
