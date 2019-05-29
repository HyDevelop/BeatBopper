import greenfoot.Actor;
import greenfoot.GreenfootImage;

/**
 * This actor displays a flash after the key is hit, covering the note landing path.
 * The algorithm should be exactly the same with Key.
 *
 * @author Team APCSA 2019
 * @author Steve Rosario
 * @since 2019-05-24 14:12
 */
@SuppressWarnings("WeakerAccess")
public class KeyFlash extends Actor
{
    /** What column it is in */
    private final int column;

    /** Transparency (0 = fully transparent, 255 = not transparent) */
    private int transparency = 255;

    /** Is pressing or not */
    private boolean isPressing;

    /**
     * Construct a key flash object
     *
     * @param column Column number
     */
    public KeyFlash(int column)
    {
        this.column = column;

        setImage(new GreenfootImage(Images.KEY_FLASH));
    }

    /**
     * Initialize the key. This method is called in KeypressHandler when
     * the key object is created.
     */
    public void init()
    {
        // Scale image
        int keyLen = Constants.GRAPHIC_TOTAL_LENGTH / Constants.NUM_COLS;
        getImage().scale(keyLen, Constants.GRAPHIC_NOTE_LANDING);

        // Initialize position
        int x = Constants.GRAPHIC_COL_OFFSET + keyLen * column;
        int y = Constants.GRAPHIC_NOTE_LANDING / 2;
        setLocation(x, y);
    }

    /**
     * Act: Dim it
     */
    @Override
    public void act()
    {
        // If not pressing, reduce the transparency by Constants.GRAPHIC_KEY_FLASH_SPEED ( > 0)
        if (!isPressing)
        {
            transparency -= Constants.GRAPHIC_KEY_FLASH_SPEED;
            if (transparency < 0) transparency = 0;
        }

        // Set the transparency of the image.
        getImage().setTransparency(transparency);
    }

    /**
     * This method is called after a key is pressed.
     */
    public void press()
    {
        // Reset transparency to not transparent
        transparency = 255;

        // Update state of isPressing
        isPressing = true;
    }

    /**
     * This method is called after a key is released.
     */
    public void release()
    {
        // Update state of isPressing
        isPressing = false;
    }
}
