import greenfoot.Actor;

import java.awt.*;

/**
 * This class creates a Note object.
 * 
 * @author Steve Rosario
 * @version 2019-05-23
 */
public class Note extends Actor
{
    private Color noteColor;
    private long checkTime;
    private long startTime;
    private final int colNum;

    /**
     * Constructs a Note object with designated parameters.
     *
     * @param noteColor the color of the note.
     * @param checkTime the time that needs to be checked.
     * @param colNum the column in which the Note appears.
     */
    public Note(Color noteColor, long checkTime, int colNum)
    {
        this.noteColor = noteColor;
        this.checkTime = checkTime;
        this.startTime = System.currentTimeMillis();
        this.colNum = colNum;

        //initPositionAndScale();
    }
    
    /**
     * Act - do whatever the Note wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
      //for(int i = 0; i< Images.NOTES.length; i++)
      
        setImage(Images.NOTES[colNum]);
        int x = getX();
        int y = getY();

        setLocation(x++, y);
    
    }
    
    /**
     * Helper method for init() that initializes the position.
     */
    private void initPositionAndScale()
    {
        int noteLen = Constants.GRAPHIC_TOTAL_LENGTH / Constants.KEYS.length/4;
        getImage().scale(noteLen, Constants.GRAPHIC_KEY_HEIGHT);
       
       
        int x = Constants.GRAPHIC_COL_OFFSET + noteLen * colNum;
        int y = Constants.HEIGHT - (getImage().getHeight() / 2) 
            - Constants.GRAPHIC_KEY_K;
        setLocation (50, 100);
    }
    
    public Color getNoteColor()
    {
        return noteColor;
    }
    
    public long getCheckTime()
    {
        return checkTime;
    }
    
    public long getStartTime()
    {
        return startTime;
    }
    
    public int getColNum()
    {
        return colNum;
    }
}
