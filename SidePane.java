import java.awt.*;
import javax.swing.*;

public class SidePane extends JPanel {

    public SidePane(Shape next, int gridX, int gridY) {
        setBackground(Color.DARK_GRAY);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Color[][] nextC = new Color[4][6];
        double[] x = next.getX();
        double[] y = next.getY();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                nextC[i][j] = Color.BLACK;
            }
        }
        for (int j = 0; j < 4; j++) {
            nextC[(int) y[j] + 2][(int) x[j]] = next.getC();
        }
        GridPane panel = new GridPane(nextC, 6, 4);
        add(panel, BorderLayout.CENTER);
    }
}