import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

/**
 * KeypressHandler handles key presses and register them to different
 * components when needed.
 *
 * @author Team APCSA 2019
 * @author Yijie Gui
 * @version 2019-05-21
 */
@SuppressWarnings("WeakerAccess")
public class KeypressHandler extends Actor
{
    /** This should contain the key visualizers, in a row */
    private final Key[] keys;

    /** Key flashes, in a row */
    private final KeyFlash[] keyFlashes;

    /**
     * Constructor for objects of class KeypressHandler
     */
    public KeypressHandler()
    {
        // Create key array
        keys = new Key[Constants.NUM_COLS];

        // Create key flashes array
        keyFlashes = new KeyFlash[Constants.NUM_COLS];

        // This actor does not need to have image
        setImage((GreenfootImage) null);
    }

    /**
     * Initialize keypress handler.
     */
    public void init()
    {
        // Create keys and initialize
        for (int i = 0; i < keys.length; i++)
        {
            getWorld().addObject(keys[i] = new Key(i, Constants.KEYS[i]), 0, 0);
            keys[i].init();
            getWorld().addObject(keyFlashes[i] = new KeyFlash(i), 0, 0);
            keyFlashes[i].init();
        }
    }

    /**
     * Act: Detect key down for all the key buttons and call
     * registerKeyPress() if the key is pressed. It basically converts
     * Greenfoot.isKeyDown(), which returns a boolean value representing
     * if the key is down, to method calls when keys are pressed or
     * released.
     */
    public void act()
    {
        // Update key status
        for (Key key : keys)
        {
            boolean keyDown = Greenfoot.isKeyDown(key.getKeyboardButton());
            boolean lastDown = key.isPressed();

            // Key turned from up to down
            if (keyDown && !lastDown)
            {
                key.setPressed(true);
                registerKeyPress(key);
            }

            // Key turned from down to up
            if (!keyDown && lastDown)
            {
                key.setPressed(false);
                registerKeyRelease(key);
            }
        }
    }

    /**
     * Register a key press.
     *
     * @param key The key.
     */
    private void registerKeyPress(Key key)
    {
        ((BeatmapWorld) getWorld()).getBeatmapController().hit(key.getColumn());
        keyFlashes[key.getColumn()].press();
    }

    /**
     * Register a key release.
     *
     * @param key The key.
     */
    private void registerKeyRelease(Key key)
    {
        keyFlashes[key.getColumn()].release();
    }
}
