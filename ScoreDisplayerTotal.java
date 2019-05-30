/**
 * This class displays the total score
 *
 * @author Team APCSA 2019
 * @author Andy Ge
 * @since 2019-05-29 08:33
 */
public class ScoreDisplayerTotal extends NumberDisplayer
{
    /**
     * Construct a total score displayer.
     */
    public ScoreDisplayerTotal()
    {
        super(Images.SCORE_NUMBERS, Images.SCORE_DOT, 1.5);
    }

    /**
     * Initialize
     */
    public void init()
    {
        super.init(Constants.WIDTH / 2 - Constants.GRAPHIC_SCORE_X_PADDING, 40);
        update(0);
    }

    /**
     * Update image.
     *
     * @param total Total score
     */
    public void update(int total)
    {
        // Clear the image.
        clear();

        // Draw the total score after it
        drawNumber((int) total);
    }
}
