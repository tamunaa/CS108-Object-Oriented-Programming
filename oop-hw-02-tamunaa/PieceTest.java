import junit.framework.Assert;
import junit.framework.TestCase;

import java.awt.*;
import java.util.*;

/*
  Unit test for Piece class -- starter shell.
 */
public class PieceTest extends TestCase {
	// You can create data to be used in the your
	// test cases like this. For each run of a test method,
	// a new PieceTest object is created and setUp() is called
	// automatically by JUnit.
	// For example, the code below sets up some
	// pyramid and s pieces in instance variables
	// that can be used in tests.

	private Piece SQUARE_STR, SQUARE_STR1, SQUARE_STR2, SQUARE_STR3;
	private Piece STICK_STR, STICK_STR1, STICK_STR2, STICK_STR3;
	private Piece L1_STR, L1_STR1, L1_STR2, L1_STR3;
	private Piece L2_STR, L2_STR1, L2_STR2, L2_STR3;
	private Piece S1_STR, S1_STR1, S1_STR2, S1_STR3;
	private Piece S2_STR, S2_STR1, S2_STR2, S2_STR3;
	private Piece PYRAMID_STR, PYRAMID_STR1, PYRAMID_STR2, PYRAMID_STR3;

	Piece[] fastRotated;

	protected void setUp() throws Exception {
		super.setUp();

		init_STICK_STR();
		init_SQUARE_STR();
		init_L1_STR();
		init_L2_STR ();
		init_S1_STR();
		init_S2_STR();
		init_PYRAMID_STR();

		fastRotated = Piece.getPieces();
	}
	void init_L2_STR (){
		L2_STR = new Piece(Piece.L2_STR);
		L2_STR1 = L2_STR.computeNextRotation();
		L2_STR2 = L2_STR1.computeNextRotation();
		L2_STR3 = L2_STR2.computeNextRotation();
	}

	void init_S1_STR (){
		S1_STR = new Piece(Piece.S1_STR);
		S1_STR1 = S1_STR.computeNextRotation();
		S1_STR2 = S1_STR1.computeNextRotation();
		S1_STR3 = S1_STR2.computeNextRotation();
	}

	void init_S2_STR (){
		S2_STR = new Piece(Piece.S2_STR);
		S2_STR1 = S2_STR.computeNextRotation();
		S2_STR2 = S2_STR1.computeNextRotation();
		S2_STR3 = S2_STR2.computeNextRotation();
	}

	void init_PYRAMID_STR (){
		PYRAMID_STR = new Piece(Piece.PYRAMID_STR);
		PYRAMID_STR1 = PYRAMID_STR.computeNextRotation();
		PYRAMID_STR2 = PYRAMID_STR1.computeNextRotation();
		PYRAMID_STR3 = PYRAMID_STR2.computeNextRotation();
	}
	void init_L1_STR(){
		L1_STR = new Piece(Piece.L1_STR);
		L1_STR1 = L1_STR.computeNextRotation();
		L1_STR2 = L1_STR1.computeNextRotation();
		L1_STR3 = L1_STR2.computeNextRotation();
	}
	void init_SQUARE_STR(){
		SQUARE_STR = new Piece(Piece.SQUARE_STR);
		SQUARE_STR1 = SQUARE_STR.computeNextRotation();
		SQUARE_STR2 = SQUARE_STR1.computeNextRotation();
		SQUARE_STR3 = SQUARE_STR2.computeNextRotation();
		SQUARE_STR2 = SQUARE_STR3.computeNextRotation();
	}

	void init_STICK_STR(){
		STICK_STR = new Piece(Piece.STICK_STR);
		STICK_STR1 = STICK_STR.computeNextRotation();
		STICK_STR2 = STICK_STR1.computeNextRotation();
		STICK_STR3 = STICK_STR2.computeNextRotation();
	}

	// Here are some sample tests to get you started


	public void testSTICK_STR_onSize(){
		assertEquals(1, STICK_STR.getWidth());
		assertEquals(4, STICK_STR.getHeight());
	}

	public void testSQUARE_STR_onSize(){
		assertEquals(2, SQUARE_STR.getWidth());
		assertEquals(2, SQUARE_STR.getHeight());
	}


