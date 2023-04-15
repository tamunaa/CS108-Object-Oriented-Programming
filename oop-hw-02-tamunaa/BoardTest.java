import junit.framework.TestCase;


public class BoardTest extends TestCase {
	private Board b;
	private Piece pyr1, pyr2, pyr3, pyr4, s, sRotated;
	private Piece SQUARE_STR, SQUARE_STR1, SQUARE_STR2, SQUARE_STR3;

	private Piece STICK_STR, STICK_STR1, STICK_STR2, STICK_STR3;

	// This shows how to build things in setUp() to re-use
	// across tests.

	// In this case, setUp() makes shapes,
	// and also a 3X6 board, with pyr placed at the bottom,
	// ready to be used by tests.

	protected void setUp() throws Exception {
		b = new Board(3, 6);

		pyr1 = new Piece(Piece.PYRAMID_STR);
		pyr2 = pyr1.computeNextRotation();
		pyr3 = pyr2.computeNextRotation();
		pyr4 = pyr3.computeNextRotation();

		SQUARE_STR = new Piece(Piece.SQUARE_STR);
		SQUARE_STR1 = SQUARE_STR.computeNextRotation();
		SQUARE_STR2 = SQUARE_STR1.computeNextRotation();
		SQUARE_STR3 = SQUARE_STR2.computeNextRotation();
		SQUARE_STR2 = SQUARE_STR3.computeNextRotation();

		STICK_STR = new Piece(Piece.STICK_STR);
		STICK_STR1 = STICK_STR.computeNextRotation();
		STICK_STR2 = STICK_STR1.computeNextRotation();
		STICK_STR3 = STICK_STR2.computeNextRotation();

		s = new Piece(Piece.S1_STR);
		sRotated = s.computeNextRotation();

		b.place(pyr1, 0, 0);
	}

	// Check the basic width/height/max after the one placement
	public void testSample1() {
		assertEquals(1, b.getColumnHeight(0));
		assertEquals(2, b.getColumnHeight(1));
		assertEquals(2, b.getMaxHeight());
		assertEquals(3, b.getRowWidth(0));
		assertEquals(1, b.getRowWidth(1));
		assertEquals(0, b.getRowWidth(2));
	}

	// Place sRotated into the board, then check some measures
	public void testSample2() {
		b.commit();
		int result = b.place(sRotated, 1, 1);
		assertEquals(Board.PLACE_OK, result);
		assertEquals(1, b.getColumnHeight(0));
		assertEquals(4, b.getColumnHeight(1));
		assertEquals(3, b.getColumnHeight(2));
		assertEquals(4, b.getMaxHeight());
		b.undo();

		b.place(STICK_STR, 0, 1);
		assertEquals(5, b.getColumnHeight(0));
		assertEquals(2, b.getColumnHeight(1));
	}

	public void testSample3() {
		b.commit();
		b.place(STICK_STR, 0, 1);
		b.commit();
		int dropped = b.dropHeight(SQUARE_STR,1);
		b.place(SQUARE_STR, 1, dropped);
		b.clearRows();

		assertEquals(2, b.getColumnHeight(0));
		assertEquals(1, b.getColumnHeight(1));
		assertEquals(2, b.getMaxHeight());

		b.commit();
		b.place(STICK_STR, 2,  0);
		b.clearRows();

		assertEquals(2, b.getRowWidth(0));
		assertEquals(1, b.getRowWidth(1));
		assertEquals(3, b.getMaxHeight());
	}

	public void testSample4(){
		Board board = new Board(4, 7);

		board.place(SQUARE_STR, 0, 0);
		board.commit();
		board.place(SQUARE_STR, 2, 0);
		board.commit();
		board.clearRows();

		assertEquals(0, board.getColumnHeight(0));
		assertEquals(0, board.getColumnHeight(1));
		assertEquals(0, board.getColumnHeight(2));
		assertEquals(0, board.getColumnHeight(3));

		assertEquals(0, board.getRowWidth(0));
		assertEquals(0, board.getRowWidth(1));
		// for this moment board is all clear

		board.commit();
		int res = board.dropHeight(STICK_STR1, 0);
		board.place(STICK_STR1, 0, res);

		assertEquals(1, board.getColumnHeight(0));
		assertEquals(1, board.getColumnHeight(1));
		assertEquals(1, board.getColumnHeight(2));
		assertEquals(1, board.getColumnHeight(3));

		board.clearRows();

		assertEquals(0, board.getColumnHeight(0));
		assertEquals(0, board.getColumnHeight(1));
		assertEquals(0, board.getColumnHeight(2));
		assertEquals(0, board.getColumnHeight(3));

		assertEquals(0, board.getRowWidth(0));
	}

	public void testGetGrid(){
		Board board = new Board(5, 5);
		board.place(pyr1, 0, 0);
		board.commit();

		assertTrue(board.getGrid(0, 0));
		assertTrue(board.getGrid(1, 0));
		assertTrue(board.getGrid(1, 1));
		assertTrue(board.getGrid(2, 0));

		assertFalse(board.getGrid(2, 3));
		assertFalse(board.getGrid(3, 3));
		assertFalse(board.getGrid(2, 2));
		assertFalse(board.getGrid(1, 4));

		board.clearRows();

		String curBoard = 	"|     |\n" +
							"|     |\n" +
							"|     |\n" +
							"| +   |\n" +
							"|+++  |\n" +
							"-------";
		assertEquals(curBoard, board.toString());

	}
}
