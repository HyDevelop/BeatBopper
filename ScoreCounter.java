import greenfoot.World;

/**
 * This score counter class keeps the scores. It contains ScoreDisplayer
 * that displays the score on the screen.
 * 
 * @author Team APCSA 2019
 * @author Andy Ge
 * @author Yijie Gui
 * @version 2019-05-22
 */
@SuppressWarnings("WeakerAccess")
public class ScoreCounter
{
    /** Beatmap */
    private final Beatmap beatmap;

    /**
     * This array stores the count of all the hit scores:
     * scores[0]: Max
     * scores[1]: Great
     * scores[2]: Cool
     * scores[3]: Good
     * scores[4]: Bad
     * scores[5]: Poor
     * Timings for each score see JudgementController.calculateTimings().
     */
    private final int[] scores = new int[6];

    /**
     * This array stores scores in the hit order.
     * Eg. [0, 0, 1, 0, 0, 0, 2, 1, 0, 0, 5, 3, 0, ...]
     */
    private final int[] scoresHitOrder;

    /**
     * Half note ratio.
     * See ScoreCalculator.calculateHalfNoteRatio() for more details.
     */
    private final double halfNoteRatio;

    /** The index of the current note (Live updating) */
    private int noteIndex = 0;

    /** Total score (Live updating) */
    private double totalScore = 0;

    /** Bonus (Live updating) */
    private double bonus = 100;

    /** Combo (Number of continuous hits) (Live updating) */
    private int combo = 0;

    /** Maximum combo (Live updating) */
    private int maxCombo = 0;

    // Used transient because I used GSON for testing, and it throws an exception when serializing images.
    /** Accuracy displayer */
    private transient final ScoreDisplayerAccuracy accuracyDisplayer;

    /** Bonus displayer */
    private transient final ScoreDisplayerBonus bonusDisplayer;

    /** Total Score Displayer */
    private transient final ScoreDisplayerTotal totalDisplayer;

    /** Combo Displayer */
    private transient final ScoreDisplayerCombo comboDisplayer;

    /**
     * Create a new counter, initialised to 0.
     *
     * @param beatmap The beatmap
     */
    public ScoreCounter(Beatmap beatmap)
    {
        this.beatmap = beatmap;
        this.scoresHitOrder = new int[beatmap.countTotalObjects()];
        this.halfNoteRatio = ScoreCalculator.calculateHalfNoteRatio(beatmap.countTotalObjects());

        // Create displayers
        accuracyDisplayer = new ScoreDisplayerAccuracy();
        bonusDisplayer = new ScoreDisplayerBonus();
        totalDisplayer = new ScoreDisplayerTotal();
        comboDisplayer = new ScoreDisplayerCombo();
    }

    /**
     * Initialize score displayers.
     *
     * @param world World object
     */
    public void initDisplayers(World world)
    {
        world.addObject(accuracyDisplayer, 0, 0);
        accuracyDisplayer.init();

        world.addObject(bonusDisplayer, 0, 0);
        bonusDisplayer.init();

        world.addObject(totalDisplayer, 0, 0);
        totalDisplayer.init();

        world.addObject(comboDisplayer, 0, 0);
        comboDisplayer.init();
    }

    /**
     * Update image for all the displayers.
     */
    private void updateImage()
    {
        accuracyDisplayer.update(ScoreCalculator.calculateAccuracy(this));
        bonusDisplayer.update(bonus);
        totalDisplayer.update((int) Math.round(totalScore + 0.5));
        comboDisplayer.update(combo);
    }

    /**
     * Add a new hit score, and update the image.
     *
     * @param hit Hit value from 0 to 5 (From Great to Poor)
     */
    public void hit(int hit)
    {
        // Store scores.
        scores[hit]++;
        scoresHitOrder[noteIndex] = hit;
        noteIndex ++;

        // Update bonus and total score.
        bonus = ScoreCalculator.calculateNewBonus(bonus, hit);
        totalScore += ScoreCalculator.calculateHitScore(combo, halfNoteRatio, bonus, hit);

        // Update combo (> 4 does not count as hit.)
        if (hit > 4)
        {
            resetCombo();
            comboDisplayer.miss();
        }
        else combo ++;

        // Update image
        updateImage();
    }

    /**
     * Reset the combo.
     */
    private void resetCombo()
    {
        maxCombo = Math.max(combo, maxCombo);
        combo = 0;
    }

    // ###################
    // Getters and Setters
    // ###################

    public int[] getScores()
    {
        return scores;
    }

    public int[] getScoresHitOrder()
    {
        return scoresHitOrder;
    }

    public double getBonus()
    {
        return bonus;
    }

    public void setBonus(double bonus)
    {
        this.bonus = bonus;
    }

    public double getTotalScore()
    {
        return totalScore;
    }

    public double getHalfNoteRatio()
    {
        return halfNoteRatio;
    }

    public ScoreDisplayerAccuracy getAccuracyDisplayer()
    {
        return accuracyDisplayer;
    }

    public ScoreDisplayerBonus getBonusDisplayer()
    {
        return bonusDisplayer;
    }

    public ScoreDisplayerTotal getTotalDisplayer()
    {
        return totalDisplayer;
    }

    public ScoreDisplayerCombo getComboDisplayer()
    {
        return comboDisplayer;
    }

    public int getCombo()
    {
        return combo;
    }

    public int getMaxCombo()
    {
        return maxCombo;
    }

    public Beatmap getBeatmap()
    {
        return beatmap;
    }
}
