package hw4;

import api.Point;

/**
 * class that models a switch link
 * 
 * @author Lawson Port
 *
 */
public class SwitchLink extends TripleLinks {

	/**
	 * endpoint A of a SwitchLink
	 */
	private Point endpointA = null;
	
	/**
	 * endpoint B of a SwitchLink
	 */
	private Point endpointB = null;
	
	/**
	 * endpoint C of a SwitchLink
	 */
	private Point endpointC = null;
	
	/**
	 * boolean for if the turn on a SwitchLink is activated or not
	 */
	private boolean turn = false;
	
	/**
	 * boolean for if a train is crossing or not
	 */
	private boolean isTrainCrossing = false;

	/**
	 * constructs an instance of a SwitchLink
	 * @param endpointA
	 * @param endpointB
	 * @param endpointC
	 */
	public SwitchLink(Point endpointA, Point endpointB, Point endpointC) {
		this.endpointA = endpointA;
		this.endpointB = endpointB;
		this.endpointC = endpointC;
	}

	/**
	 * sets if the turn is activated in a SwitchLink
	 * @param turn
	 */
	public void setTurn(boolean turn) {
		// turn can only be modified when train isn't crossing
		if (isTrainCrossing == false) {
			this.turn = turn;
		}
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
			// if point is on path A and point index == endpoint a index
			if (point.getPointIndex() == endpointA.getPointIndex() && point.getPath() == endpointA.getPath()) {
				// if switch == true
				if (turn) {
					return endpointC;
				} else {
					return endpointB;
				}
			}
		} // if result returns not null (it found a point, so return that point)
		else if (resultOfParentConnectedPoint != null) {
			return resultOfParentConnectedPoint;
		}
		// if it finds no point connected
		return null;
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
