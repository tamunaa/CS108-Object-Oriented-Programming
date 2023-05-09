import java.awt.*;
import javax.swing.*;

public class GradientPanel extends JPanel {
    private Color startColor;
    private Color endColor;

    private int WIDTH = SudokuFrame.FRAME_WIDTH/2;
    private int HEIGHT = SudokuFrame.FRAME_HEIGHT;
    private int borderOffset = 10;

    public void setColors(Color startColor, Color endColor){
        this.startColor = startColor;
        this.endColor = endColor;
    }

    public GradientPanel(LayoutManager layout) {
        super(layout);
        setOpaque(false);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBorder(BorderFactory.createEmptyBorder(borderOffset, borderOffset, borderOffset, borderOffset));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        GradientPaint gradient = new GradientPaint(0, 0, startColor, width, height, endColor);

        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);
    }
}
