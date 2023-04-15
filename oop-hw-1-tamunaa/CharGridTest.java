
// Test cases for CharGrid -- a few basic tests are provided.

import junit.framework.TestCase;

public class CharGridTest extends TestCase {
	
	public void testCharArea1() {
		char[][] grid = new char[][] {
				{'a', 'y', ' '},
				{'x', 'a', 'z'},
			};
		
		
		CharGrid cg = new CharGrid(grid);
				
		assertEquals(4, cg.charArea('a'));
		assertEquals(1, cg.charArea('z'));
	}
	
	
	public void testCharArea2() {
		char[][] grid = new char[][] {
				{'c', 'a', ' '},
				{'b', ' ', 'b'},
				{' ', ' ', 'a'}
			};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(6, cg.charArea('a'));
		assertEquals(3, cg.charArea('b'));
		assertEquals(1, cg.charArea('c'));
	}

	public void testCharArea3() {
		char[][] grid = new char[][] {
				{'c', 'a', ' '},
				{'b', ' ', 'b'},
				{' ', ' ', 'a'}
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(0, cg.charArea('z'));
	}
	public void testCountPlus0() {
		char[][] grid = new char[][] {
				{'c', '1', ' '},
				{'a', '1', '1'},
				{' ', '1', 'a'}
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(0, cg.countPlus());
	}
	public void testCountPlus1() {
		char[][] grid = new char[][] {
				{'c', 'a', ' '},
				{'a', 'a', 'a'},
				{' ', 'a', 'a'}
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(1, cg.countPlus());
	}

	public void testCountPlus2() {
		char[][] grid = new char[][] {
				{'c', 'a', 'b', 'b', 'b'},
				{'a', 'b', 'b', 'b', 'b'},
				{' ', 'a', 'b', 'b', 'b'}
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(0, cg.countPlus());
	}

	public void testCountPlus3() {
		char[][] grid = new char[][] {
				{'c', 'a', 'x', 'b', 'b'},
				{'a', 'b', 'x', 'b', 'b'},
				{'x', 'x', 'x', 'x', 'x'},
				{' ', 'a', 'x', 'b', 'b'},
				{' ', 'a', 'x', 'b', 'b'}
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(1, cg.countPlus());
	}
}
