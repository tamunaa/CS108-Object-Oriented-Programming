// HW1 2-d array Problems
// CharGrid encapsulates a 2-d grid of chars and supports
// a few operations on the grid.

public class CharGrid {
	private char[][] grid;

	/**
	 * Constructs a new CharGrid with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public CharGrid(char[][] grid) {
		this.grid = grid;
	}
	
	/**
	 * Returns the area for the given char in the grid. (see handout).
	 * @param ch char to look for
	 * @return area for given char
	 */
	public int charArea(char ch) {
		int upper = grid.length;
		int lower = -1;
		int leftMost = grid[0].length;
		int rightMost = -1;
		boolean flag = false;

		for (int i = 0; i < grid.length; i++){
			for (int j = 0; j < grid[0].length; j++){
				char c = grid[i][j];

				if (c == ch){
					flag = true;
					upper = Math.min(upper, i);
					lower = Math.max(lower, i);

					leftMost = Math.min(leftMost, j);
					rightMost = Math.max(rightMost, j);
				}
			}
		}
		if (!flag)return 0;

		return (lower - upper + 1) * (rightMost - leftMost + 1);
	}


	/**
	 * Returns the count of '+' figures in the grid (see handout).
	 * @return number of + in grid
	 */
	public int countPlus() {
		int res = 0;
		for (int i = 1; i < grid.length; i++){
			for (int j = 1; j < grid[0].length - 1; j++){
				if (isPlus(i, j)){
					res++;
				}
			}
		}
		return res;
	}

	private boolean isVal(int r, int c){
		return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
	}
	private int countSame(int r, int c, int rp, int cp){
		int res = 0;

		while (true){
			r = r + rp;
			c = c + cp;
			if(!isVal(r, c))return res;
			if (grid[r - rp][c - cp] != grid[r][c])return res;
			res++;
		}
	}
	private boolean isPlus(int r, int c){
		int res1 = countSame(r, c, 1, 0);
		int res2 = countSame(r, c, 0, 1);
		int res3 = countSame(r, c, -1, 0);
		int res4 = countSame(r, c, 0, -1);
		return Math.min(res1, res2) != 0 && Math.min(res3, res4) != 0 &&
				res1 == res2 && res3 == res4 && res2 == res3;
	}

}
