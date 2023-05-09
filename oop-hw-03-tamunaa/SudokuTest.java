import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

public class SudokuTest {

    @Test
    public void testEasyGrid() {
        Sudoku s = new Sudoku(Sudoku.easyGrid);
        int solutions = s.solve();
        assertEquals(1, solutions);
    }

    @Test
    public void testMediumGrid() {
        Sudoku s = new Sudoku(Sudoku.mediumGrid);
        int solutions = s.solve();
        assertEquals(1, solutions);
    }

    @Test
    public void testHardGrid() {
        Sudoku s = new Sudoku(Sudoku.hardGrid);
        int solutions = s.solve();
        assertEquals(1, solutions);

    }

    @Test
    public void testHardGridcp() {
        Sudoku s = new Sudoku(Sudoku.hardGridcp);
        int solutions = s.solve();
        assertEquals(6, solutions);
    }

    @Test
    public void testSolve() {
        int[][] grid = Sudoku.stringsToGrid(
                "1 0 0 0 0 7 0 9 0",
                "0 3 0 0 2 0 0 0 8",
                "0 0 9 6 0 0 5 0 0",
                "0 0 5 3 0 0 9 0 0",
                "0 1 0 0 8 0 0 0 2",
                "6 0 0 0 0 4 0 0 0",
                "3 0 0 0 0 0 0 1 0",
                "0 4 0 0 0 0 0 0 7",
                "0 0 7 0 0 0 3 0 0");
        Sudoku s = new Sudoku(grid);
        int solutions = s.solve();

//        System.out.println("solved " + s.toString());
        assertEquals(1, solutions);

        int[][] expected = Sudoku.stringsToGrid(
                "1 6 2 8 5 7 4 9 3",
                "5 3 4 1 2 9 6 7 8",
                "7 8 9 6 4 3 5 2 1",
                "4 7 5 3 1 2 9 8 6",
                "9 1 3 5 8 6 7 4 2",
                "6 2 8 7 9 4 1 3 5",
                "3 5 6 4 7 8 2 1 9",
                "2 4 1 9 3 5 8 6 7",
                "8 9 7 2 6 1 3 5 4");

        int[][] solved = Sudoku.textToGrid(s.getSolutionText());
        assert(Arrays.deepEquals(solved, expected));

        Sudoku solvedSudoku = new Sudoku(Sudoku.textToGrid(s.getSolutionText()));
        Sudoku expectedSudoku = new Sudoku(expected);

        assertEquals(solvedSudoku.toString(), expectedSudoku.toString());
        assertArrayEquals(solvedSudoku.getGrid(), expected);

        assert (solvedSudoku.getElapsed() != 0);
    }
}
