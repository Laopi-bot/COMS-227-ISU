package lab3;

import balloon3.Balloon;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class BalloonTests {

	private Balloon bloon;

	@Test
	public void initialTest() { // tests to see if balloon has radius zero when inflated
		bloon = new Balloon(5);
		assertEquals(0, bloon.getRadius());
		// also tests if bloon is popped when initialized
		assertEquals(false, bloon.isPopped());
	}

	@Test
	public void maxRadiusTest() { //checks to see if bloon pops after inflating below max radius
		bloon = new Balloon(5);
		bloon.blow(4);
		assertEquals(false, bloon.isPopped());
	}
	
	@Test
	public void inflateTest() { // tests to see if baloon inflates to right radius
		bloon = new Balloon(5);
		bloon.blow(4);
		assertEquals(4, bloon.getRadius());
		// also tests if it pops under the value that it should pop at
		assertEquals(false, bloon.isPopped());
	}

	@Test
	public void overInfate() { // over inflates balloon to test if it pops
		bloon = new Balloon(5);
		bloon.blow(6);
		assertEquals(0, bloon.getRadius());
		// checks if balloon is popped function returns true if it is popped
		assertEquals(true, bloon.isPopped());
		// checks if bloon inflates after being popped
		bloon.blow(3);
		assertEquals("bloon inflate after being popped?", 0, bloon.getRadius());
	}

	@Test
	public void popTest() { // checks to see if pop function works properly
		bloon = new Balloon(5);
		bloon.blow(4);
		bloon.pop();
		assertEquals(true, bloon.isPopped());
		assertEquals(0, bloon.getRadius());
		// cheks if popped bloon can be inflated again
		bloon.blow(3);
		assertEquals(0, bloon.getRadius());

	}

	@Test
	public void negativeTest() { // checks to see if bloon can inflate negatively
		bloon = new Balloon(5);
		bloon.blow(-3);
		assertEquals(0, bloon.getRadius());
	}

	@Test
	public void negativeStartTest() { // checks and sees if negative radius sets radius max to 0
		bloon = new Balloon(-5);
		assertEquals(0, bloon.getRadius());
		assertEquals(false, bloon.isPopped());
		bloon.blow(1);
		assertEquals(true,bloon.isPopped());
	}

	@Test
	public void deflateTest() { // checks if deflate sets radius to zero and returns thaat the bloon is not
								// popped
		bloon = new Balloon(5);
		bloon.blow(5);
		bloon.deflate();
		assertEquals(0, bloon.getRadius());
		assertEquals(false, bloon.isPopped());
	}

	@Test
	public void deflateTest1() { //blow up balloon twice and see 
		bloon = new Balloon(5);
		bloon.blow(2);
		assertEquals(2, bloon.getRadius());
		bloon.blow(1);
		assertEquals(3, bloon.getRadius());
	}
	// test results
	/**
	 * bloon1 did't pop when the radius was over max radius 
	 * bloon2 inflated after being popped 
	 * bloon3 sets the new radius to equal to the radius the blow funcion gives
	 * bloon4 returns that the deflated bloon was popped
	 */
}
