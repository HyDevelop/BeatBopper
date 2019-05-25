import greenfoot.Actor;

/**
 * This class creates a Note object.
 * 
 * @author Steve Rosario
 * @version 2019-05-23
 */
@SuppressWarnings("WeakerAccess")
public class Note extends Actor
{
    private final long hitTime;
    private final long startTime;
    private final int colNum;

    /**
     * Constructs a Note object with designated parameters.
     *
     * @param startTime The in-game time that the note is spawned.
     * @param hitTime The in-game time that the note is hit.
     * @param column The column number.
     */
    public Note(long startTime, long hitTime, int column)
    {
        this.hitTime = hitTime;
        this.startTime = System.currentTimeMillis();
        this.colNum = column;
    }
    
    /**
     * Act - do whatever the Note wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setLocation(getX(), getY() + 1);
    }

    /**
     * Initialize
     */
    public void init()
    {
        setImage(Images.NOTES[colNum]);

        int noteLen = Constants.GRAPHIC_TOTAL_LENGTH / Constants.KEYS.length;
        getImage().scale(noteLen, getImage().getHeight());

        int x = Constants.GRAPHIC_COL_OFFSET + noteLen * colNum;
        setLocation (x, 0);
    }

    // ###################
    // Getters and Setters
    // ###################

    public long getHitTime()
    {
        return hitTime;
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
