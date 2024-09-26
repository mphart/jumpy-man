package JM;
import java.awt.Graphics;
/**
 * @author Mason P. Hart
 * 
 * The ancestor for all objects that can be drawn and updated on the screen.
 * The following extend GameObject: Player, Wall, Target
 */
public abstract class GameObject implements Animatable{
    protected int x;
    protected int y;
    protected int vel;
    protected int tag;
    protected int w;
    protected int h;
    public static final int GRAV = 3;


    /**
     * The default velocity is -10, so that the player begins to fall
     * The velocity can mean different things for different objects, for example,
     * the player can only move up and down, so vel describes y-velocity.
     * For a Wall, which can only move left, it is x-velocity.
     */
    public GameObject(){
        this.tag = -1;
        this.vel = -10;
    }

    /**
     * Unused in this project
     * 
     * @return this GameObject's tag
     */
    public int getTag(){
        return this.tag;
    }

    /**
     * The x-coordinate of the top left corner of this object
     * 
     * @return the x coordinate of the top left corner
     */
    public int getX(){
        return this.x;
    }

    /**
     * The y-coordinate of the top left corner of this object
     * 
     * @return the y coordinate of the top left corner
     */
    public int getY(){
        return this.y;
    }

    /**
     * @return the height of this object
     */
    public int getH(){
        return this.h;
    }

    /**
     * @return the width of this object
     */
    public int getW(){
        return this.w;
    }

    public abstract void paint(Graphics g);
    public abstract void update();
    /**
     * Will be called whenever the game state changes from the death screen to the main menu
     */
    public abstract void reset();
}
