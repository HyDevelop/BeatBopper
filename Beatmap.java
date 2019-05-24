import greenfoot.GreenfootSound;

import java.util.ArrayList;
import java.util.Map;

/**
 * A beatmap defines the arrangement of the notes in a song.
 * 
 * @author Team APCSA 2019
 * @author Yijie Gui
 * @version 2019-05-21
 */
@SuppressWarnings({"unchecked", "WeakerAccess"})
public class Beatmap
{
    /**
     * This is a list of arraylists representing the notes in each column.
     * Eg. First column: noteColumns[0];
     * Eg. First note in first column: noteColumns[0].get(0);
     */
    private ArrayList<NoteInformation>[] pressed;
    private ArrayList<NoteInformation>[] notPressed;
    
    /** Music */
    private GreenfootSound music;

    /** Properties */
    private Map<String, String> properties;
    
    /**
     * Constructor for objects of class Beatmap
     */
    public Beatmap()
    {
        // Initialize array lists.
        pressed = new ArrayList[Constants.KEYS.length];
        notPressed = new ArrayList[Constants.KEYS.length];

        for (int i = 0; i < Constants.KEYS.length; i++)
        {
            pressed[i] = new ArrayList<>();
            notPressed[i] = new ArrayList<>();
        }
    }

    /**
     * Count the total number of objects.
     *
     * @return Total number of objects.
     */
    public int countTotalObjects()
    {
        int total = 0;
        for (int i = 0; i < Constants.KEYS.length; i++)
        {
            total += pressed[i].size() + notPressed[i].size();
        }
        return total;
    }

    // ###################
    // Getters and Setters
    // ###################

    public NoteInformation getFirstInLine(int col)
    {
        return getPressed(col).get(0);
    }

    public ArrayList<NoteInformation> getPressed(int col)
    {
        return pressed[col];
    }
    
    public ArrayList<NoteInformation> getNotPressed(int col)
    {
        return notPressed[col];
    }

    public GreenfootSound getMusic()
    {
        return music;
    }

    public void setMusic(GreenfootSound music)
    {
        this.music = music;
    }

    public Map<String, String> getProperties()
    {
        return properties;
    }

    public void setProperties(Map<String, String> properties)
    {
        this.properties = properties;
    }
}
