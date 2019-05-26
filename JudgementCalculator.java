/**
 * This class determines the judgement for each key press.
 *
 * @author Team APCSA 2019
 * @author Yijie Gui
 * @since 2019-05-22 21:34
 */
@SuppressWarnings("WeakerAccess")
public class JudgementCalculator
{
    /** Timings, see calculateTimings() method for more info. */
    private final int[] timings;

    /**
     * Construct a Judgement controller.
     *
     * @param beatmap Beatmap
     */
    public JudgementCalculator(Beatmap beatmap)
    {
        timings = calculateTimings(beatmap);
    }

    /**
     * Calculate the value of each hit. A hit cannot miss, only ignored
     * notes are considered missed.
     *
     * @param noteTime The in-game time of the note.
     * @param gameTime The actual hit time of the note.
     * @return Hit value (0 to 4). No notes = -1.
     */
    public int calculateHitValue(int noteTime, int gameTime)
    {
        // How much ms is the player off by?
        int off = Math.abs(noteTime - gameTime);

        // Get the closest timing
        for (int i = 0; i < timings.length; i++)
        {
            if (timings[i] > off)
            {
                return i;
            }
        }

        // There are no notes.
        return -1;
    }

    /**
     * Calculate if a note is missed or not. A note is only missed when
     * it went over the last judgement window.
     *
     * @param noteTime The in-game time of the note.
     * @param gameTime Current in-game time.
     * @return Missed or not.
     */
    public boolean isMissed(int noteTime, int gameTime)
    {
        int late = gameTime - noteTime;

        // Note is earlier than the game time.
        if (late < 0) return false;

        return timings[timings.length - 1] < late;
    }

    /**
     * Calculate the timing values from a beatmap.
     * Calculation Reference:
     * https://www.reddit.com/r/osugame/comments/6phntt/difficulty_settings_table_with_all_values/
     *
     * @param beatmap The beatmap.
     * @return Timings, sorted
     */
    private static int[] calculateTimings(Beatmap beatmap)
    {
        // Get overall difficulty
        double overall = Double.parseDouble(beatmap.getProperties().get("OverallDifficulty"));
        double overallOffset = (overall * 3) + 0.5;

        // Create timings array
        int[] timings = new int[]
        {
                17, // Max
                (int) (64 - overallOffset), // Great
                (int) (97 - overallOffset), // Cool
                (int) (127 - overallOffset), // Good
                (int) (151 - overallOffset) // Bad
                // If larger than Bad, ignore it.
                // If player didn't hit, Poor.
        };

        // Add easy multiplier
        for (int i = 0; i < timings.length; i++)
        {
            timings[i] *= (double) Constants.GAME_EASY_MULTIPLIER;
        }

        return timings;
    }

    // ###################
    // Getters and Setters
    // ###################

    public int[] getTimings()
    {
        return timings;
    }
}
