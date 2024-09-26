package JM;
/**
 * @author Mason P. Hart
 * 
 * Jumpy Man
 * 9/20/2024
 * 
 * I first tackled this project in early 2023, when I was new to programming in Java. 
 * Looking back on the project when I was finally about to upload it to my GitHub, I
 * realized just how messy it had been, so I decided to rewrite the program again from 
 * scratch. It was a good exercise, and not too difficult; this program has a solid
 * architecture and I understood every single line of code that I wrote, which is not
 * something I could have said before. I am proud of both versions, and both of them
 * work, but this is the only one that is presentable, in terms of source code. 
 * Enjoy this very simple and 'minimalist' Flappy-Bird-inspired game.
 * 
 * 
 * BUILD TIME:
 * -Approx 20 mins for planning
 * -Approx 7 hrs for writing and debugging
 * -Approx 30 mins for documentation
 */
public class Game {
    
    public static final int HEIGHT = 600;
    public static final int WIDTH = 600 * 16 / 9;
    public static final int FPS = 30;
    public static boolean running;

    public static void main(String[] args) {
        running = true;
        new Window(WIDTH,HEIGHT);
    }
}
