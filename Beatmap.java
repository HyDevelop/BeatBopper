import java.util.ArrayList;
import greenfoot.*;

/**
 * A beatmap defines the arrangement of the notes in a song.
 * 
 * @author Team APCSA 2019
 * @version 2019-05-21
 */
public class Beatmap  
{
    /**
     * This is a list of arraylists representing the notes in each column.
     * Eg. First column: noteColumns[0];
     * Eg. First note in first column: noteColumns[0].get(0);
     */
    private ArrayList<Note>[] noteColumns = new ArrayList[4];

    /** Music */
    private GreenfootSound music;
    
    /**
     * Constructor for objects of class Beatmap
     */
    public Beatmap()
    {
    }
}
