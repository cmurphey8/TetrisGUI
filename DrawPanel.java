import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

class DrawPanel extends JPanel {

    private void doDrawing(Graphics g) {        
        int x = 0;
        int y = 0;
        int hw = 25;
        int buffer = 2;
        

        var g2d = (Graphics2D) g;
        g2d.setColor(Color.CYAN);
        g2d.fillRect(x, y, hw, hw);

        // StdDraw.setPenColor(StdDraw.BLACK);
        // StdDraw.square(x + 0.5, y + 0.5, 0.5); 
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, hw, hw);

        // StdDraw.line(x, y, x+1, y+1);
        // StdDraw.line(x, y+1, x+1, y);
        g2d.drawLine(x, y, x + hw, y + hw);
        g2d.drawLine(x, y + hw, x + hw, y);
        
        // StdDraw.setPenColor(superC);
        // StdDraw.filledSquare(x + 0.5, y + 0.5, 0.3);
        g2d.setColor(Color.CYAN);
        g2d.fillRect(x + buffer, y + buffer, hw - 2 * buffer, hw - 2 * buffer);

        // StdDraw.setPenColor(StdDraw.BLACK);
        // StdDraw.square(x + 0.5, y + 0.5, 0.3); 
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x + buffer, y + buffer, hw - 2 * buffer, hw - 2 * buffer);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}