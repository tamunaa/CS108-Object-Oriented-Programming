import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.SQLException;

public class DBView extends JFrame {

    protected JTextField metropolisField;
    protected JTextField continentField;
    protected JTextField populationField;

    protected JComboBox populationBox;
    protected JComboBox matchBox;
    private final int FIELD_LEN = 11;


    JButton add;
    JButton search;

    private int HEIGHT = 700;
    private int WIDTH = 1000;

    public DBView(DBModel model){
        super("Metropolis View");
        this.setLayout(new BorderLayout());
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);

        JPanel northPanel = new JPanel(new FlowLayout());
        northPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        addNorthFields(northPanel);
        this.add(northPanel, BorderLayout.NORTH);

        TableModel tableModel = model;
        JTable left = new JTable(tableModel);
        left.setRowHeight(40);
        left.setAlignmentX(30);
        left.setAlignmentY(20);

        JPanel center = new JPanel(new BorderLayout());
        JPanel right = new JPanel(new GridLayout(3, 1, 0, 10));


        addRightUpperPart(right);
        addComboPart(right);

        center.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        left.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        right.setBorder(BorderFactory.createLineBorder(Color.black));

        center.add(left, BorderLayout.CENTER);
        center.add(right, BorderLayout.EAST);
        this.add(center, BorderLayout.CENTER);


        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void addRightUpperPart(JPanel right){
        JPanel rightUpper = new JPanel(new GridLayout(3, 1, 2, 10));
        rightUpper.setPreferredSize(new Dimension(300, 0));
        rightUpper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addEastFields(rightUpper);
        right.add(rightUpper);
    }

    private void addComboPart(JPanel right){
        JPanel comboPanel = new JPanel(new GridLayout(3, 1, 2, 10));
        comboPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        populationBox = new JComboBox(new String[]{"Population larger than", "Population smaller than or equal to"});
        matchBox = new JComboBox(new String[]{"Exact match", "Partial match"});

        populationBox.setBackground(Color.white);
        matchBox.setBackground(Color.white);

        comboPanel.add(populationBox);
        comboPanel.add(matchBox);

        right.add(comboPanel);
    }
    private void addEastFields(JPanel right){
        add = new JButton("Add");
        search = new JButton("Search");

        add.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        search.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        add.setBorder(new LineBorder(Color.black));
        search.setBorder(new LineBorder(Color.BLACK));

        right.add(add);
        right.add(search);
    }

    private void addNorthFields(JPanel northPanel){
        JLabel metropolisLab = new JLabel("Metropolis: ");
        JLabel continentLab = new JLabel("Continent: ");
        JLabel populationLab = new JLabel("Population: ");
        metropolisField = new JTextField(FIELD_LEN);
        continentField = new JTextField(FIELD_LEN);
        populationField = new JTextField(FIELD_LEN);

        northPanel.add(metropolisLab);
        northPanel.add(metropolisField);
        northPanel.add(continentLab);
        northPanel.add(continentField);
        northPanel.add(populationLab);
        northPanel.add(populationField);

    }


}
