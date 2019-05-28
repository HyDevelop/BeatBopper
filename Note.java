import greenfoot.Actor;
import greenfoot.GreenfootImage;

/**
 * This class creates a Note object.
 *
 * @author Team APCSA 2019
 * @author Andrew Vittiglio
 * @author Steve Rosario
 * @version 2019-05-23
 */
@SuppressWarnings("WeakerAccess")
public class Note extends Actor
{
    private static final int NOTE_WIDTH = Constants.GRAPHIC_TOTAL_LENGTH / Constants.KEYS.length;

    private final int startTime;
    private final int hitTime;
    private final int column;
    
    private final long startTimeSystem;

    // Init note textures when this class is accessed for the first time.
    static
    {
        initNoteTextures();
    }

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
        this.column = column;
        
        this.startTimeSystem = System.currentTimeMillis();

        setImage(Images.NOTES[column]);
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
        int elapseTime = (int) (System.currentTimeMillis() - startTimeSystem);

        // Fall to the correct location.
        double y = (double) elapseTime * Constants.GRAPHIC_NOTE_LANDING / Constants.GAME_SPEED_MS;
        setLocation(getX(), (int) Math.round(y)); 
    }

    /**
     * Initialize position
     */
    public void init()
    {
        // Initialize position
        int x = Constants.GRAPHIC_COL_OFFSET + Constants.GRAPHIC_TOTAL_LENGTH / 4 * column;
        setLocation(x, 0);
    }

    /**
     * Initialize note textures
     */
    public static void initNoteTextures()
    {
        for (GreenfootImage note : Images.NOTES)
        {
            note.scale(NOTE_WIDTH, Constants.GRAPHIC_NOTE_HEIGHT);
        }
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
        return column;
    }
}
