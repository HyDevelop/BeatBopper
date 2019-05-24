import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

/**
 * A Counter class that allows you to display a numerical value on screen.
 * 
 * The Counter is an actor, so you will need to create it, and then add it to
 * the world in Greenfoot.  If you keep a reference to the Counter then you
 * can adjust its value.
 * 
 * @author Team APCSA 2019
 * @author Andy Ge
 * @version 2019-05-22
 */
@SuppressWarnings("WeakerAccess")
public class ScoreCounter extends Actor
{
    private static final Color TRANSPARENT = new Color(0,0,0,0);
    private String prefix;

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

    /**
     * Create a new counter, initialised to 0.
     *
     * @param prefix of the counter
     */
    public ScoreCounter(String prefix, Beatmap beatmap)
    {
        this.prefix = prefix;
        this.scoresHitOrder = new int[beatmap.countTotalObjects()];
        this.halfNoteRatio = ScoreCalculator.calculateHalfNoteRatio(beatmap.countTotalObjects());
        updateImage();
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
        totalScore += ScoreCalculator.calculateHitScore(halfNoteRatio, bonus, hit);

        updateImage();
    }

    /**
     * Updates the image on screen to show the current value.
     */
    private void updateImage()
    {
        GreenfootImage image = Images.COUNTER;
        GreenfootImage text = new GreenfootImage(prefix + (int) totalScore, 22, Color.BLACK, TRANSPARENT);

        if (text.getWidth() > image.getWidth() - 20)
        {
            image.scale(text.getWidth() + 20, image.getHeight());
        }

        image.drawImage(text, (image.getWidth() - text.getWidth()) / 2, (image.getHeight() - text.getHeight()) / 2);
        setImage(image);
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
}
