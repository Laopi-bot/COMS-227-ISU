package hw4;

import api.Point;
import api.PointPair;

/**
 * abstract class that extends AbstractLink and models a base
 * Multilink that is used as a parent calss for the two 
 * MultiLinks in the project
 * @author Lawson Port
 *
 */
public abstract class MultiLinks extends AbstractLink {
	
	/**
	 * An array of connections that a MultiLink has
	 */
	private PointPair[] connections = null;

	public Point getConnectedPoint(Point point) {
		int trackNumber = connections.length;

		// loop through each connection to see if the point is equal
		// to the start of a connection, and if the point is on the same
		// path as the point at the start of the connection
		for (int i = 0; i < trackNumber; i++) {
			// checks if indexes are the same for point A
			if (point.getPointIndex() == connections[i].getPointA().getPointIndex()) {
				// checks if paths are the same
				if (point.getPath() == connections[i].getPointA().getPath()) {
					return connections[i].getPointB();
				}
			}
			
			//check if indexes are the same for point B
			if (point.getPointIndex() == connections[i].getPointB().getPointIndex()) {
				// checks if paths are the same
				if (point.getPath() == connections[i].getPointB().getPath()) {
					return connections[i].getPointA();
				}
			}
		}
		// if it finds no point
		return null;
	}
	
	/**
	 * sets the connections in this class to ones which are given by the constructor of the child
	 * class and reinitialized before every getConnectedPoint call, that way no matter what
	 * multi link path you came from in the past, you always have the points of the one you're
	 * currently on
	 * @param connections
	 */
	protected void setConnections(PointPair[] connections) {
		this.connections = connections;
	}
	
	@Override
	public int getNumPaths() {
		return connections.length;
	}
}
