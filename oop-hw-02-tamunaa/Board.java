// Board.java

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 CS108 Tetris Board.
 Represents a Tetris board -- essentially a 2-d grid
 of booleans. Supports tetris pieces and row clearing.
 Has an "undo" feature that allows clients to add and remove pieces efficiently.
 Does not do any drawing or have any idea of pixels. Instead,
 just represents the abstract 2-d board.
*/
public class Board	{
	// Some ivars are stubbed out for you:
	private int width;
	private int height;
	private boolean[][] grid;
	private int[] widths;
	private int[] heights;
	private boolean DEBUG = true;
	boolean committed;

	private boolean[][] Xgrid;
	private int[] Xwidths;
	private int[] Xheights;

	
	
	// Here a few trivial methods are provided:
	
	/**
	 Creates an empty board of the given width and height
	 measured in blocks.
	*/
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		grid = new boolean[width][height];
		committed = true;

		widths = new int[height];
		heights = new int[width];

		Xgrid = new boolean[width][height];
		Xwidths = new int[height];
		Xheights = new int[width];

		commit();
	}
	
	
	/**
	 Returns the width of the board in blocks.
	*/
	public int getWidth() {
		return width;
	}
	
	
	/**
	 Returns the height of the board in blocks.
	*/
	public int getHeight() {
		return height;
	}
	
	
	/**
	 Returns the max column height present in the board.
	 For an empty board this is 0.
	*/
	public int getMaxHeight() {	 
		int res = 0;
		for (int i : heights){
			res = Math.max(i, res);
		}
		return res;
	}

	/**
	 Checks the board for internal consistency -- used
	 for debugging.
	*/
	public void sanityCheck() {
		if (DEBUG) {
			// YOUR CODE HERE
//			System.out.println(this);

//			for (int i = 0; i < width; i++){
//				System.out.println(Arrays.toString(grid[i]));
//			}
//			System.out.println("");
		}
	}
	
	/**
	 Given a piece and an x, returns the y
	 value where the piece would come to rest
	 if it were dropped straight down at that x.
	 
	 <p>
	 Implementation: use the skirt and the col heights
	 to compute this fast -- O(skirt length).
	*/
	public int dropHeight(Piece piece, int x) {
		int[] skirt = piece.getSkirt();

		int res = IntStream.range(0, piece.getWidth())
				.map(i -> getColumnHeight(x + i) - piece.getSkirt()[i])
				.filter(nxtY -> nxtY > 0)
				.max()
				.orElse(0);

		return res;
	}
	
	
	/**
	 Returns the height of the given column --
	 i.e. the y value of the highest block + 1.
	 The height is 0 if the column contains no blocks.
	*/
	public int getColumnHeight(int x) {
		return heights[x];
	}
	
	
	/**
	 Returns the number of filled blocks in
	 the given row.
	*/
	public int getRowWidth(int y) {
		 return widths[y];
	}
	
	
	/**
	 Returns true if the given block is filled in the board.
	 Blocks outside of the valid width/height area
	 always return true.
	*/
	public boolean getGrid(int x, int y) {
		if (x < 0 || x >= getWidth())return true;
		if (y < 0 || y >= getHeight())return true;
		return grid[x][y];
	}
	
	
	public static final int PLACE_OK = 0;
	public static final int PLACE_ROW_FILLED = 1;
	public static final int PLACE_OUT_BOUNDS = 2;
	public static final int PLACE_BAD = 3;
	
	/**
	 Attempts to add the body of a piece to the board.
	 Copies the piece blocks into the board grid.
	 Returns PLACE_OK for a regular placement, or PLACE_ROW_FILLED
	 for a regular placement that causes at least one row to be filled.
	 
	 <p>Error cases:
	 A placement may fail in two ways. First, if part of the piece may falls out
	 of bounds of the board, PLACE_OUT_BOUNDS is returned.
	 Or the placement may collide with existing blocks in the grid
	 in which case PLACE_BAD is returned.
	 In both error cases, the board may be left in an invalid
	 state. The client can use undo(), to recover the valid, pre-place state.
	*/
	public int place(Piece piece, int x, int y) {
		// flag !committed problem
		if (!committed) throw new RuntimeException("place commit problem");
			
		committed = false;
		//დასასეივებელი მაქვს მდგომარეობა
		save();
		int result = PLACE_OK;

		for (TPoint p : piece.getBody()){
			int xoffset = p.x;
			int yoffset = p.y;

			int xcor = x + xoffset;
			int ycor = y + yoffset;

			if (xcor < 0 || xcor >= width)return PLACE_OUT_BOUNDS;
			if (ycor < 0 || ycor >= height)return PLACE_OUT_BOUNDS;

			if (grid[xcor][ycor])return PLACE_BAD;

			grid[xcor][ycor] = true;
			widths[ycor]++;
			heights[xcor] = Math.max(heights[xcor], ycor + 1);
			if (widths[ycor] == getWidth()){
				result = PLACE_ROW_FILLED;
			}
		}
		return result;
	}


	private void save(){
		for (int i = 0; i < width; i++){
			System.arraycopy(grid[i], 0, Xgrid[i], 0, height);
		}
		System.arraycopy(heights, 0, Xheights, 0, width);
		System.arraycopy(widths, 0, Xwidths, 0, height);
	}
	
	/**
	 Deletes rows that are filled all the way across, moving
	 things above down. Returns the number of rows cleared.
	*/

	public int clearRows() {
		committed = false;

		int rowsCleared = 0;
		int filledRows = 0;

		for (int i = 0; i < height; i++) {
			if (widths[i] == width) rowsCleared++;
			else {
				if (rowsCleared == 0) {
					filledRows++;
					continue;
				}
				for (int j = 0; j < width; j++){
					grid[j][filledRows] = grid[j][i];
				}
				widths[filledRows] = widths[i];
				filledRows++;
			}
		}

		IntStream.range(filledRows, height)
				.forEach(i -> {
					IntStream.range(0, width)
							.forEach(j -> {
								grid[j][i] = false;
								heights[j] -= 1;
							});
					widths[i] = 0;
				});

		sanityCheck();
		return rowsCleared;
	}



	/**
	 Reverts the board to its state before up to one place
	 and one clearRows();
	 If the conditions for undo() are not met, such as
	 calling undo() twice in a row, then the second undo() does nothing.
	 See the overview docs.
	*/
	public void undo() {
		if (committed)return;
		committed = true;

		boolean[][] temp = grid;
		grid = Xgrid;
		Xgrid = temp;

		int[] tempY = widths;
		widths = Xwidths;
		Xwidths = tempY;

		int[] tempX = heights;
		heights = Xheights;
		Xheights = tempX;

	}
	
	
	/**
	 Puts the board in the committed state.
	*/
	public void commit() {
		committed = true;
	}


	public String toString() {
		StringBuilder buff = new StringBuilder();
		for (int y = height-1; y>=0; y--) {
			buff.append('|');
			for (int x=0; x<width; x++) {
				if (getGrid(x,y)) buff.append('+');
				else buff.append(' ');
			}
			buff.append("|\n");
		}
		buff.append("-".repeat(Math.max(0, width + 2)));
		return(buff.toString());
	}
}


