import javax.swing.*;
import java.awt.*;

public class SudokuJButton extends JButton {
    private int WIDTH = 120;
    private int HEIGHT = 40;

    public SudokuJButton(String text){
        super(text);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        this.setBackground(Color.black);
        this.setForeground(Color.white);
    }

}
