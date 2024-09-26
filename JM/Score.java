package JM;
/**
 * @author Mason P. Hart
 * The score class is a special label that keeps track of the score.
 * It is the only label that has many possible texts not accounted for (any number 1 - 2^16)
 */
public class Score extends Label {

    private int s;

    public Score(int x, int y, int size) {
        super("0", x, y, size);
        s = 0;
        setText(""+s);
    }
    

    /**
     * @return the score
     */
    public int getScore(){
        return s;
    }

    /**
     * Increments the score by one
     */
    public void incrementScore(){
        s++;
        setText(""+s);
    }

    /**
     * Resets the score to zero
     */
    public void resetScore(){
        s = 0;
    }
}
