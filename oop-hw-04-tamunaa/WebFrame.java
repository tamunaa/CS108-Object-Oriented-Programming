import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class WebFrame extends JFrame {

    private static final int TEXT_FIELD_WIDTH = 7;
    private static final int TEXT_FIELD_HEIGHT = 15;
    private final int TEXT_HEIGHT = 10;
    private final int WINDOW_WIDTH = 900;
    private final int WINDOW_HEIGHT = 450;
    private static final String LINKS_FILE = "links.txt";

    private DefaultTableModel model;
    private JButton singleThreadButton;
    private JButton multiButton;
    private JTextField threadNum;
    private JLabel runningLabel;
    private JLabel completedLabel;
    private JLabel elLabel;
    private JProgressBar progress;
    private JButton stopButton;

    private AtomicInteger finishedThreadNum;
    private AtomicInteger runningThreadNum;
    private long startTime;

    private Launcher launcher;

    public WebFrame() {
        super("WebLoader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        addListeners();

        pack();
        setVisible(true);
    }

    private void initComponents()  {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        model = new DefaultTableModel(new String[]{"URL", "Status"}, 0);

        try {
            fillTable();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));


        singleThreadButton = new JButton("single Thread Fetch");
        multiButton = new JButton("concurrent Fetch");

        threadNum = new JTextField("5", TEXT_FIELD_WIDTH);
        threadNum.setMaximumSize(threadNum.getPreferredSize());

        runningLabel = new JLabel("running: 0");
        completedLabel = new JLabel("completed: 0");
        elLabel = new JLabel("elapsed: ");


        progress = new JProgressBar(0, model.getRowCount());
        progress.setValue(0);
        progress.setStringPainted(true);

        stopButton = new JButton("stop");
        stopButton.setEnabled(false);

        panel.add(scrollPane);
        addComponentsOnPanel(panel);

        add(panel);
    }

    private void addComponentsOnPanel(JPanel panel) {
        panel.add(threadNum);
        panel.add(progress);

        JPanel downPanel = new JPanel(new FlowLayout());
        downPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, 50));
        downPanel.add(singleThreadButton);
        downPanel.add(multiButton);
        downPanel.add(stopButton);
        panel.add(downPanel);

        FlowLayout layout = new FlowLayout();
        layout.setHgap(60);
        JPanel textPanel = new JPanel(layout);
        textPanel.add(elLabel);
        textPanel.add(runningLabel);
        textPanel.add(completedLabel);
        panel.add(textPanel);
    }

    private void addListeners() {
        stopButton.addActionListener(e -> {
            changeButtons(true);
            long elapsedTime = System.currentTimeMillis() - startTime;
            updateElapsedLabel(elapsedTime);

            if (launcher != null) {
                launcher.stopFetching();
            }
        });

        singleThreadButton.addActionListener(e -> {
            changeButtons(false);
            resetView();
            reset();

            int numThreads = 1;
            startFetching(numThreads);
        });

        multiButton.addActionListener(e -> {
            changeButtons(false);
            resetView();
            reset();

            int numThreads = Integer.parseInt(threadNum.getText());
            startFetching(numThreads);
        });
    }

    private void reset() {
        finishedThreadNum = new AtomicInteger(0);
        runningThreadNum = new AtomicInteger(0);
    }

    private void resetView() {
        IntStream.range(0, model.getRowCount())
                .forEach(i -> model.setValueAt("", i, 1));

        progress.setValue(0);
        completedLabel.setText("Completed: 0");
        runningLabel.setText("Running: 0");
        elLabel.setText("Elapsed: ");
    }

    private void startFetching(int numThreads) {
        startTime = System.currentTimeMillis();

        launcher = new Launcher(numThreads, model, this);
        launcher.start();
    }

    void changeButtons(boolean enableFetchButtons) {
        stopButton.setEnabled(!enableFetchButtons);
        singleThreadButton.setEnabled(enableFetchButtons);
        multiButton.setEnabled(enableFetchButtons);
    }

    private void fillTable() throws IOException {
            Files.lines(Paths.get(LINKS_FILE)).forEach(line -> model.addRow(new Object[]{line, ""}));
    }

    void updateElapsedLabel(long elapsedTime) {
        double elapsedSeconds = elapsedTime / 1000.0;
        elLabel.setText("Elapsed: " + elapsedSeconds + "s");
    }

    public synchronized void changeView(final String data, final int row, boolean isCompleted, boolean isIncrease) {
        if (isCompleted) {
            finishedThreadNum.incrementAndGet();
            SwingUtilities.invokeLater(() -> {
                completedLabel.setText("Completed: " + finishedThreadNum);
                progress.setValue(finishedThreadNum.get());
                model.setValueAt(data, row, 1);
            });
            return;
        }

        if (isIncrease) {
            runningThreadNum.incrementAndGet();
        } else {
            runningThreadNum.decrementAndGet();
        }
        SwingUtilities.invokeLater(() -> runningLabel.setText("running: " + runningThreadNum));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WebFrame::new);
    }

    public long getStartTime() {
        return startTime;
    }
}
