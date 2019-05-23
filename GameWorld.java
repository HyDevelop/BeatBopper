import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
public class GameWorld extends World
{

    /** The keypress handler */
    private KeypressHandler keypressHandler;

    /** It has to know it's column to decide which image to use */
    private int column;

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public GameWorld()
    {    
        // Set resolution
        super(600, 800, 1);

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
