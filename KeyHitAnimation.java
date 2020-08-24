import greenfoot.Actor;
import greenfoot.GreenfootImage;

/**
 * This class displays a hit animation on the note hitting line after 
 * the player hits a note.
 * 
 * @author Team APCSA 2019
 * @author Andrew Vittiglio
 * @since 2019-05-24 14:15
 */
public class KeyHitAnimation extends Actor
{
    /** What column it is in */
    private final int column;

    /** Current index of the animated image */
    private int index;
    
    /** Timer */
    private long time = System.currentTimeMillis();

    /**
     * Construct a key hit animation object
     *
     * @param column Column number
     */
    public KeyHitAnimation(int column)
    {
        this.column = column;
        this.index = 0;
        setImage((GreenfootImage) null);
    }

    /**
     * Initialize the key. This method is called in KeypressHandler when
     * the key object is created.
     */
    public void init()
    {
        // Scale image
        int keyLen = Constants.GRAPHIC_TOTAL_LENGTH / Constants.NUM_COLS;
        // getImage().scale(keyLen, Constants.GRAPHIC_NOTE_LANDING);

        // Initialize position
        int x = Constants.GRAPHIC_COL_OFFSET + keyLen * column;
        int y = Constants.GRAPHIC_NOTE_LANDING;
        setLocation(x, y);
    }

    /**
     * Act: Updates the frame
     */
    public void act()
    {
        // Execute every 20 ms
        long current = System.currentTimeMillis();
        if (current - time < 20) return;
        time = current;
        
        
        if (index < Images.KEY_HIT_ANIMATION_FRAMES.length)
        {
            setImage(Images.KEY_HIT_ANIMATION_FRAMES[index]);
            index++;
        }
    }
    
    /**
     * Reset the index.
     */
    public void resetIndex()
    {
        index = 0;
    }
}
