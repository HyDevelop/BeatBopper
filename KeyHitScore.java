import greenfoot.Actor;
import greenfoot.GreenfootImage;

/**
 * This class display the hit value after a hit. Eg. Great, Cool, Good, Bad, etc.
 *
 * @author Team APCSA 2019
 * @author Andy Ge
 * @since 2019-05-25 19:52
 */
@SuppressWarnings("WeakerAccess")
public class KeyHitScore extends Actor
{
    /** Transparency (0 = fully transparent, 255 = not transparent) */
    private int transparency = 255;

    /**
     * Create object and initialize to no image
     */
    public KeyHitScore()
    {
        setImage(new GreenfootImage(1, 1));
    }

    /**
     * Initialize position. This method is called in KeypressHandler when
     * the key score object is created.
     */
    public void init()
    {
        // Initialize position
        int keyLen = Constants.GRAPHIC_TOTAL_LENGTH / Constants.NUM_COLS;
        int x = Constants.GRAPHIC_COL_OFFSET + Constants.GRAPHIC_TOTAL_LENGTH / 2 - keyLen / 2;
        int y = (int) Math.round(Constants.GRAPHIC_NOTE_LANDING * (2.0 / 3.0));
        setLocation(x, y);
    }

    /**
     * Act: Dim it
     */
    @Override
    public void act()
    {
        // Reduce the transparency by Constants.GRAPHIC_KEY_HIT_SCORE_SPEED ( > 0)
        transparency -= Constants.GRAPHIC_KEY_HIT_SCORE_SPEED;
        if (transparency < 0) transparency = 0;

        // Set the transparency of the image to the value in the transparency variable.
        getImage().setTransparency(transparency);
    }

    /**
     * This method is called when the player hit a note.
     *
     * @param hitScore Hit score (0 to 5)
     */
    public void hit(int hitScore)
    {
        // Set the image to the corresponding hit image (Images.KEY_HIT_SCORE[hitScore])
        setImage(Images.KEY_HIT_SCORE[hitScore]);
        
        // Reset the transparency variable to not transparent
        transparency = 255;
    }
}
