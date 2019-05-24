/**
 * This class determines the judgement for each key press.
 *
 * @author Team APCSA 2019
 * @author Yijie Gui
 * @since 2019-05-22 21:34
 */
@SuppressWarnings("WeakerAccess")
public class JudgementController
{
    /** Timings, see calculateTimings() method for more info. */
    public final int[] timings;

    /**
     *
     * @param beatmap Beatmap
     */
    public JudgementController(Beatmap beatmap)
    {
        timings = calculateTimings(beatmap);
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
        return new int[]
        {
                17, // Max
                (int) (64 - overallOffset), // Great
                (int) (97 - overallOffset), // Cool
                (int) (127 - overallOffset), // Good
                (int) (151 - overallOffset) // Bad
                // If larger than Bad, ignore it.
                // If player didn't hit, Poor.
        };
    }
}
