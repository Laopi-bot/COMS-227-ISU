package lab7;

public class MaxValue {

	public static void main(String[] args) {
		int arrMax = 0;
		int[] test = { 3, 4, 5, 1, 2, 3, 123 }; // max should be 5
		int result = arraySum(test, arrMax);
		System.out.println(result);
	}

	/**
	 * Returns the sum of all array elements.
	 */
	public static int arraySum(int[] arr, int arrMax) {
		return findMaxNumber(arr, 0, arr.length - 1, arrMax);
	}

	/**
	 * Returns the max number from the array from start to end inclusive
	 */
	private static int findMaxNumber(int[] arr, int start, int end, int arrMax) {
		if (start == end) {
			if (arrMax < arr[start]) {
				arrMax = arr[start];
			}
			return arrMax;
		} else {
			int mid = (start + end) / 2;
			int leftMax = findMaxNumber(arr, start, mid, arrMax);
			int rightMax = findMaxNumber(arr, mid + 1, end, arrMax);
			if (rightMax > leftMax) {
				arrMax = rightMax;
			} else {
				arrMax = leftMax;
			}
			return arrMax;
		}
	}
}
