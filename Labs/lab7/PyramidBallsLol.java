package lab7;

public class PyramidBallsLol {

	public static void main(String[] args) {
		int pyramidLevel = 7;
		System.out.println(getPyramidCount(pyramidLevel));

	}

	private static int getPyramidCount(int levels) {
		if (levels == 1) {
			return 1;
		} else {
			int levelCount = (levels * levels) + getPyramidCount(levels - 1);
			return levelCount;
		}
	}
}
