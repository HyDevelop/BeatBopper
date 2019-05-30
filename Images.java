import greenfoot.GreenfootImage;

/**
 * This class stores the constants of the image assets.
 *
 * @author Team APCSA 2019
 * @version 2019-05-21
 */
@SuppressWarnings("WeakerAccess")
public class Images
{
    // Notes
    public static final GreenfootImage[] NOTES = 
    {
            new GreenfootImage("mania-note1.png"),
            new GreenfootImage("mania-note2.png"),
            new GreenfootImage("mania-note2.png"),
            new GreenfootImage("mania-note1.png")
    };
    
    // Keys
    public static final GreenfootImage[] KEYS = 
    {
            new GreenfootImage("mania-key1.png"),
            new GreenfootImage("mania-key2.png"),
            new GreenfootImage("mania-key2.png"),
            new GreenfootImage("mania-key1.png")
    };
    
    // Pressed Keys
    public static final GreenfootImage[] KEYS_PRESSED = 
    {
            new GreenfootImage("mania-key1D.png"),
            new GreenfootImage("mania-key2D.png"),
            new GreenfootImage("mania-key2D.png"),
            new GreenfootImage("mania-key1D.png")
    };

    // Key Flash
    public static final GreenfootImage KEY_FLASH = new GreenfootImage("mania-stage-light.png");

    // Key Hit Score
    public static final GreenfootImage[] KEY_HIT_SCORE =
    {
            new GreenfootImage("mania-hit300g.png"),
            new GreenfootImage("mania-hit300.png"),
            new GreenfootImage("mania-hit200.png"),
            new GreenfootImage("mania-hit100.png"),
            new GreenfootImage("mania-hit50.png"),
            new GreenfootImage("mania-hit0.png")
    };

    // Key Numbers
    public static final GreenfootImage[] KEY_HIT_SCORE_NUMBERS =
    {
            new GreenfootImage("mn-0.png"),
            new GreenfootImage("mn-1.png"),
            new GreenfootImage("mn-2.png"),
            new GreenfootImage("mn-3.png"),
            new GreenfootImage("mn-4.png"),
            new GreenfootImage("mn-5.png"),
            new GreenfootImage("mn-6.png"),
            new GreenfootImage("mn-7.png"),
            new GreenfootImage("mn-8.png"),
            new GreenfootImage("mn-9.png")
    };

    // Frames for the hit animations
    public static final GreenfootImage[] KEY_HIT_ANIMATION_FRAMES =
    {
            new GreenfootImage("lightingN-0.png"),
            new GreenfootImage("lightingN-1.png"),
            new GreenfootImage("lightingN-2.png"),
            new GreenfootImage("lightingN-3.png"),
            new GreenfootImage("lightingN-4.png"),
            new GreenfootImage("lightingN-5.png"),
            new GreenfootImage("lightingN-6.png"),
            new GreenfootImage("lightingN-7.png"),
            new GreenfootImage("lightingN-8.png"),
            new GreenfootImage("lightingN-9.png"),
            new GreenfootImage("lightingN-10.png"),
            new GreenfootImage("lightingN-11.png"),
            null
    };

    // Score Numbers
    public static final GreenfootImage[] SCORE_NUMBERS =
    {
            new GreenfootImage("score-0.png"),
            new GreenfootImage("score-1.png"),
            new GreenfootImage("score-2.png"),
            new GreenfootImage("score-3.png"),
            new GreenfootImage("score-4.png"),
            new GreenfootImage("score-5.png"),
            new GreenfootImage("score-6.png"),
            new GreenfootImage("score-7.png"),
            new GreenfootImage("score-8.png"),
            new GreenfootImage("score-9.png"),
    };

    // Score Letters
    public static final GreenfootImage SCORE_COMMA = new GreenfootImage("score-comma.png");
    public static final GreenfootImage SCORE_DOT = new GreenfootImage("score-dot.png");
    public static final GreenfootImage SCORE_PERCENT = new GreenfootImage("score-percent.png");
    public static final GreenfootImage SCORE_X = new GreenfootImage("score-x.png");

    // Ranking Letter Scores
    public static final GreenfootImage[] RANKING_LETTER_SCORES =
    {
            new GreenfootImage("ranking-X.png"),
            new GreenfootImage("ranking-S.png"),
            new GreenfootImage("ranking-A.png"),
            new GreenfootImage("ranking-B.png"),
            new GreenfootImage("ranking-C.png"),
            new GreenfootImage("ranking-D.png"),
    };

    // Ranking Perfect
    public static final GreenfootImage RANKING_PERFECT = new GreenfootImage("ranking-perfect.png");

    // Stage
    public static final GreenfootImage STAGE_LEFT = new GreenfootImage("mania-stage-left.png");
    public static final GreenfootImage STAGE_RIGHT = new GreenfootImage("mania-stage-right.png");
    public static final GreenfootImage STAGE_HINT = new GreenfootImage("mania-stage-hint.png");

    // Counter
    public static final GreenfootImage COUNTER = new GreenfootImage("Counter.png");

    // Wallpaper
    public static final GreenfootImage WALLPAPER = new GreenfootImage("wallpaper-xperanza.jpg");
    public static final GreenfootImage BLACK = new GreenfootImage("black-2x2.png");
}
