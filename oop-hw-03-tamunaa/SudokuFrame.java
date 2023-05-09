import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;


public class SudokuFrame extends JFrame {
	SudokuJTextArea puzzleArea;
	SudokuJTextArea solutionArea;
	protected static final int FRAME_WIDTH = 1000;
	protected static final int FRAME_HEIGHT = 600;

	private void addLabels(JPanel left, JPanel right){
		SudokuJLabel puzzleLabel = new SudokuJLabel("PUZZLE: ", JLabel.CENTER);
		SudokuJLabel solutionLabel = new SudokuJLabel("SOLUTION: ", JLabel.CENTER);
		left.add(puzzleLabel, BorderLayout.NORTH);
		right.add(solutionLabel, BorderLayout.NORTH);
	}

	private void addTextAreas(JPanel left, JPanel right){
		puzzleArea = new SudokuJTextArea();
		solutionArea = new SudokuJTextArea();
		solutionArea.setEditable(false);
		left.add(puzzleArea, BorderLayout.CENTER);
		right.add(solutionArea, BorderLayout.CENTER);
	}
	private void addButtons(JPanel down){
		int w = 120;
		int h = 40;

		JCheckBox tic = new JCheckBox("auto check");
		SudokuJButton check = new SudokuJButton("check");
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				solveSudoku();
			}
		});

		tic.setSelected(true);
		puzzleArea.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
//				System.out.println("insert");
				if (tic.isSelected())solveSudoku();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
//				System.out.println("remove");
				if (tic.isSelected())solveSudoku();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
//				System.out.println("change");
				if (tic.isSelected())solveSudoku();
			}
		});


		tic.setPreferredSize(new Dimension(w, h));
		down.add(check);
		down.add(tic);
	}

	private void solveSudoku() {
		try {
			Sudoku s = new Sudoku(Sudoku.textToGrid(puzzleArea.getText()));
			int solutionNum = s.solve();
			solutionArea.setText(s.getSolutionText());
			solutionArea.append("\n");
			solutionArea.append("Solutions: " + solutionNum + "\n\n");
			solutionArea.append("elapsed: " + (Long.toString(s.getElapsed())) + " ms" + '\n');

		}catch (Exception e){
			//throw new RuntimeException();
		}
	}

	public SudokuFrame() {
		super("Sudoku Solver");

		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setResizable(false);
		setSize(new Dimension(500, 500));

		GradientPanel left = new GradientPanel(new BorderLayout());
		GradientPanel right = new GradientPanel(new BorderLayout());
		JPanel down = new JPanel(new FlowLayout());

		left.setColors(Color.PINK, Color.BLACK);
		right.setColors(Color.BLACK, Color.PINK);

		add(left, BorderLayout.WEST);
		add(right, BorderLayout.EAST);
		add(down, BorderLayout.SOUTH);

		down.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT/10));

		addLabels(left, right);
		addTextAreas(left, right);
		addButtons(down);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationByPlatform(true);
		setVisible(true);


	}
	
	
	public static void main(String[] args) {
		// GUI Look And Feel
		// Do this incantation at the start of main() to tell Swing
		// to use the GUI LookAndFeel of the native platform. It's ok
		// to ignore the exception.

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ignored) { }

		SudokuFrame frame = new SudokuFrame();


		frame.setVisible(true);
	}

}
