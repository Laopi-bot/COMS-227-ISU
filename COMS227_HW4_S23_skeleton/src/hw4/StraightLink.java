package hw4;

import api.Point;

/**
 * class that models a StraightLink
 * 
 * @author Lawson Port
 *
 */
public class StraightLink extends TripleLinks {

	/**
	 * endpoint A in a StraightLink
	 */
	private Point endpointA = null;
	
	/**
	 * endpoint B in a StriaghtLink
	 */
	private Point endpointB = null;
	
	/**
	 * endpoint C in a StraightLink
	 */
	private Point endpointC = null;

	/**
	 * constructs an instance of a StraightLink
	 * @param endpointA
	 * @param endpointB
	 * @param endpointC
	 */
	public StraightLink(Point endpointA, Point endpointB, Point endpointC) {
		this.endpointA = endpointA;
		this.endpointB = endpointB;
		this.endpointC = endpointC;
	}

	@Override
	public Point getConnectedPoint(Point point) {
		// update endpoints of the parent class
		setEndpoints(endpointA, endpointB, endpointC);

		// run getConnectedPoint to check if it is one of the points that all three
		// triple classes have in common
		Point resultOfParentConnectedPoint = super.getConnectedPoint(point);

		// if it returns null = didn't find point
		if (resultOfParentConnectedPoint == null) {
			// if point is on path A and has the same index as the endpoint of A
			if (point.getPointIndex() == endpointA.getPointIndex() && point.getPath() == endpointA.getPath()) {
				return endpointB;
			}
		}//if result returns not null (it found a point, so return that point)
		else if(resultOfParentConnectedPoint != null) {
			return resultOfParentConnectedPoint;
		}
		// if it finds no point connected
		return null;
	}
}
