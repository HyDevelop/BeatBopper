import greenfoot.Actor;

/**
 * A key actor visualizes key presses.
 *
 * TODO: Display splash (Also fade out)
 * 
 * @author Team APCSA 2019
 * @author Russell Doucet
 * @author Maanik George
 * @author Yijie Gui
 * @version 2019-05-21
 */
@SuppressWarnings("WeakerAccess")
public class Key extends Actor
{
    /** Pressed or not */
    private boolean pressed;

    /** What column it is in */
    private final int column;

    /** Which button correspond to this key */
    private final String keyboardButton;

    /**
     * Construct a Key object with column
     *
     * @param column The column number
     * @param keyboardButton Button (Eg. "A")
     */
    public Key(int column, String keyboardButton)
    {
        this.pressed = false;
        this.column = column;
        this.keyboardButton = keyboardButton;
    }

    /**
     * Initialize the key. This method is called in KeypressHandler when
     * the key object is created.
     */
    public void init()
    {
        // Initialize to not pressed, but need to initialize for both image.
        setPressed(true);
        initPositionAndScale();
        setPressed(false);
        initPositionAndScale();
    }

    /**
     * Helper method for init() that initializes the position.
     */
    private void initPositionAndScale()
    {
        // Scale image
        int keyLen = Constants.GRAPHIC_TOTAL_LENGTH / Constants.NUM_COLS;
        getImage().scale(keyLen, Constants.GRAPHIC_KEY_HEIGHT);

        // Initialize position
        int x = Constants.GRAPHIC_COL_OFFSET + keyLen * column;
        int y = getWorld().getHeight() - (getImage().getHeight() / 2) - Constants.GRAPHIC_KEY_K;
        setLocation(x, y);
    }

    /**
     * Update pressed state and it's image.
     *
     * @param pressed Pressed or not.
     */
    public void setPressed(boolean pressed)
    {
        this.pressed = pressed;

        // Set image based on pressed or not.
        if (!pressed)
        {
            setImage(Images.KEYS[column]);
        }
        else
        {
            setImage(Images.KEYS_PRESSED[column]);
        }
    }

    // ###################
    // Getters and Setters
    // ###################

    public boolean isPressed()
    {
        return pressed;
    }

    public int getColumn()
    {
        return column;
    }

    public String getKeyboardButton()
    {
        return keyboardButton;
    }
}
