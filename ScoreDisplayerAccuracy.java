/**
 * This class displays the accuracy
 *
 * @author Team APCSA 2019
 * @author Andy Ge
 * @since 2019-05-29 08:34
 */
public class ScoreDisplayerAccuracy extends NumberDisplayer
{
    private final ScoreCounter counter;

    /**
     * Construct an accuracy displayer.
     *
     * @param counter Score counter.
     */
    public ScoreDisplayerAccuracy(ScoreCounter counter)
    {
        super(Images.SCORE_NUMBERS, Images.SCORE_DOT);
        this.counter = counter;
    }

    /**
     * Initialize
     */
    public void init()
    {
        int y = 500;
        super.init(Constants.WIDTH / 2, y);
    }

    /**
     * Update image.
     *
     * @param accuracy Accuracy
     */
    public void update()
    {
        // TODO: Clear the image.
        clear();

        // Calculate the accuracy
        double accuracy = ScoreCalculator.calculateAccuracy(counter);

        // TODO: Draw "%" first because it draws from the right.
        // (Hint: use drawLetter()) (Hint: % is in Images)
        drawLetter(Images.SCORE_PERCENT);

        // TODO: Draw the accuracy after it, keep 2 decimal
        // (Hint: accuracy is a percentage from 0 to 1, so multiply it by 100)
        // (Hint: there's already encapsulated methods in super class NumberDisplayer
        //        that draws numbers.)
        drawNumber(accuracy * 100, 2);
    }
}