	public void testL_STR_onSize(){
		assertEquals(2, L1_STR.getWidth());
		assertEquals(3, L1_STR.getHeight());

		assertEquals(2, L2_STR.getWidth());
		assertEquals(3, L2_STR.getHeight());
	}

	public void testS_STR_onSize(){
		assertEquals(3, S1_STR.getWidth());
		assertEquals(2, S1_STR.getHeight());

		assertEquals(3, S2_STR.getWidth());
		assertEquals(2, S2_STR.getHeight());
	}
	public void testPYRAMID_STR_onSize(){
		assertEquals(3, PYRAMID_STR.getWidth());
		assertEquals(2, PYRAMID_STR.getHeight());
	}

	public void testSampleSkirt() {
		assertTrue(Arrays.equals(new int[] {0}, STICK_STR.getSkirt()));

	}

	public void testSTICK_STR_rotation(){
		String STICK_STR_R0 = "0 0  0 1  0 2  0 3";
		String STICK_STR_R1 = "0 0  1 0  2 0  3 0";

		assertTrue(new Piece(STICK_STR_R0).equals(STICK_STR));
		assertTrue(new Piece(STICK_STR_R1).equals(STICK_STR1));
		assertTrue(new Piece(STICK_STR_R0).equals(STICK_STR2));
		assertTrue(new Piece(STICK_STR_R1).equals(STICK_STR3));
	}

	public void testSQUARE_STR_rotation() {
		String SQUARE_STR_R0 = "0 0  0 1  1 0  1 1";

		assertTrue(new Piece(SQUARE_STR_R0).equals(SQUARE_STR));
		assertTrue(new Piece(SQUARE_STR_R0).equals(SQUARE_STR1));
		assertTrue(new Piece(SQUARE_STR_R0).equals(SQUARE_STR2));
		assertTrue(new Piece(SQUARE_STR_R0).equals(SQUARE_STR3));

	}


	public void testL1_STR_rotation() {

		String L1_STR_R0 = "0 0  0 1  0 2  1 0";
		String L1_STR_R1 = "0 0  1 0  2 0  2 1";
		String L1_STR_R2 = "0 2  1 0  1 1  1 2";
		String L1_STR_R3 = "0 0  0 1  1 1  2 1";

		assertTrue(new Piece(L1_STR_R0).equals(L1_STR));
		assertTrue(new Piece(L1_STR_R1).equals(L1_STR1));
		assertTrue(new Piece(L1_STR_R2).equals(L1_STR2));
		assertTrue(new Piece(L1_STR_R3).equals(L1_STR3));
	}

	public void testL2_STR_rotation() {

		String L2_STR_R0 = "0 0  1 0  1 1  1 2";
		String L2_STR_R1 = "0 1  1 1  2 0  2 1";
		String L2_STR_R2 = "0 0  0 1  0 2  1 2";
		String L2_STR_R3 = "0 0  0 1  1 0  2 0";

		assertTrue(new Piece(L2_STR_R0).equals(L2_STR));
		assertTrue(new Piece(L2_STR_R1).equals(L2_STR1));
		assertTrue(new Piece(L2_STR_R2).equals(L2_STR2));
		assertTrue(new Piece(L2_STR_R3).equals(L2_STR3));

	}

	public void testS1_STR_rotation() {

		String S1_STR_R0 = "0 0  1 0  1 1  2 1";
		String S1_STR_R1 = "0 1  0 2  1 0  1 1";
		String S1_STR_R2 = "0 0  1 0  1 1  2 1";
		String S1_STR_R3 = "0 1  0 2  1 0  1 1";

		assertTrue(new Piece(S1_STR_R0).equals(S1_STR));
		assertTrue(new Piece(S1_STR_R1).equals(S1_STR1));
		assertTrue(new Piece(S1_STR_R2).equals(S1_STR2));
		assertTrue(new Piece(S1_STR_R3).equals(S1_STR3));

	}

	public void testS2_STR_rotation() {

		String S2_STR_R0 = "0 1  1 0  1 1  2 0";
		String S2_STR_R1 = "0 0  0 1  1 1  1 2";
		String S2_STR_R2 = "0 1  1 0  1 1  2 0";
		String S2_STR_R3 = "0 0  0 1  1 1  1 2";

		assertTrue(new Piece(S2_STR_R0).equals(S2_STR));
		assertTrue(new Piece(S2_STR_R1).equals(S2_STR1));
		assertTrue(new Piece(S2_STR_R2).equals(S2_STR2));
		assertTrue(new Piece(S2_STR_R3).equals(S2_STR3));

	}

