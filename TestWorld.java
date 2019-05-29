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
        getBackground().fill();
        
        NumberDisplayer nd = NumberDisplayer.getTestInstance();
        addObject(nd, 0, 0);
        nd.init(Constants.WIDTH / 2, 0);
        
        nd.drawNumber(10923.23581, 3);
    }
}
