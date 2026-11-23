package common;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * THE WINDOW. Knows nothing about the paper: it just shows a list of Displayable.
 */
public class GridView {

    private final JFrame frame;
    private final List<JPanel> cells = new ArrayList<>();
    private final List<JLabel> labels = new ArrayList<>();

    public GridView(String title, int rows, int cols, int width, int height) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // closing the window ends the program
        frame.setSize(width, height);

        JPanel grid = new JPanel(new GridLayout(rows, cols, 4, 4)); // rows, cols, gaps
        for (int i = 0; i < rows * cols; i++) {                     // i = 0, 1, ..., rows*cols - 1
            JPanel cell = new JPanel(new BorderLayout());           // 1 make card
            cell.setBackground(Color.WHITE);                        //   (default is light grey!)
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel label = new JLabel("", SwingConstants.CENTER);   // 2 make label
            cell.add(label, BorderLayout.CENTER);                   // 3 label on card
            grid.add(cell);                                         // 4 card on grid
            cells.add(cell);                                        // 5 keep both
            labels.add(label);
        }
        frame.add(grid);
    }

    /** Show the window. Call this AFTER building everything. */
    public void show() {
        frame.setVisible(true);
    }

    public void refresh(List<? extends Displayable> things) {
        for (int i = 0; i < things.size() && i < cells.size(); i++) {
            Displayable d = things.get(i);
            labels.get(i).setText(d.getCellText());     // POLYMORPHISM: each object's own text
        }
        frame.repaint();
    }
}
