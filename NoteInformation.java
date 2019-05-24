/**
 * The objects of NoteInformation contains the information about a note.
 * This is used to store in a beatmap, since storing actors would be too
 * memory consuming.
 *
 * @author Team APCSA 2019
 * @author Yijie Gui
 * @since 2019-05-24 17:27
 */
public class NoteInformation
{
    /** Time is an integral number of milliseconds from the beginning
     * of the song, specifying when to hit. */
    private final int time;

    /** Which column is it on */
    private final int column;

    /**
     * Construct a note information.
     *
     * @param time Hit time in ms.
     * @param column Column number.
     */
    public NoteInformation(int time, int column)
    {
        this.time = time;
        this.column = column;
    }

    public int getTime()
    {
        return this.time;
    }

    public int getColumn()
    {
        return this.column;
    }
}
