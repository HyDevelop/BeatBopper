import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

/**
 * A start button on the BeatmapWorld
 * TODO: This will be unnecessary once MainMenu is done.
 *
 * @author Team APCSA 2019
 * @author Yijie Gui
 * @since 2019-05-26 09:13
 */
@Deprecated
public class StartButton extends Actor
{
    public StartButton()
    {
        GreenfootImage button = new GreenfootImage(200, 50);
        button.setColor(Color.WHITE);
        button.fillRect(0, 0, button.getWidth(), button.getHeight());
        button.setColor(Color.BLACK);
        button.drawString("CLICK TO START GAME", 40, button.getHeight() / 2);
        setImage(button);
    }

    @Override
    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            ((BeatmapWorld) getWorld()).startGame();
            getWorld().removeObject(this);
        }
    }
}
