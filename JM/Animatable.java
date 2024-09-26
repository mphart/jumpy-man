package JM;
import java.awt.Graphics;
/**
 * @author Mason P. Hart
 * 
 * Interface for all objects that can be painted,
 * anything that can be drawn on the screen should 
 * implement this class.
 * For this project, it would perhaps be better 
 * structured as Drawable, with
 * abstract void update() as a member of
 * GameObject instead.
 */
public interface Animatable {
    public void paint(Graphics g);
    public void update();
}
