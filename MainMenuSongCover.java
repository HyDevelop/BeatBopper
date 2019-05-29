import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * The song cover image button for the MainMenu.
 *
 * @author Team APCSA 2019
 * @author Yijie Gui
 * @since 2019-05-28 18:17
 */
@SuppressWarnings({"WeakerAccess", "ConstantConditions"})
public class MainMenuSongCover extends Actor
{
    /** The directory that this beatmap set is in. */
    private final File beatmapsDir;

    /** How many other beatmap sets are before it? */
    private final int index;

    /** The difficulties of this beatmap-set. (Eg. Easy, Hard, etc.) */
    private final ArrayList<String> difficulties;

    /** The .osu files in this beatmap-set. */
    private final ArrayList<File> beatmapFiles;

    /** The properties of each beatmap */
    private final ArrayList<Map<String, String>> beatmapProperties;

    /** Is the difficulties listed or not */
    private boolean isListed = false;

    /** Difficulty selection buttons */
    private final ArrayList<MainMenuSongDifficulty> difficultyButtons;

    /**
     * Construct a main menu song selection cover
     *
     * @param beatmapsDir Beatmap-set directory
     * @param index How many are there before it?
     */
    public MainMenuSongCover(File beatmapsDir, int index)
    {
        this.beatmapsDir = beatmapsDir;
        this.index = index;

        // Get all the difficulties
        difficulties = BeatmapReader.listDifficulties(beatmapsDir);

        // Get all the beatmap files with the diffs
        beatmapFiles = new ArrayList<>();
        for (String difficulty : difficulties)
        {
            beatmapFiles.add(BeatmapReader.findBeatmapByDifficulty(beatmapsDir, difficulty));
        }

        // Read all the properties.
        beatmapProperties = new ArrayList<>();
        for (File beatmapFile : beatmapFiles)
        {
            beatmapProperties.add(BeatmapReader.readProperties(beatmapFile));
        }

        // Create all the difficulty selection buttons
        difficultyButtons = new ArrayList<>();
    }

    /**
     * Render the song selection cover
     */
    public void init()
    {
        final int sideLen = Constants.SELECTION_COVER_SIDE_LENGTH;
        final int textHeight = Constants.SELECTION_COVER_TEXT_HEIGHT;
        final int minSpacing = Constants.SELECTION_MIN_SPACING;

        // Create base image
        GreenfootImage image = new GreenfootImage(sideLen, sideLen);
        setImage(image);

        // Draw the beatmap's wallpaper on the cover.
        {
            // Get wallpaper image
            List<String> fileNameList = Arrays.asList(beatmapsDir.list());
            String backgroundSuffix = fileNameList.contains("background.jpg") ? "jpg" : "png";
            GreenfootImage wallpaper = new GreenfootImage("../beatmaps/" +
                    beatmapsDir.getName() + "/background." + backgroundSuffix);

            // Scale it.
            int origWidth = wallpaper.getWidth();
            int origHeight = wallpaper.getHeight();
            wallpaper.scale((int) (1.0 * origWidth / origHeight * sideLen), sideLen);

            // Draw it
            image.drawImage(wallpaper, 0, 0);
        }

        // Darken the text zone
        {
            image.setColor(new Color(0, 0, 0, (int) (255.0 * 0.75)));
            image.fillRect(0, sideLen - textHeight, sideLen, textHeight);
        }

        // Draw text
        {
            // Get text and make an image
            String songName = beatmapProperties.get(0).get("Title");
            GreenfootImage text = new GreenfootImage(songName, 16, Color.WHITE, null);

            // Calculate the x and y that the text is centered
            int x = sideLen / 2 - text.getWidth() / 2;
            int y = (sideLen - textHeight) + textHeight / 2 - text.getHeight() / 2;

            // Draw it
            image.drawImage(text, x, y);
        }

        // Set location
        {
            // Get row and col. Simple APCSA stuff
            int row = index / Constants.SELECTION_ROWS;
            int col = index % Constants.SELECTION_ROWS;

            // Calculate position
            int x = minSpacing + col * (sideLen + minSpacing) + sideLen / 2;
            int y = Constants.SELECTION_TOP_PADDING + minSpacing + row * (sideLen + minSpacing) + sideLen / 2;

            // Set position
            setLocation(x, y);
        }

        // Init difficulty selection buttons
        for (int i = 0; i < difficulties.size(); i++)
        {
            difficultyButtons.add(new MainMenuSongDifficulty(difficulties.get(i), beatmapFiles.get(i), i, this));
            getWorld().addObject(difficultyButtons.get(i), 0, 0);
            difficultyButtons.get(i).init();
        }
    }

    /**
     * Act: Process clicks: Show / Hide the difficulties.
     */
    @Override
    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            listDifficulties(!isListed);
        }
    }

    /**
     * List all the difficulties
     *
     * @param list List or not.
     */
    public void listDifficulties(boolean list)
    {
        isListed = list;
        difficultyButtons.forEach(button -> button.show(list));
    }

    // ###################
    // Getters and Setters
    // ###################

    public File getBeatmapsDir()
    {
        return beatmapsDir;
    }

    public int getIndex()
    {
        return index;
    }

    public ArrayList<String> getDifficulties()
    {
        return difficulties;
    }

    public ArrayList<File> getBeatmapFiles()
    {
        return beatmapFiles;
    }

    public ArrayList<Map<String, String>> getBeatmapProperties()
    {
        return beatmapProperties;
    }

    public boolean isListed()
    {
        return isListed;
    }

    public ArrayList<MainMenuSongDifficulty> getDifficultyButtons()
    {
        return difficultyButtons;
    }
}
