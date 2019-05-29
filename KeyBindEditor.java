import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

/**
 * Asks User for input, and updates key-binds (Determines which keys to
 * use for the four keys)
 *
 * @author Team APCSA 2019
 * @author Will LoGiudice
 * @since 2019-05-29
 */
public class KeyBindEditor extends Actor
{
    /** Total spacing */
    private static final int TOTAL_SPACING = Constants.SELECTION_MIN_SPACING * (Constants.NUM_COLS + 1);

    /** Width */
    private static final int WIDTH = (int) ((double) (Constants.WIDTH - TOTAL_SPACING) / Constants.NUM_COLS);

    /** Height */
    private static final int HEIGHT = 30;

    /** Transparency of the background colors */
    private static final int TRANSPARENCY = (int) (255 * 0.75);

    /** Which one is currently being focused on? */
    private static KeyBindEditor selected;

    /** The column it is setting for. */
    private final int column;

    /**
     * Construct a new key bind editor object
     *
     * @param column The column that it is setting for.
     */
    public KeyBindEditor(int column)
    {
        this.column = column;
    }

    /**
     * Initialize the image and position
     */
    public void init()
    {
        // Calculate location
        double thisSpacing = Constants.SELECTION_MIN_SPACING * (column + 1);
        double x = thisSpacing + WIDTH / 2.0 + WIDTH * column;
        int y = Constants.HEIGHT - 40;

        // Set location
        setLocation((int) x, y);

        // Update image
        updateImage();
    }

    /**
     * Update Image
     */
    private void updateImage()
    {
        // Create image
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);

        // Fill background (Blue if selected, white if not.)
        image.setColor(selected == this ? new Color(132, 255, 255) : Color.WHITE);
        image.setTransparency(TRANSPARENCY);
        image.fill();

        // Fill text
        GreenfootImage textImage = new GreenfootImage(getText(), 24, null, null);
        image.drawImage(textImage, (image.getWidth() - textImage.getWidth()) / 2, 15 - textImage.getHeight() / 2);
        setImage(image);
    }

    /**
     * Act: Process mouse and keyboard clicks: Takes in what user types
     * and displays it back and updates key-bind.
     */
    public void act()
    {
        // When this is selected.
        if (selected == this)
        {
            // Click anywhere else: Unselect this.
            if (Greenfoot.mouseClicked(null) && !Greenfoot.mouseClicked(this))
            {
                select(false);
                return;
            }

            // Get current key.
            String key = Greenfoot.getKey();

            // No key
            if (key == null) return;

            // Escape: Unselect this
            if ("escape".equals(key))
            {
                select(false);
                return;
            }

            // A key: set that key to the key bind.
            if (key.length() == 1)
            {
                Constants.KEYS[column] = key.toUpperCase();
                select(false);
            }
        }
        else
        {
            // Click: set this to selected.
            if (Greenfoot.mouseClicked(this))
            {
                select(true);
            }
        }
    }

    /**
     * Generate text.
     *
     * @return Text showing on the button.
     */
    private String getText()
    {
        if (selected == this) return "Please enter Key " + (column + 1) + ":";
        return "Key " + (column + 1) + ": " + Constants.KEYS[column];
    }

    /**
     * Update select state.
     *
     * @param select Selected or not.
     */
    private void select(boolean select)
    {
        if (select)
        {
            selected = this;
        }
        else
        {
            selected = null;
        }

        updateImage();
    }
}