	public void testPYRAMID_STR_rotation() {

		String PYRAMID_STR_R0 = "0 0  1 0  1 1  2 0";
		String PYRAMID_STR_R1 = "0 1  1 0  1 1  1 2";
		String PYRAMID_STR_R2 = "0 1  1 0  1 1  2 1";
		String PYRAMID_STR_R3 = "0 0  0 1  0 2  1 1";

		assertTrue(new Piece(PYRAMID_STR_R0).equals(PYRAMID_STR));
		assertTrue(new Piece(PYRAMID_STR_R1).equals(PYRAMID_STR1));
		assertTrue(new Piece(PYRAMID_STR_R2).equals(PYRAMID_STR2));
		assertTrue(new Piece(PYRAMID_STR_R3).equals(PYRAMID_STR3));

	}

	public void testSTICK_STR_onFastRotation(){
		assertEquals(fastRotated[0], STICK_STR);
		assertEquals(fastRotated[0].computeNextRotation(), STICK_STR1);
		assertEquals(fastRotated[0].computeNextRotation().computeNextRotation(), STICK_STR2);
	}

	public void testL1_STR_onFastRotation(){
		assertEquals(fastRotated[1], L1_STR);
		assertEquals(fastRotated[1].computeNextRotation(), L1_STR1);
		assertEquals(fastRotated[1].computeNextRotation().computeNextRotation(), L1_STR2);
		assertEquals(fastRotated[1].computeNextRotation().computeNextRotation().computeNextRotation(), L1_STR3);
	}

	public void testS2_STR_onFastRotation(){
		assertEquals(fastRotated[4], S2_STR);
		assertEquals(fastRotated[4].computeNextRotation(), S2_STR1);
		assertEquals(fastRotated[4].computeNextRotation().computeNextRotation(), S2_STR2);
		assertEquals(fastRotated[4].computeNextRotation().computeNextRotation().computeNextRotation(), S2_STR3);
	}

	public void testParsePointsRuntimeExceptionMessage() {
		String invalidString = "1 2 3a 4";
		String good = "0 0	0 1	 0 2  0 3";
		String cur = invalidString;

		for (int i = 0; i < 2; i++) {
			try {
				new Piece(cur);
			} catch (RuntimeException e) {
				assertEquals("Could not parse x,y string:" + cur, e.getMessage());
			}
			cur = good;
		}
	}

	public void testToString(){
		assertEquals(STICK_STR.toString(), "[(0,0), (0,1), (0,2), (0,3)]");
		assertEquals(SQUARE_STR.toString(), "[(0,0), (0,1), (1,0), (1,1)]");
	}

	public void testGetBody(){
		assertEquals(new Piece(STICK_STR.getBody()), STICK_STR);
		assertEquals(new Piece(STICK_STR1.getBody()), STICK_STR1);
		assertEquals(new Piece(STICK_STR2.getBody()), STICK_STR2);
		assertEquals(new Piece(STICK_STR3.getBody()), STICK_STR3);
	}

	public void testFastRotation(){
		assertEquals(fastRotated[0].fastRotation(), STICK_STR1);
		assertEquals(fastRotated[0].fastRotation().fastRotation(), STICK_STR2);
		assertEquals(fastRotated[0].fastRotation().fastRotation().fastRotation(), STICK_STR3);

		assertEquals(fastRotated[6].fastRotation(), PYRAMID_STR1);
		assertEquals(fastRotated[6].fastRotation().fastRotation(), PYRAMID_STR2);
		assertEquals(fastRotated[6].fastRotation().fastRotation().fastRotation(), PYRAMID_STR3);
	}

	public void testPoints(){
		TPoint[] pointArr = new TPoint[STICK_STR.getBody().length];
		TPoint[] stickArr = STICK_STR.getBody();

		for (int i = 0; i < stickArr.length; i ++){
			pointArr[i] = new TPoint(stickArr[i]);
		}

		assertTrue(Arrays.equals(pointArr, STICK_STR.getBody()));
	}

	public void testComparePoints(){
		TPoint p1 = new TPoint(1, 0);
		TPoint p2 = new TPoint(1, 0);

		assertEquals(0, p1.compareTo(p2) );
	}
}
