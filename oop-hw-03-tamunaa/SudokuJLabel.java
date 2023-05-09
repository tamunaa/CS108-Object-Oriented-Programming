import javax.swing.*;
import java.awt.*;

public class SudokuJLabel extends JLabel {
    private Font fn = new Font("Arial", Font.PLAIN, 30);

    public SudokuJLabel(String text, int horizontalAlignment) {
        super(text, null, horizontalAlignment);

        this.setForeground(Color.white);
        this.setFont(fn);
    }



}
