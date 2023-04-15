import junit.framework.TestCase;
import java.util.*;

public class TetrisGridTest extends TestCase {
	
	// Provided simple clearRows() test
	// width 2, height 3 grid
	public void testClear0() {
		boolean[][] before =
		{	
			{true, true, false },
			{false, true, true }
		};
		
		boolean[][] after =
		{	
			{true, false, false },
			{false, true, false }
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}

	public void testClear1() {
		boolean[][] before =
				{
						{true, false, false },
						{false, true, true }
				};

		boolean[][] after =
				{
						{true, false, false },
						{false, true, true }
				};

		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}

	public void testClear2() {
		boolean[][] before =
				{
						{true, true, false },
						{true, true, true },
						{true, true, true }
				};

		boolean[][] after =
				{
						{false, false, false},
						{true, false, false},
						{true, false, false}
				};

		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}

	public void testClear3() {
		boolean[][] before =
				{
						{true, true, false },
						{true, false, true },
						{true, true, true }
				};

		boolean[][] after =
				{
						{true, false, false},
						{false, true, false},
						{true, true, false}
				};

		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
}
