package JM;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
/**
 * @author Mason P. Hart
 * 
 * The JPanel class that the game is painted on. Also handles the game logic loop.
 */
public class Canvas extends JPanel implements KeyListener{

    Manager mg;
    List<Animatable> gameList;
    int highScore;
    int state;
    // gameobjects & labels
    Player p;
    Score score;
    Label tLabel;
    Label iLabel;
    Label hsLabel;
    Label dLabel;

    public Canvas(int width, int height){
        this.setBackground(Color.BLACK);
        addKeyListener(this);
        mg = new Manager();
        gameList = new ArrayList<Animatable>();
        highScore = mg.getHighScore();

        // create the player
        p = new Player();
        // create all Labels
        score = new Score(Game.WIDTH/2,70,69);
        tLabel = new Label("Jumpy Man",18,200,230);
        iLabel = new Label("Press 'Space' to Start", 180, 340, 100);
        hsLabel = new Label("High Score: "+ highScore, 30, 540, 155);
        dLabel = new Label("you died", 200, 200, 220);
        // add the Player, Walls, and Targets
        gameList.add(p);
        for (int i = 0; i < Wall.NUM_WALLS; i++) {
            Wall w = new Wall(Game.WIDTH + i*Wall.WALL_OFF);
            Target t = new Target();
            w.addTarget(t);
            gameList.add(w);
            gameList.add(t);
        }
        // add all of the GameObjects and Labels
        gameList.add(tLabel);
        gameList.add(iLabel);
        gameList.add(hsLabel);
    }


    /**
     * Contains the animation loop. While the game is running,
     * which is controlled by instance variable running, do the following:
     * update
     * paint
     * wait
     */
    public void run(){
        while(Game.running){
            long stTime = System.currentTimeMillis();
            // title screen
            if(state == 0){
                // do nothing
            }
            // game is running
            else if (state == 1){
                update();
            }
            // death screen
            else if (state == 2){
                if(!gameList.contains(dLabel) && gameList.contains(score)){
                    gameList.add(dLabel);
                    gameList.remove(score);
                }
            }
            repaint();
            delay(stTime);
        }
    }

    /**
     * Calls the update method for all active objects. Will also update the 
     * score, high score, and data file if applicable. 
     * Handles the state change from 1 --> 2 if the Player verifies that it
     * has collided with a Wall or the screen bounds.
     */
    public void update(){
        for(Animatable o : gameList){
            o.update();
            // check if its a gameobject and NOT the player
            if(o instanceof GameObject){
                // check if it collided with the player
                int val = p.checkCollisions((GameObject)o);
                if(val == -1){
                    // wall - game over
                    state = 2;
                } else if(val == 1){
                    // target - increment score
                    score.incrementScore();
                }
            }
        }
        // check if there is a new highscore
        if(score.getScore() > highScore){
            highScore = score.getScore();
            mg.writeFile(""+highScore);
            hsLabel.setText("High Score: "+highScore);
        }
    }

    /**
     * Draws every object in the gamelist, including GameObjects and text Labels
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.fillRect(0,0,Game.WIDTH,Game.HEIGHT);
        for(Animatable o : gameList){
            o.paint(g);
        }
    }

    /**
     * Delays based on the start time given for 1000/FPS milliseconds
     * 
     * @param stTime the time in millis at the beginning of the loop
     * @return the amount of time since the beginning of the loop. Should always be 1000/FPS
     */
    public long delay(long stTime){
        long currTime = System.currentTimeMillis();
        while(currTime - stTime < 1000 / Game.FPS){
            currTime = System.currentTimeMillis();
        }
        return System.currentTimeMillis() - stTime;
    }

    /**
     * Evaluates the only possible input the player has: the space bar.
     * This method handles all transitions between states EXCEPT for 1 --> 2, 
     * which is handled in update()
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            // title screen --> start game
            if(state == 0){
                state = 1;
                gameList.add(score);
                gameList.remove(tLabel);
                gameList.remove(iLabel);
                gameList.remove(hsLabel);
            }
            // game is running --> jump
            else if (state == 1){
                p.jump();
            }
            // death screen --> title screen
            else if (state == 2){    
                for(Animatable o : gameList){
                    if(o instanceof GameObject){
                        ((GameObject)o).reset();
                    }
                }
                score.resetScore();
                score.setText("0");
                state = 0;
                gameList.remove(score);
                gameList.remove(dLabel);
                gameList.add(tLabel);
                gameList.add(iLabel);
                gameList.add(hsLabel);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // do nothing
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }
}