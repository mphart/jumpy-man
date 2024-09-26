package JM;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.NoSuchElementException;
/**
 * @author Mason P. Hart
 * 
 * The Manager class handles the text data file that contains the
 * player's high score to be displayed on the title screen.
 * The Manager class can:
 * -write a new score to the file
 * -read from the file
 * -create and initialize a new file if it cannot be found
 * Additionally, the Manager class alone has the power to terminate
 * the program by modifying the value of Game.running
 */
public class Manager {
    
    private File data;
    private FileWriter fw;
    private Scanner scn;
    private final String KEY = "34085238";


    public Manager(){
        try{
            data = new File("gd");
            data.setWritable(true,true);
            data.createNewFile();
            scn = new Scanner(data);
        }
        catch(IOException e){
            Game.running = false;
        }
    }


    /**
     * Gets the information from the file, and decrypts it.
     * If anything goes wrong, this method will
     * 
     * @return the highscore stored in the file
     */
    public int getHighScore(){
        try{
            String text = scn.nextLine();
            scn.close();
            return descramble(text);
        } catch (NoSuchElementException e) {
            // the file does not exist
            writeFile("0");
            return 0;
        } catch (IndexOutOfBoundsException e){
            // the file has been tampered with
            writeFile("0");
            return 0;
        } 
    }

    /**
     * Writes to the file. Always calls scramble to encrypt the data
     * @param text to be written to the file
     */
    public void writeFile(String text){
        try{
            fw = new FileWriter(data);
            fw.write(scramble(text));
            fw.close();
        } catch (IOException e){
            // the file has been deleted while the game is running
            Game.running = false;
        } catch (IndexOutOfBoundsException e){
            // the file has been tampered with
            Game.running = false;
        }
    }
    
    /**
     * Scrambles the string based on Manager.KEY
     * PRECONDITION: the length of the string is not greater than the length of the key
     * @param s the string to be scrambled
     * @return the string s scrambled
     * @throws IndexOutOfBoundsException
     */
    private String scramble(String s) throws IndexOutOfBoundsException{
        int i = Integer.parseInt(s);
        s = ""+i;
        while(s.length() < 8){
            s="0"+s;
        }
        String scramble = "";
        for (int j = 0; j < s.length(); j++) {
            scramble += (char)(s.charAt(j) + Integer.parseInt(""+KEY.charAt(j)));
        }
        return scramble;
    }
    
    /**
     * Descrambes the string based on Manager.KEY
     * PRECONDITION: the length of the string is not greater than the length of the key
     * @param s the string to be scrambled
     * @return the string s descrambled
     * @throws IndexOutOfBoundsException
     */
    private int descramble(String s) throws IndexOutOfBoundsException{
        String dscr = "";
        for (int i = 0; i < s.length(); i++) {
            dscr += (char)(s.charAt(i) - Integer.parseInt(""+KEY.charAt(i)));
        }
        return Integer.parseInt(dscr);
    }
}