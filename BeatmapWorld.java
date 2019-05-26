import greenfoot.GreenfootImage;
import greenfoot.World;

import java.io.File;

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
@SuppressWarnings({"ConstantConditions", "WeakerAccess"})
public class BeatmapWorld extends World
{
    /** The keypress handler */
    private final KeypressHandler keypressHandler;
    
    /** Initializes a new counter */ 
    private final ScoreCounter scoreCounter;

    /** Beatmap */
    private final Beatmap beatmap;

    /** Beatmap Controller */
    private final BeatmapController beatmapController;

    /**
     * Start a game with specific beatmap, specified by id and difficulty.
     *
     * @param beatmapId Beatmap ID (The number before the folder name)
     * @param difficulty Difficulty name
     */
    public BeatmapWorld(String beatmapId, String difficulty)
    {
        this(BeatmapReader.findBeatmapSetById(beatmapId), difficulty);
    }

    /**
     * Start a game with specific beatmap, specified by beatmap set
     * directory and difficulty.
     *
     * @param beatmapSet Beatmap set directory.
     * @param difficulty Difficulty name.
     */
    public BeatmapWorld(File beatmapSet, String difficulty)
    {
        this(BeatmapReader.read(BeatmapReader.findBeatmapByDifficulty(beatmapSet, difficulty)));
    }

    /**
     * Start a game with a specific beatmap.
     *
     * @param beatmap Beatmap object
     */
    public BeatmapWorld(Beatmap beatmap)
    {
        // Set resolution
        super(Constants.WIDTH, Constants.HEIGHT, 1);

        this.beatmap = beatmap;

        // Creates a new counter and adds it to the world
        scoreCounter = new ScoreCounter("Score: ", beatmap);
        addObject(scoreCounter, Constants.WIDTH - 100, 20);

        // Initialize keypress handler
        addObject(keypressHandler = new KeypressHandler(), 0, 0);
        keypressHandler.init();

        // Initialize beatmap controller
        addObject(beatmapController = new BeatmapController(beatmap, scoreCounter), 0, 0);

        // Draw background
        drawBackground();

        // Create a start button
        // TODO: Implement song selection menu
        addObject(new StartButton(), 800, 200);
    }

    /**
     * Start the game.
     */
    public void startGame()
    {
        beatmapController.start();
    }

    /**
     * Draw the wallpaper and stage images and stuff.
     */
    private void drawBackground()
    {
        // Show wallpaper
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

        // Darken hit columns
        {
            //TODO
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
    }

    // ###################
    // Getters and Setters
    // ###################

    public KeypressHandler getKeypressHandler()
    {
        return keypressHandler;
    }
    
    public ScoreCounter getScoreCounter()
    {
        return scoreCounter;
    }

    public Beatmap getBeatmap()
    {
        return beatmap;
    }

    public BeatmapController getBeatmapController()
    {
        return beatmapController;
    }
}
