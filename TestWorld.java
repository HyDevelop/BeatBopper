import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TestWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestWorld extends World
{

    /**
     * Constructor for objects of class TestWorld.
     * 
     */
    public TestWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(Constants.WIDTH, Constants.HEIGHT, 1);
        
        getBackground().setColor(Color.BLACK);
        //getBackground().fill();
        
        /* Testing number displayer
        NumberDisplayer nd = NumberDisplayer.getTestInstance();
        addObject(nd, 0, 0);
        nd.init(Constants.WIDTH / 2, 0);
        
        nd.drawNumber(10923.23581, 3);*/

        // Show stage images (This is used to test alignment)
        {
            GreenfootImage left = Images.STAGE_LEFT;
            GreenfootImage right = Images.STAGE_RIGHT;

            // Scale height
            left.scale(left.getWidth(), Constants.HEIGHT);
            right.scale(right.getWidth(), Constants.HEIGHT);

            // Calculate x and draw image
            int x = Constants.GRAPHIC_COL_OFFSET - Constants.GRAPHIC_TOTAL_LENGTH / Constants.NUM_COLS / 2;
            getBackground().drawImage(left, x - left.getWidth(), 0);
            getBackground().drawImage(right, x + Constants.GRAPHIC_TOTAL_LENGTH, 0);
        }
        
        /* Testing ScoreReport
        try
        {
            ScoreCounter counter = new Gson().fromJson(new FileReader("./score-counter-object-2019-05-29.json"), ScoreCounter.class);
            ScoreReport sr = new ScoreReport(counter);
            addObject(sr, Constants.WIDTH / 2, Constants.HEIGHT / 2);
            sr.draw();
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }*/
    }
}
