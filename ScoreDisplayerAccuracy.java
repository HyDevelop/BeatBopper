/**
 * This class displays the accuracy
 *
 * @author Team APCSA 2019
 * @author Andy Ge
 * @since 2019-05-29 08:34
 */
@SuppressWarnings("WeakerAccess")
public class ScoreDisplayerAccuracy extends NumberDisplayer
{
    /**
     * Construct an accuracy displayer.
     */
    public ScoreDisplayerAccuracy()
    {
        super(Images.SCORE_NUMBERS, Images.SCORE_DOT);
    }

    /**
     * Initialize
     */
    public void init()
    {
        super.init(Constants.WIDTH / 2 - Constants.GRAPHIC_SCORE_X_PADDING, 100);
        update(1);
    }

    /**
     * Update image.
     *
     * @param accuracy Accuracy
     */
    public void update(double accuracy)
    {
        // Clear the image.
        clear();

        // Draw "%" first because it draws from the right.
        drawLetter(Images.SCORE_PERCENT);

        // Draw the accuracy after it, keep 2 decimal
        drawNumber(accuracy * 100, 2);
    }
}
