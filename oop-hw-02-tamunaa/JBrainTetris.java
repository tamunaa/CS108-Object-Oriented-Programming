
import javax.swing.*;
import java.awt.*;
import java.util.random.RandomGenerator;

public class JBrainTetris extends JTetris {

    private Brain brain;
    private Brain.Move move;
    private boolean seenPiece;
    private JCheckBox brainMode;
    private JSlider adversary;
    private JLabel status;
    private JCheckBox mode;
    private RandomGenerator rgen = RandomGenerator.getDefault();

    public static void main(String[] args) {
        JBrainTetris tetris = new JBrainTetris(30);
        JFrame frame = JTetris.createFrame(tetris);
        frame.setPreferredSize(new Dimension(50, 50));
        frame.setVisible(true);
    }
    JBrainTetris(int pixels) {
        super(pixels);
        brain = new DefaultBrain();
    }

    private void initArversary(){
        status = new JLabel("*ok*");
        adversary = new JSlider(0, 100, 0);
        adversary.setPreferredSize(new Dimension(100, 10));
    }


    public JComponent createControlPanel() {
        JPanel panel = (JPanel) super.createControlPanel();

        // Adversary - slider - ok status
        JPanel left = new JPanel();

        initArversary();
        left.add(new JLabel("Adversary:"));
        left.add(adversary);
        left.add(status);

        left.setVisible(true);
        panel.add(left);

        brainMode = new JCheckBox("Brain active");
        panel.add(brainMode);

        mode = new JCheckBox("Animate Falling");
        mode.setSelected(true);
        panel.add(mode);

        return panel;
    }

    @Override
    public void tick(int verb) {
        if (!(brainMode.isSelected() && verb == DOWN)) {
            super.tick(verb);
            return;
        }

        if (!seenPiece) {
            seenPiece = !seenPiece;
            board.undo();
            move = brain.bestMove(board, currentPiece, board.getHeight(), move);
        }

        if (move == null){
            super.tick(verb);
            return;
        }

        if (!move.piece.equals(currentPiece))super.tick(ROTATE);

        if (move.x < currentX) {
            super.tick(LEFT);
        }else if (move.x > currentX){
            super.tick(RIGHT);
        }else if (!mode.isSelected() && move.x == currentX && currentY > move.y) {
            super.tick(DROP);
        }

        super.tick(verb);
    }


    @Override
    public void addNewPiece() {
        seenPiece = false;
        super.addNewPiece();
    }

    @Override
    public Piece pickNextPiece() {
        status.setText("*ok*");
        int adversaryValue = adversary.getValue();

        if(!(rgen.nextInt(99) < adversaryValue)){
            return super.pickNextPiece();
        }

        Piece nxtPiece = worstOption();
        return nxtPiece;
    }

    private Piece worstOption(){
        Piece res = new Piece("");
        double curScore = Double.MAX_VALUE;

        for (Piece piece : pieces) {
            board.undo();
            Brain.Move nxtMove = brain.bestMove(board, piece, board.getHeight(), null);

            if (nxtMove ==  null)continue;

            if (curScore > nxtMove.score){
                curScore = nxtMove.score;
                res = piece;
            }
        }

        return res;
    }

}