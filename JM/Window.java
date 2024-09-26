package JM;
import java.awt.Dimension;
import javax.swing.JFrame;
/**
 * @author Mason P. Hart
 * 
 * The Window class creates a new JFrame and a new Canvas object,
 * and handles all of the necessary attributes
 */
public class Window {
    public Window(int width, int height){
        JFrame f = new JFrame();
        Canvas c = new Canvas(width, height);
        f.setResizable(false);
        f.setTitle("Jumpy Man");
        c.setFocusable(true);
        c.requestFocusInWindow();
        c.setPreferredSize(new Dimension(width,height));
        f.add(c);
        f.pack();
        f.setVisible(true);
        c.run();
    }
}
