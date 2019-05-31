/**
 * This utility class calculates the scores.
 *
 * @author Team APCSA 2019
 * @author Yijie Gui
 * @since 2019-05-24 18:44
 */
@SuppressWarnings("WeakerAccess")
public class ScoreCalculator
{
    /** Values of each score. */
    private static final int[] HIT_VALUES = new int[]{320, 300, 200, 100, 50, 0};

    /** Value of each hit bonus values. */
    private static final int[] HIT_BONUS_VALUES = new int[]{32, 32, 16, 8, 4, 0};

    /** Value of each hit bonus. */
    private static final int[] HIT_BONUS = new int[]{2, 1, -8, -24, -44, -100};

    /** Max score on a scale. */
    private static final int MAX_SCORE = 1000000;

    /**
     * Calculate accuracy with formula.
     *
     * @param scoreCounter Score counter
     * @return accuracy in percentage.
     */
    public static double calculateAccuracy(ScoreCounter scoreCounter)
    {
        int[] scores = scoreCounter.getScores();
        int total = 300 * (scores[0] + scores[1]) + 200 * scores[2] + 100 * scores[3] + 50 * scores[4];
        int divider = 300 * (scores[0] + scores[1] + scores[2] + scores[3] + scores[4] + scores[5]);
        return (double) total / divider;
    }

    /**
     * Calculate total score with formula.
     * This method is used only when the game is finished.
     *
     * @param scoreCounter Score counter
     * @return Total score
     */
    public static int calculateTotal(ScoreCounter scoreCounter)
    {
        int[] scores = scoreCounter.getScores();
        final int totalNotes = scoreCounter.getScoresHitOrder().length;
        final double half = scoreCounter.getHalfNoteRatio();

        // Total score
        double totalScore = 0;

        // Bonus value
        double bonus = 100d;
        
        // Combo
        int combo = 0;

        // Calculate score for each note.
        for (int hit : scoreCounter.getScoresHitOrder())
        {
            bonus = calculateNewBonus(bonus, hit);
            
            if (hit > 4) combo = 0;
            else combo++;
            
            totalScore += calculateHitScore(combo, half, bonus, hit);
        }

        return (int) Math.round(totalScore);
    }

    /**
     * Calculate new bonus value.
     *
     * @param bonus Old bonus value.
     * @param hit Hit value.
     * @return New bonus.
     */
    public static double calculateNewBonus(double bonus, int hit)
    {
        return range(0, 100, bonus + HIT_BONUS[hit]);
    }

    /**
     * Calculate the score for a specific note hit.
     *
     * @param combo Combo.
     * @param hnr Half note ratio.
     * @param bonus Bonus
     * @param hit Hit value.
     * @return Hit score.
     */
    public static double calculateHitScore(int combo, double hnr, double bonus, int hit)
    {
        /* 1,000,000 Based algorithm:
        // With this algorithm, the maximum score is 1,000,000
        // This is the most common algorithm for this kind of games on the internet.
        // But I mean who needs this when there's already Accuracy defined...

        double baseScore = hnr * HIT_VALUES[hit];
        double bonusScore = hnr * HIT_BONUS_VALUES[hit] * Math.sqrt(bonus);
        return baseScore + bonusScore; */

        // Unlimited score algorithm:
        if (combo > 100) combo = 100;
        double value = HIT_VALUES[hit];
        return value + (value * (combo + bonus) / 25);
    }

    /**
     * Half note ratio.
     * Multiply this by note value to get half note score in percentage.
     *
     * @param totalNotes Total notes
     * @return Half note ratio.
     */
    public static double calculateHalfNoteRatio(int totalNotes)
    {
        return MAX_SCORE / 2d / totalNotes / 320d;
    }

    /**
     * Rectify a number so that it is in range.
     * Eg. range(0, 10, 29) = 10  |  range(0, 10, 5) = 5
     *
     * @param min Minimum
     * @param max Maximum
     * @param val Actual value
     * @return Rectified value.
     */
    private static double range(double min, double max, double val)
    {
        return val < min ? min : val > max ? max : val;
    }
}
