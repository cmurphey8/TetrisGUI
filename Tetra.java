/**************************************************************************************************
 *  Compilation:  javac Tetra.java
 *  Execution:    java Tetra
 *  Dependencies: StdDraw.java Shape.java Tetroid*.java
 * 
 *  Usage:  Move blocks with keys: a (left), a (right), s (down), w (rotate), space (slam down)
 * 
 *  GOAL: Complete the TetraSet Class -> complete the reduce() and fullRow() methods
 * 
 *  Discussion: 
 * 
 *  (1) What is the significance of the TetraSet class elements being declared protected and static?
 * 
 *  (2) What do we gain from having our Shape and Display classes extend TetraSet? 
 * 
 *  (3) Making only this one change, could we set the global tetroid object in Tetra to type TetraSet? 
 *          -> Why or why not? 
 *
 *  (4) Making only this one change, could we set the global templates array in Display to type TetraSet? 
 *          -> Why or why not?
 * 
 *  (5) Suppose we were determined to change Shape from abstract to interface, 
 *      but we did not want to add any more methods to our Tetroids. How could we accomplish this?
 * 
 *  EXTRA PRACTICE: Find a suitable end-game procedure to break out of the while(true) loop below!
 * 
 **************************************************************************************************/

public class Tetra { 
    // final global constants
    public final static int DELAY = 10;
    public final static int gridX = 10;
    public final static int gridY = 20;

    // global objects
    public static Shape tetroid;
    public static TetraSet blob = new TetraSet(gridX, gridY);
    // public static Display background = new Display();

    public static View frameView;

    // global block select
    public static int nextBlock;
    public static Shape nextTetroid;

    public static void main(String[] args) {   
        boolean inPlay = false;
        nextBlock = -1;
        select(nextBlock);

        frameView = new View(nextTetroid);
        long time0 = System.currentTimeMillis();
        while (true) {
            long timeDelta = System.currentTimeMillis() - time0;
            // select a new block if last block no longer in play
            if (!inPlay) {
                inPlay = select(nextBlock);
            }

            // process key entries if object is in play
            if (frameView.hasNextKeyTyped() && inPlay) {
                switch (frameView.nextKeyTyped()) {
                    case ' ':    // space bar >> slam down
                        tetroid.slam();
                        break;
                    case 'a':    // a >> move left
                        tetroid.move(-1);
                        break;
                    case 'w':    // w >> rotate
                        tetroid.rotateSuper();
                        break;
                    case 'd':    // d >> move right
                        tetroid.move(1);
                        break;
                    case 's':    // s >> speed down
                        tetroid.drop();
                        break;
                }
            }

            // if block IN play: drop on-time; remove from play if floored
            if (inPlay) {
                // reload frame with Tetroid updates
                frameView.nextFrame(nextTetroid, tetroid);
                if (timeDelta > 500) {
                    if (!tetroid.drop()) {
                        inPlay = unselect();
                    }
                    time0 = System.currentTimeMillis();
                }
                try {
                    Thread.sleep(DELAY);
                }
                catch (InterruptedException e) {
                    System.out.println("Error sleeping");
                }
            }
        } // end of while
    } 

    // remove block from play if floored
    public static boolean unselect() {
        blob.update(tetroid);
        tetroid = null;
        return false;
    }

    // reinit block if no block in play
    public static boolean select(int k) {
        // init a new block of type k, centered at the top of the grid
        if (k >= 0) {
            initNew(k, gridX / 2, gridY - 2);
        }
        initNext(nextBlock(), 1, -1);
        return true;
    }

    // select the next block to play
    public static int nextBlock() {
        nextBlock = (nextBlock + 1) % 7;
        return nextBlock;
    }

    public static void initNext(int k, double x, double y) {
        // add a new block to the tetroids array by the template type identified
        switch (k) {
            case 0: 
                nextTetroid = new TetroidI(x, y);
                break;
            case 1: 
                nextTetroid = new TetroidJ(x, y);
                break;
            case 2: 
                nextTetroid = new TetroidL(x, y);
                break;
            case 3: 
                x++;
                nextTetroid = new TetroidO(x, y);
                break;
            case 4: 
                nextTetroid = new TetroidS(x, y);
                break;
            case 5: 
                nextTetroid = new TetroidT(x, y);
                break;
            case 6:
                y++;
                nextTetroid = new TetroidZ(x, y);
                break;
        }
    }

    public static void initNew(int k, double x, double y) {
        // add a new block to the tetroids array by the template type identified
        switch (k) {
            case 0: 
                tetroid = new TetroidI(x, y);
                break;
            case 1: 
                tetroid = new TetroidJ(x, y);
                break;
            case 2: 
                tetroid = new TetroidL(x, y);
                break;
            case 3: 
                tetroid = new TetroidO(x, y);
                break;
            case 4: 
                tetroid = new TetroidS(x, y);
                break;
            case 5: 
                tetroid = new TetroidT(x, y);
                break;
            case 6:
                tetroid = new TetroidZ(x, y);
                break;
        }
    }
} 