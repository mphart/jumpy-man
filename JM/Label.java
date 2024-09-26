package JM;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
/**
 * @author Mason P. Hart
 * 
 * Objects that only display text on the screen
 */
public class Label implements Animatable{

    String text;
    int size;
    int x;
    int y;


    public Label(String text, int x, int y, int size){
        setText(text);
        setSize(size);
        moveTo(x,y);
    }


    /**
     * Moves the label to the given coordinates
     * 
     * @param x the x-coordinate to move the top left corner to
     * @param y the y-coordinate to move the top left corner to
     */
    public void moveTo(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the displayed text of the label
     * 
     * @param text
     */
    public void setText(String text){
        this.text = text;
    }

    /**
     * Sets the text font size of the label
     * 
     * @param size
     */
    public void setSize(int size){
        this.size = size;
    }

    /**
     * Draws the label.
     */
    @Override
    public void paint(Graphics g) {
        g.setFont(new Font("Impact",0,size));
        g.setColor(Color.WHITE);
        g.drawString(text,x,y);
    }

    @Override
    public void update() {
        // do nothing
    }
}