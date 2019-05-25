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
    private final int startTime;
    private final int hitTime;
    private final int colNum;

    private final long startSystemTime = System.currentTimeMillis();

    /**
     * Constructs a Note object with designated parameters.
     *
     * @param hitTime The in-game time that the note is hit.
     * @param column The column number.
     */
    public Note(int hitTime, int column)
    {
        this.startTime = hitTime - Constants.GAME_SPEED_MS;
        this.hitTime = hitTime;
        this.colNum = column;
    }

    /**
     * Construct a Note actor with Note info.
     *
     * @param noteInfo Note information
     */
    public Note(NoteInformation noteInfo)
    {
        this(noteInfo.getTime(), noteInfo.getColumn());
    }
    
    /**
     * The note fall down
     */
    public void act() 
    {
        int elapseTime = (int) (System.currentTimeMillis() - startSystemTime);

        // Set location based on time.
        setLocation(getX(), (int) Math.round((double) elapseTime / Constants.GAME_SPEED_MS * Constants.GRAPHIC_NOTE_LANDING));
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

    public int getHitTime()
    {
        return hitTime;
    }

    public int getStartTime()
    {
        return startTime;
    }
    
    public int getColumn()
    {
        return colNum;
    }
}
