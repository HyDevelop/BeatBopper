/**
 * This class displays the bonus score
 *
 * @author Team APCSA 2019
 * @author Andy Ge
 * @since 2019-05-29 08:34
 */
public class ScoreDisplayerBonus extends NumberDisplayer
{
    /**
     * Construct a bonus displayer.
     */
    public ScoreDisplayerBonus()
    {
        super(Images.SCORE_NUMBERS, Images.SCORE_DOT);
    }

    /**
     * Initialize
     */
    public void init()
    {
        super.init(Constants.WIDTH / 2 - Constants.GRAPHIC_SCORE_X_PADDING, Constants.HEIGHT - 40);
        update(100);
    }

    /**
     * Update image.
     *
     * @param bonus Bonus score
     */
    public void update(double bonus)
    {
        // Clear the image.
        clear();

        // Draw "x" first because it draws from the right.
        drawLetter(Images.SCORE_X);

        // Draw the bonus after it, keep 2 decimal
        drawNumber(bonus, 2);
    }
}
