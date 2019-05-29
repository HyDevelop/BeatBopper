import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import java.io.File;

/**
 * The song difficulty image in Main Menu
 *
 * @author Team APCSA 2019
 * @author Yijie Gui
 * @since 2019-05-28 19:28
 */
public class MainMenuSongDifficulty extends Actor
{
    /** Name of this difficulty */
    private final String name;

    /** The file of this beatmap */
    private final File beatmapFile;

    /** How many difficulties are behind it? */
    private final int index;

    /** The cover containing it */
    private final MainMenuSongCover cover;

    /**
     * Construct a main menu song difficulty button
     * @param name Name of this difficulty
     * @param beatmapFile File of this beatmap
     * @param index How many difficulties are behind it?
     * @param cover The cover containing it
     */
    public MainMenuSongDifficulty(String name, File beatmapFile, int index, MainMenuSongCover cover)
    {
        this.name = name;
        this.beatmapFile = beatmapFile;
        this.index = index;
        this.cover = cover;
    }

    /**
     * Render stuff
     */
    public void init()
    {
        final int count = cover.getDifficulties().size();
        final int totalHeight = Constants.SELECTION_COVER_SIDE_LENGTH - Constants.SELECTION_COVER_TEXT_HEIGHT;
        final double oneHeight = totalHeight / count;
        final int height = (int) Math.round(Constants.SELECTION_DIFF_HEIGHT_RATIO * oneHeight);
        final int spacing = Constants.SELECTION_DIFF_SPACING;
        final int width = Constants.SELECTION_COVER_SIDE_LENGTH - spacing * 2;

        // Create base image
        GreenfootImage image = new GreenfootImage(width, height);
        setImage(image);

        // Draw white overlay
        {
            image.setColor(new Color(255, 255, 255, (int) (255 * 0.75)));
            image.fillRect(0, 0, width, height);
        }

        // Draw text
        {
            // Get text and make an image
            GreenfootImage text = new GreenfootImage(name, 16, Color.BLACK, null);

            // Calculate the x and y that the text is centered
            int x = width / 2 - text.getWidth() / 2;
            int y = height / 2 - text.getHeight() / 2;

            // Draw it
            image.drawImage(text, x, y);
        }

        // Set location
        {
            int halfSide = Constants.SELECTION_COVER_SIDE_LENGTH / 2;
            int x = cover.getX() - halfSide + spacing + image.getWidth() / 2;
            int y = cover.getY() - halfSide + spacing + index * (spacing + height) + image.getHeight() / 2;
            setLocation(x, y);
        }

        // Initialize to not visible.
        show(false);
    }

    /**
     * Change transparency state. (Not transparent or transparent)
     *
     * @param show Show or not.
     */
    public void show(boolean show)
    {
        getImage().setTransparency(show ? 255 : 0);
    }

    /**
     * Act: Launch the beatmap
     */
    @Override
    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            // Error: since the hiding method is using setTransparency(), it
            // means that it is still there when it is hidden, which means if
            // it is clicked, the user meant to click the cover under it.
            if (!cover.isListed()) cover.listDifficulties(true);
            else
            {
                BeatmapWorld world = new BeatmapWorld(BeatmapReader.read(beatmapFile));
                Greenfoot.setWorld(world);
                world.startGame();
                System.gc();
            }
        }
    }

    // ###################
    // Getters and Setters
    // ###################

    public String getName()
    {
        return name;
    }

    public File getBeatmapFile()
    {
        return beatmapFile;
    }

    public int getIndex()
    {
        return index;
    }
}
