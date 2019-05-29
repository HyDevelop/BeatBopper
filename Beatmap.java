import greenfoot.sound.Sound;

import java.io.File;
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
     * This is a list of array lists representing the notes in each column.
     * Eg. First column: noteColumns[0];
     * Eg. First note in first column: noteColumns[0].get(0);
     *
     * Past: Notes that the player already hit.
     * Present: Notes that are on the screen.
     * Future: Notes that are not yet spawned.
     *
     * Order: The earliest notes are at the front of the array.
     */
    private ArrayList<NoteInformation>[] future;
    private ArrayList<Note>[] present;
    private ArrayList<NoteInformation>[] past;
    
    /** Music */
    private Sound music;

    /** Properties */
    private Map<String, String> properties;

    /** Beatmap ID */
    private int id;

    /** Beatmap File (.osu) */
    private File file;
    
    /**
     * Constructor for objects of class Beatmap
     */
    public Beatmap()
    {
        // Initialize array lists.
        future = new ArrayList[Constants.KEYS.length];
        present = new ArrayList[Constants.KEYS.length];
        past = new ArrayList[Constants.KEYS.length];

        for (int i = 0; i < Constants.KEYS.length; i++)
        {
            future[i] = new ArrayList<>();
            present[i] = new ArrayList<>();
            past[i] = new ArrayList<>();
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
            total += past[i].size() + present[i].size() + future[i].size();
        }
        return total;
    }

    // ###################
    // Getters and Setters
    // ###################

    public NoteInformation getFirstInLine(int col)
    {
        return getPast(col).get(0);
    }

    public ArrayList<NoteInformation> getPast(int col)
    {
        return past[col];
    }

    public ArrayList<NoteInformation>[] getPast()
    {
        return past;
    }

    public ArrayList<Note> getPresent(int col)
    {
        return present[col];
    }

    public ArrayList<Note>[] getPresent()
    {
        return present;
    }

    public ArrayList<NoteInformation> getFuture(int col)
    {
        return future[col];
    }

    public ArrayList<NoteInformation>[] getFuture()
    {
        return future;
    }

    public Sound getMusic()
    {
        return music;
    }

    public void setMusic(Sound music)
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

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public File getFile()
    {
        return file;
    }

    public void setFile(File file)
    {
        this.file = file;
    }
}
