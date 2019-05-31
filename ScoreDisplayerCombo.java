import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;

import java.util.LinkedList;

/**
 * This class displays the combo below the KeyHitScore. Combo is defined
 * as the count of continuous hit.
 *
 * @author Team APCSA 2019
 * @author Yijie Gui
 * @since 2019-05-29 08:38
 */
@SuppressWarnings("WeakerAccess")
public class ScoreDisplayerCombo extends Actor
{
    /** Sound effect for combo break */
    private static final GreenfootSound COMBO_BREAK = new GreenfootSound("combobreak.aiff");

    /** Last combo */
    private int lastCombo = 0;

    /**
     * Construct a combo displayer object
     */
    public ScoreDisplayerCombo()
    {
        setImage((GreenfootImage) null);
    }

    /**
     * Initialize position.
     */
    public void init()
    {
        // Calculate position
        int keyLen = Constants.GRAPHIC_TOTAL_LENGTH / Constants.NUM_COLS;
        int x = Constants.GRAPHIC_COL_OFFSET + Constants.GRAPHIC_TOTAL_LENGTH / 2 - keyLen / 2;
        int y = (int) Math.round(Constants.GRAPHIC_NOTE_LANDING * (2.0 / 3.0)) + Images.KEY_HIT_SCORE[0].getHeight() / 2;

        // Set location
        setLocation(x, y);
    }

    /**
     * Update image
     *
     * @param combo Combo number
     */
    public void update(int combo)
    {
        lastCombo = combo;

        // Display combo only start at 3
        if (combo < 3)
        {
            setImage((GreenfootImage) null);
            return;
        }

        // Get digits
        LinkedList<Integer> stack = NumberDisplayer.toDigits(combo);

        // Get total width and max height
        int totalWidth = 0;
        int maxHeight = 0;
        for (Integer digit : stack)
        {
            totalWidth += Images.KEY_COMBO_NUMBERS[digit].getWidth();
            maxHeight = Math.max(maxHeight, Images.KEY_COMBO_NUMBERS[digit].getHeight());
        }

        // Create image
        GreenfootImage image = new GreenfootImage(totalWidth, maxHeight);
        setImage(image);

        // Draw numbers
        int pointer = 0;
        for (Integer digit : stack)
        {
            GreenfootImage digitImg = Images.KEY_COMBO_NUMBERS[digit];
            image.drawImage(digitImg, pointer, maxHeight - digitImg.getHeight());
            pointer += digitImg.getWidth();
        }
    }

    /**
     * This method is called when player misses a note.
     * Precondition: This should be called before update()
     */
    public void miss()
    {
        // Play a sound if there's a combo before missing
        if (lastCombo >= 3) COMBO_BREAK.play();
    }
}
