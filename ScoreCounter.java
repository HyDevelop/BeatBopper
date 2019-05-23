import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

/**
 * A Counter class that allows you to display a numerical value on screen.
 * 
 * The Counter is an actor, so you will need to create it, and then add it to
 * the world in Greenfoot.  If you keep a reference to the Counter then you
 * can adjust its value.
 * 
 * @author Team APCSA 2019
 * @author Andy Ge
 * @version 2019-05-22
 */
public class ScoreCounter extends Actor
{
    private static final Color TRANSPARENT = new Color(0,0,0,0);
    private GreenfootImage background;
    private double value;
    private String prefix;
    
    public ScoreCounter()
    {
        this("");
    }
   
    /**
     * Create a new counter, initialised to 0.
     */
    public ScoreCounter(String prefix)
    {
        background = Images.COUNTER; // Sets counter.
        value = 0;
        this.prefix = prefix;
        updateImage();
    }
    
    /**
     * Animate the display to count up or down
     */
    public void act()
    {
        /**
        if(CorrectNotePlayed)
        {
            add(100);
            updateImage();
        }
        else if(MistakeIsMade)
        {
            add(-100);
            updateImage();
        }
        */
    }
    
    /**
     * Add a new score to the current counter value.  This will animate
     * the counter over consecutive frames until it reaches the new value.
     */
    public void add(double score)
    {
        value += score; 
    }
  
    /**
     * Update the image on screen to show the current value.
     */
    private void updateImage()
    {
        GreenfootImage image = new GreenfootImage(background);
        GreenfootImage text = new GreenfootImage(prefix + 
            (int) value, 22, Color.BLACK, TRANSPARENT);
        
        if (text.getWidth() > image.getWidth() - 20)
        {
            image.scale(text.getWidth() + 20, image.getHeight());
        }
        
        image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
        (image.getHeight()-text.getHeight())/2);
        setImage(image);
    }
    
}
