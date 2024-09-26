package JM;
import java.awt.Graphics;
import java.awt.Color;
/**
 * @author Mason P. Hart
 * 
 * The Player class represents the player. This is the only GameObject
 * that the player can control.
 */
public class Player extends GameObject{
    
    public Player(){
        super();
        this.h = 80;
        this.w = 80;
        this.x = 80;
        this.vel = 0;
        this.y = Game.HEIGHT / 2 - this.h / 2;
        this.tag = 0;      
    }

    /**
     * Checks if the player has collided with the GameObject other provided.
     * If the player collides with the screen bounds or a Wall, return -1
     * If there is a collision, and this GameObject is a Target, return 1
     * If there is no collision with this object, return 0
     * 
     * @param other the GameObject to be compared 
     * @return an int representing the status of the collision
     */
    public int checkCollisions(GameObject other){
        // collision with screen bounds
        if(y < 0 || y + h > Game.HEIGHT){
            return -1;
        } 
        // collision with wall
        else if (other instanceof Wall &&
            ((this.x + this.w >= other.getX() && this.x <= other.getX()) || 
            (this.x + this.w >= other.getX() + other.getW() && this.x <= other.getX() + other.getW()))
            &&
            (this.y <= other.getH() || this.y + this.h >= other.getH() + Wall.WINDOW)
        ){
            return -1;
        } 
        // collision with target
        else if (other instanceof Target &&
            ((this.x + this.h >= other.getX() && this.x <= other.getX()) &&
            (this.y >= other.getY() || this.y + this.h <= other.getY() + other.getH()))
            && ((Target)other).isActive()
        ){
            ((Target)other).setInactive();
            return 1;
        }
        // no collision
        return 0;
    }

    /**
     * Causes the player to 'jump' by resetting the object's velocity to 
     * a positive constant
     */
    public void jump(){
        vel = 24;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x,y,w,h);
    }

    @Override
    public void update() {
        vel -= GameObject.GRAV;
        y -= vel;
    }

    @Override
    public void reset() {
        this.y = Game.HEIGHT / 2 - this.h / 2;
        vel = 0;
    }

}
