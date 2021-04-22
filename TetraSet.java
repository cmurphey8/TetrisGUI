/**********************************************************************************
 *
 *  A class to store all placed boxes in one object -> Nothing to do here!
 *              
 **********************************************************************************/
import java.awt.Color;

public class TetraSet {
    protected static int gridX;
    protected static int gridY;
    protected static Color[][] superC;     // yx Color for squares

    //******************************************************************
    //  CONSTRUCTORS
    //*******************************************************************/

    public TetraSet(int x, int y) {
        gridX = x;
        gridY = y; 
        superC = new Color[gridY][gridX];
    }

    public TetraSet() {}

    //******************************************************************
    //  MUTATORS
    //*******************************************************************/

    public void update(Shape Tetroid) {
        // gather Tetroid data
        double[] xT = Tetroid.getX();
        double[] yT = Tetroid.getY();
        Color cT = Tetroid.getC();

        // update background with this set piece
        for (int i = 0; i < 4; i++) {
            superC[(int) yT[i]][(int) xT[i]] = cT;
        }

        // reduce if blocks span the grid
        reduce();
    }

    private void reduce() {
        // eliminate all blocks in a row if the row is full
        for (int i = 0; i < gridY - 1; i++) {
            if (fullRow(i)) {
                for (int j = i; j < gridY - 1; j++)
                    for (int k = 0; k < gridX; k++)
                        superC[j][k] = superC[j + 1][k];
                reduce();
                return;
            }
        }  

        if (fullRow(gridY - 1))
            for (int k = 0; k < gridX; k++)
                superC[gridY - 1][k] = null;                                  
    } 
    
    private boolean fullRow(int i) {
        for (int j = 0; j < gridX; j++)
            if (superC[i][j] == null)
                return false;
        return true;
    }    
}
