package JM;
import java.awt.Graphics;
/**
 * @author Mason P. Hart
 * Invisible object attached to each Wall object
 * If the Player object detects a collision with this,
 * that means the Player has gotten through a wall
 * The Target deactivates on first collision, so that
 * the score does not increment more than once.
 * It is reactivated when it is reset to the right of the
 * Player
 */
public class Target extends GameObject{

    private boolean activated;

    public Target(){
        w = 2;
        setActive();
    }


    /**
     * Sets the position of top left coordinates of the Target
     * @param x 
     * @param y
     */
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        this.h = Wall.WINDOW;
    }

    /**
     * @return true if a collision between the player and this object should increment the score
     */
    public boolean isActive(){
        return activated;
    }

    /**
     * Sets this Target to be inactive
     */
    public void setInactive(){
        activated = false;
    }

    /**
     * Sets this Target to be active
     */
    public void setActive(){
        activated = true;
    }

    @Override
    public void paint(Graphics g) {
        // do nothing
    }

    @Override
    public void update() {
        x += vel;
    }

    /**
     * Sets the Target active
     */
    public void reset() {
        setActive();
    }
    
}
