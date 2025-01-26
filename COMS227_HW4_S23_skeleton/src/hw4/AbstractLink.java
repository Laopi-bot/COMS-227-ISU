package hw4;

import api.Crossable;
import api.Point;
import api.PositionVector;

/**
 * models an AbstractLink
 * 
 * There are two abstract classes that extend from this main abstract link
 * class. One of them encapsulates the three triple path links. The other one
 * encapsulates the two MultiLink classes. I chose to do it this way because I
 * think personally it's better than the alternative which would've been to
 * organize two subclasses of the two types by if they are switchable or not,
 * and I feel like there was too little commonality between the links that were
 * able to switch paths.
 * 
 * I then chose to lump the three triple paths together and include the common bits
 * from eacho of those classes, and did the same for the multiple link classes. I also
 * left the deadEndLink and CouplingLink classes out to the side because I felt like
 * there wasn't enough similarities between the two to include them in their own abstract
 * class or incorporate them in the other abstract classes I had created. 
 * 
 * @author Lawson Port
 *
 */
public abstract class AbstractLink implements Crossable {

	@Override
	public void shiftPoints(PositionVector positionVector) {
		Point pointA = positionVector.getPointA();
		Point pointB = positionVector.getPointB();

		// find the direction of travel
		int travel = 0;
		if (pointA.getPointIndex() < pointB.getPointIndex()) {
			travel = 1;
		} else {
			travel = -1;
		}

		// update point B index to determine where on the path we are at
		int updatedPointBIndex = pointB.getPointIndex() + travel;
		if (updatedPointBIndex < 0) {
			// we have reached the low end of the path, shift points to the next path
			Point newHighPoint = getConnectedPoint(pointA.getPath().getLowpoint());
			// get the point right below the highest point on the next track
			Point newLowPoint = getConnectedPoint(pointA.getPath().getLowpoint()).getPath().getPointByIndex(
					getConnectedPoint(pointA.getPath().getLowpoint()).getPath().getHighpoint().getPointIndex() - 1);

			// set vector using new points
			positionVector.setPointA(newHighPoint);
			positionVector.setPointB(newLowPoint);

		} else if (updatedPointBIndex >= pointB.getPath().getNumPoints()) {
			// we have reached the high end of the path, use the link to shift the points to
			// the next path
			Point newHighPoint = getConnectedPoint(pointA.getPath().getHighpoint()).getPath().getPointByIndex(
					getConnectedPoint(pointA.getPath().getHighpoint()).getPath().getLowpoint().getPointIndex() + 1);
			// get the point right get the point right above the lowest point on the next
			// track
			Point newLowPoint = getConnectedPoint(pointA.getPath().getHighpoint());

			// set vector using new points
			positionVector.setPointA(newLowPoint);
			positionVector.setPointB(newHighPoint);
		}
	}

	@Override
	public Point getConnectedPoint(Point point) {
		// so many different variations, I just decided to make the default
		// one deadEndLink and just modify every other version
		return null;
	}

	@Override
	public void trainEnteredCrossing() {
		// default is do nothing
	}

	@Override
	public void trainExitedCrossing() {
		// default is do nothing

	}

	@Override
	public int getNumPaths() {
		// default return 3, since that is the most common path amount
		return 3;
	}

}
