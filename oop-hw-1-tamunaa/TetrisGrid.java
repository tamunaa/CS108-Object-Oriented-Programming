//
// TetrisGrid encapsulates a tetris board and has
// a clearRows() capability.

import java.util.Arrays;

public class TetrisGrid {
	
	/**
	 * Constructs a new instance with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */

	private boolean[][] grid;
	public TetrisGrid(boolean[][] grid) {
		this.grid = grid;
	}
	
	
	/**
	 * Does row-clearing on the grid (see handout).
	 */
	public void clearRows() {
		boolean[] cleared = new boolean[grid[0].length];
		Arrays.fill(cleared, false);

		for (int i = 0; i < grid[0].length; i++){
			if (shouldBeCleared(i)){
				cleared[i] = true;
			}
		}

		int counter = 0;
		for (int i = 0; i < grid[0].length; i++){
			if (!cleared[i]){
				for (int j = 0; j < grid.length; j++){
					grid[j][counter] = grid[j][i];
				}
				counter++;
			}
		}

		for (int j = counter; j<grid[0].length; j++){
			for (int i = 0; i < grid.length; i++){
				grid[i][j] = false;
			}
		}

	}

	private boolean shouldBeCleared(int c){
		for (boolean[] booleans : grid) {
			if (!booleans[c]) return false;
		}
		return true;
	}

	/**
	 * Returns the internal 2d grid array.
	 * @return 2d grid array
	 */
	boolean[][] getGrid() {
		return this.grid;
	}
}
