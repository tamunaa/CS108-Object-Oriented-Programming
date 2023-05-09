import javax.swing.*;
import java.awt.*;

public class SudokuJTextArea extends JTextArea {
    private int TXT_OFFSET_UP = 50;
    private int TXT_OFFSET_LEFT = 100;
    private Boolean lineWrap = true;
    private final Font fn = new Font("Tahoma", Font.PLAIN, 30);
    private Color BACKGROUND_COLOR= new Color(217, 197, 197);

    public SudokuJTextArea() {
        super();
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBorder(BorderFactory.createEmptyBorder(TXT_OFFSET_UP, TXT_OFFSET_LEFT, 0, TXT_OFFSET_LEFT));
        this.setFont(fn);
        this.setLineWrap(lineWrap);
        this.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        this.setBackground(BACKGROUND_COLOR);
    }





}
