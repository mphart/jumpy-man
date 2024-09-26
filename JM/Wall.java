package JM;
import java.awt.Color;
import java.awt.Graphics;
/**
 * @author Mason P. Hart
 * 
 * The Wall class represents the obstacles that the Player must get through
 * Each Wall has a target.
 * The height of each Wall will be randomized when they are reset
 */
public class Wall extends GameObject{

    private int xInit;
    private Target t;
    public static final int WINDOW = 250;
    public static final int NUM_WALLS = 7;
    public static final int WALL_OFF = 300;
    
    public Wall(int x){
        this.w = 50;
        this.x = x;
        this.xInit = x;
        this.y = 0;
        this.tag = 1;
        randomize();  
    }


    /**
     * @param t the Target to be added to this wall
     */
    public void addTarget(Target t){
        this.t = t;
        t.setPosition(x+w-1,h);
    }

    /**
     * Called after this wall is reset
     * Sets the Target active again and moves it to this Wall's new location
     */
    private void resetChildTarget(){
        if(t != null){
            t.setActive(); 
            t.setPosition(x+w-1,h);
        }
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x,y,w,h);
        g.fillRect(x,y+WINDOW+h,w,Game.HEIGHT-WINDOW-h);
    }

    /**
     * Updates the Wall's position, if it has left the screen then reset
     * to the right, outside of the Player's view
     */
    @Override
    public void update() {
        x += vel;
        // check if the wall has left the screen
        if(x < -w){
            x += NUM_WALLS*w + (NUM_WALLS-1)*WALL_OFF;
            resetChildTarget();
        }
    }

    @Override
    public void reset() {
        x = xInit;
        randomize();
        resetChildTarget();
    }

    /**
     * Randomizes the height of the Wall
     */
    private void randomize(){
        // should be between 20 and (Game.HEIGHT - Wall.WINDOW - 20) (inclusive), in multiples of 10
        h = (int)(20 + 10*(Math.random()*(Game.HEIGHT-Wall.WINDOW)/10 - 2));
    }
}
