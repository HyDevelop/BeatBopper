import greenfoot.Actor;
import greenfoot.GreenfootImage;

/**
 * A BeatmapController is an actor that controls the beatmap, spawns the
 * notes in the correct timing, etc.
 *
 * @author Team APCSA 2019
 * @author Yijie Gui
 * @since 2019-05-25 06:37
 */
public class BeatmapController extends Actor
{
    /** Timing controller. */
    private TimingController timer;

    /**
     * Create a beatmap controller.
     */
    public BeatmapController()
    {
        timer = new TimingController();
        setImage((GreenfootImage) null);
    }

    /**
     * Act: Detect the notes that are about to fall down, and spawn them.
     */
    public void act()
    {
        long gameTime = timer.getTotalDuration();
        Beatmap beatmap = ((BeatmapWorld) getWorld()).getBeatmap();

        for (int i = 0; i < Constants.KEYS.length; i++)
        {
            for (NoteInformation noteInfo : beatmap.getNotPressed(i))
            {
                // Within the speed range.
                if (noteInfo.getTime() - gameTime > Constants.GAME_SPEED_MS)
                {
                    break;
                }

                // Spawn the note to the top.
                spawnNote(noteInfo, gameTime);
            }
        }
    }

    /**
     * Spawn a note.
     *
     * @param noteInfo Information of the note.
     */
    private void spawnNote(NoteInformation noteInfo, long time)
    {
        Note note = new Note(time, noteInfo.getTime(), noteInfo.getColumn());
        getWorld().addObject(note, 0, 0);
        note.init();
    }

    // ###################
    // Getters and Setters
    // ###################

    public TimingController getTimer()
    {
        return timer;
    }
}
