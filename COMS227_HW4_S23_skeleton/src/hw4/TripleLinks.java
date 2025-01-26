package hw4;

import api.Point;

/**
 * abstract class that extends AbstractLink and models a base
 * TripleLink that is used as a parent class for the three
 * TripleLinks in the project
 * @author Lawson Port
 *
 */
public abstract class TripleLinks extends AbstractLink {
	
	/**
	 * endpoint A in a TripleLink
	 */
	private Point endpointA = null;
	
	/**
	 * endpoint B in a TripleLink
	 */
	private Point endpointB = null;
	
	/**
	 * endpoint C in a TripleLink
	 */
	private Point endpointC = null;

	/**
	 * general method for getting a connected Point
	 */
	public Point getConnectedPoint(Point point) {
		//tries points that are always the same for the three tripleLinks and if
		//it fails, it continues the search in the subclasses
		
		 // if it is on point B and has the same index as the endpoint of B
		if (point.getPointIndex() == endpointB.getPointIndex() && point.getPath() == endpointB.getPath()) {
			return endpointA;
		}// if it is on point C and has the same index as the endpoint of C
		else if (point.getPointIndex() == endpointC.getPointIndex() && point.getPath() == endpointC.getPath()) {
			return endpointA;
		}	
		//if it doesn't find a match
		return null;
	}
	
	/**
	 * sets the endpoints in this class to ones which are given by the constructor of the child
	 * class and reinitialized before every getConnectedPoint call, that way no matter what
	 * tripleLink path you came from in the past, you always have the points of the one you're
	 * currently on
	 * @param endpointA
	 * @param endpointB
	 * @param endpointC
	 */
	protected void setEndpoints(Point endpointA, Point endpointB, Point endpointC) {
		this.endpointA = endpointA;
		this.endpointB = endpointB;
		this.endpointC = endpointC;
	}
	
}
