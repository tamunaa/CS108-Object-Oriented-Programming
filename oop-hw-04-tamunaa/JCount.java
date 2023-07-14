import java.awt.Dimension;
import java.util.stream.IntStream;
import javax.swing.*;

public class JCount extends JPanel {

	private static final int TEXT_FIELD_SIZE = 10;
	private static final int NUMBER_OF_COUNTERS = 4;

	private final JTextField textField;
	private final JLabel label;
	private Worker thread;

	public JCount() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		textField = new JTextField("100000000", TEXT_FIELD_SIZE);
		label = new JLabel("0");
		JButton start = new JButton("Start");
		JButton stop = new JButton("Stop");

		add(textField);
		add(label);
		add(start);
		add(stop);
		add(Box.createRigidArea(new Dimension(0, 40)));

		start.addActionListener(e -> {
			if (thread != null)
				thread.interrupt();

			thread = new Worker();
			thread.start();
		});

		stop.addActionListener(e -> {
			if (thread != null) {
				thread.interrupt();
				thread = null;
			}
		});
	}

	private class Worker extends Thread {
		@Override
		public void run() {
			int num = Integer.parseInt(textField.getText());
			for (int i = 0; i <= num && !isInterrupted(); i++) {
				if (i % 1000 != 0)continue;

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					break;
				}

				final int count = i;
				SwingUtilities.invokeLater(() -> label.setText(Integer.toString(count)));
			}
		}
	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Count");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setResizable(false);

		IntStream.range(0, NUMBER_OF_COUNTERS).forEach(i -> frame.add(new JCount()));

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(JCount::createAndShowGUI);
	}
}