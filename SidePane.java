/**************************************************************************************************
 *
 *  Class to draw the sidePane that houses the next block and the score
 *  
 *  TODO: 
 *  (1) choose a Layout Manager to nest (add) here in SidePane
 *  (2) choose GUI element to house text “SCORE:”
 *  (3) choose GUI element to display score
 * 
 **************************************************************************************************/

import java.awt.*;
import javax.swing.*;

public class SidePane extends JPanel {

    public SidePane(Shape next, long score) {
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
            nextC[3 - (int) y[j]][(int) x[j]] = next.getC();
        }
        GridPane panel = new GridPane(nextC, 6, 4);
        add(panel, BorderLayout.CENTER);

        /* TODO: 
            (1) choose a Layout Manager to nest (add) here in SidePane
            (2) choose GUI element to house text “SCORE:”
            (3) choose GUI element to display score
        */
    }
}