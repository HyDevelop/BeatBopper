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
            // Add image object
            ImageDisplayer wallpaper = new ImageDisplayer(Images.WALLPAPER);
            addObject(wallpaper, Constants.WIDTH / 2, Constants.HEIGHT / 2);

            // Simple proportions calculations right?
            int origWidth = wallpaper.getImage().getWidth();
            int origHeight = wallpaper.getImage().getHeight();
            wallpaper.getImage().scale((int) (1.0 * origWidth / origHeight * Constants.HEIGHT), Constants.HEIGHT);
        }

        // Darken wallpaper with a black overlay
        {
            ImageDisplayer black = new ImageDisplayer(Images.BLACK);
            black.getImage().scale(Constants.WIDTH, Constants.HEIGHT);
            black.getImage().setTransparency(Constants.GRAPHIC_WALLPAPER_DARKEN);
            addObject(black, Constants.WIDTH / 2, Constants.HEIGHT / 2);
        }

        // Show stage images
        {
            ImageDisplayer left = new ImageDisplayer(Images.STAGE_LEFT);
            ImageDisplayer right = new ImageDisplayer(Images.STAGE_RIGHT);

            // Calculate x and y, and set them.
            int y = Constants.HEIGHT / 2;
            int x = Constants.GRAPHIC_COL_OFFSET - Constants.GRAPHIC_TOTAL_LENGTH / Constants.KEYS.length / 2;
            int xLeft = x - left.getImage().getWidth() / 2;
            int xRight = x + left.getImage().getWidth() / 2 + Constants.GRAPHIC_TOTAL_LENGTH;
            addObject(left, xLeft, y);
            addObject(right, xRight, y);

            // Scale height
            left.getImage().scale(left.getImage().getWidth(), Constants.HEIGHT);
            right.getImage().scale(right.getImage().getWidth(), Constants.HEIGHT);
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
