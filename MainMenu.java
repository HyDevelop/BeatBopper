import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;

import java.io.File;
import java.util.ArrayList;

/**
 * Main song selection menu.
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
 * @version 2019-05-24 20:37
 */
public class MainMenu extends World
{
    /**
     * Constructor for objects of class MainMenu.
     */
    public MainMenu()
    {
        super(Constants.WIDTH, Constants.HEIGHT, 1);

        // Get the beatmap sets.
        ArrayList<File> beatmapSets = BeatmapReader.listBeatmapSets();

        // Draw the background
        drawBackground();

        // Put song selection buttons in.
        putSongSelectionButtons(beatmapSets);
    }

    /**
     * Draw the background
     */
    private void drawBackground()
    {
        // Draw wallpaper
        {
            GreenfootImage wallpaper = new GreenfootImage(Images.WALLPAPER);
            int origWidth = wallpaper.getWidth();
            int origHeight = wallpaper.getHeight();
            wallpaper.scale((int) (1.0 * origWidth / origHeight * Constants.HEIGHT), Constants.HEIGHT);
            getBackground().drawImage(wallpaper, 0, 0);
        }

        // Darken wallpaper with a black overlay
        {
            Images.BLACK.scale(Constants.WIDTH, Constants.HEIGHT);
            Images.BLACK.setTransparency(Constants.GRAPHIC_WALLPAPER_DARKEN);
            getBackground().drawImage(Images.BLACK, 0, 0);
        }

        // Draw "Song Select"
        {
            // Get text and make an image
            GreenfootImage text = new GreenfootImage("Song Select", 20, Color.WHITE, null);

            // Calculate the x and y that the text is centered within a separation space.
            int x = Constants.WIDTH / 2 - text.getWidth() / 2;
            int y = (Constants.SELECTION_TOP_PADDING + Constants.SELECTION_MIN_SPACING) / 2 - text.getHeight() / 2;

            // Draw it
            getBackground().drawImage(text, x, y);
        }
    }

    /**
     * Put the song selection buttons, MainMenuSongCover, in the world.
     */
    private void putSongSelectionButtons(ArrayList<File> beatmapSets)
    {
        for (int i = 0; i < beatmapSets.size(); i++)
        {
            File beatmapSetDirectory = beatmapSets.get(i);
            MainMenuSongCover songSelect = new MainMenuSongCover(beatmapSetDirectory, i);
            addObject(songSelect, 0, 0);
            songSelect.init();
        }
    }
}
