//import com.google.gson.Gson;
import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

/**
 * This screen displays the final score.
 *
 * @author Team APCSA 2019
 * @author Yijie Gui
 * @author Andy Ge
 * @since 2019-05-29 08:34
 */
public class ScoreReport extends Actor
{
    // Letter grade windows
    private static final double[] LETTER_GRADE_WINDOWS =
    {
            0.965, // SS
            0.925, // S
            0.895, // A
            0.795, // B
            0.695, // C
            0 // D: Failed
    };

    /** Scores */
    private final ScoreCounter counter;

    /**
     * Constructor for objects of class ScoreReport.
     *
     * @param counter Score counter
     */
    public ScoreReport(ScoreCounter counter)
    {
        this.counter = counter;
        setImage((GreenfootImage) null);
    }

    /**
     * Draw score report image with score counter
     */
    public void draw()
    {
        // Create base image
        GreenfootImage image = new GreenfootImage(Constants.WIDTH, Constants.HEIGHT);
        setImage(image);
        
        // Draw background dim
        int xStart = Constants.GRAPHIC_COL_OFFSET - Constants.GRAPHIC_TOTAL_LENGTH / Constants.NUM_COLS / 2 +
                Constants.GRAPHIC_TOTAL_LENGTH + Images.STAGE_RIGHT.getWidth();
        image.setColor(new Color(0, 0, 0, (int) (255 * 0.75)));
        image.fillRect(xStart, 0, Constants.WIDTH, Constants.HEIGHT);
        image.setColor(new Color(0, 0, 0, (int) (255 * 0.25)));
        image.fillRect(0, 0, xStart, Constants.HEIGHT);

        // Calculate accuracy.
        double accuracy = ScoreCalculator.calculateAccuracy(counter);

        // Draw letter grade
        for (int i = 0; i < LETTER_GRADE_WINDOWS.length; i++)
        {
            if (accuracy > LETTER_GRADE_WINDOWS[i])
            {
                GreenfootImage score = Images.RANKING_LETTER_SCORES[i];
                int x = Constants.GRAPHIC_COL_OFFSET - Constants.GRAPHIC_TOTAL_LENGTH / Constants.NUM_COLS / 2;
                x += (Constants.GRAPHIC_TOTAL_LENGTH - score.getWidth()) / 2;
                int y = Constants.HEIGHT / 2 - score.getHeight() / 2;
                image.drawImage(score, x, y);
                break;
            }
        }

        // Draw Hits
        int x2Len = Constants.WIDTH - xStart;
        int x2Center = x2Len / 2 + xStart;
        for (int i = 0; i < counter.getScores().length; i++)
        {
            int hitCount = counter.getScores()[i];
            GreenfootImage hitScore = new GreenfootImage(Images.KEY_HIT_SCORE[i]);

            // Restore transparency
            hitScore.setTransparency(255);

            // Scale it
            double scale = 1.4;
            hitScore.scale((int) (hitScore.getWidth() / scale), (int) (hitScore.getHeight() / scale));

            // Calc y
            int y = 40 + 70  * i;

            // Draw it
            image.drawImage(hitScore, x2Center - hitScore.getWidth() / 2, y);
            
            // Draw number
            NumberDisplayer number = new NumberDisplayer();
            getWorld().addObject(number, 0, 0);
            number.init(Constants.WIDTH / 2 - 40, y + hitScore.getHeight() / 2);
            number.drawNumber(hitCount);
        }
        
        // Draw accuracy
        {
            ScoreDisplayerAccuracy number = new ScoreDisplayerAccuracy();
            getWorld().addObject(number, 0, 0);
            number.init(Constants.WIDTH / 2 - 40, Constants.HEIGHT - 100);
            number.update(accuracy);
        }
        
        // Draw score
        {
            ScoreDisplayerTotal number = new ScoreDisplayerTotal();
            getWorld().addObject(number, 0, 0);
            number.init(Constants.WIDTH / 2 - 40, Constants.HEIGHT - 200);
            number.update((int) Math.round(counter.getTotalScore()));
        }
    }
}
