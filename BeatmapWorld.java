import greenfoot.GreenfootImage;
import greenfoot.World;

/**
 * This is the gameplay stage.
 * 
 * @author Team APCSA 2019
 * @author Andy Ge
 * @author Andrew Vittiglio
 * @author Steve Rosario
 * @author Yijie Gui
 * @author Russell Doucet
 * @author William LoGiudice
 * @author Maanik George
 * @author Mr. Gilmore (Teacher)
 * @version 2019-05-21
 */
public class BeatmapWorld extends World
{

    /** The keypress handler */
    private KeypressHandler keypressHandler;

    /** It has to know it's column to decide which image to use */
    private int column;

    /**
     * Construct and initialize the world.
     */
    public BeatmapWorld()
    {    
        // Set resolution
        super(Constants.WIDTH, Constants.HEIGHT, 1);

        // Show wallpaper
        // TODO: Find a better wallpaper
        {
            // Simple proportions calculations right?
            int origWidth = Images.WALLPAPER.getWidth();
            int origHeight = Images.WALLPAPER.getHeight();
            Images.WALLPAPER.scale((int) (1.0 * origWidth / origHeight * Constants.HEIGHT), Constants.HEIGHT);
            getBackground().drawImage(Images.WALLPAPER, 0, 0);
        }

        // Darken wallpaper with a black overlay
        {
            Images.BLACK.scale(Constants.WIDTH, Constants.HEIGHT);
            Images.BLACK.setTransparency(Constants.GRAPHIC_WALLPAPER_DARKEN);
            getBackground().drawImage(Images.BLACK, 0, 0);
        }

        // Show stage images
        {
            GreenfootImage left = Images.STAGE_LEFT;
            GreenfootImage right = Images.STAGE_RIGHT;

            // Scale height
            left.scale(left.getWidth(), Constants.HEIGHT);
            right.scale(right.getWidth(), Constants.HEIGHT);

            // Calculate x and draw image
            int x = Constants.GRAPHIC_COL_OFFSET - Constants.GRAPHIC_TOTAL_LENGTH / Constants.KEYS.length / 2;
            getBackground().drawImage(left, x - left.getWidth(), 0);
            getBackground().drawImage(right, x + Constants.GRAPHIC_TOTAL_LENGTH, 0);
        }

        // TODO: Add

        // Initialize keypress handler
        addObject(keypressHandler = new KeypressHandler(), 0, 0);
        keypressHandler.init();

        // TODO: Show stage right and left images.
    }

    // ###################
    // Getters and Setters
    // ###################

    public KeypressHandler getKeypressHandler()
    {
        return keypressHandler;
    }
}
