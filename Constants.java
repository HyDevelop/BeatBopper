/**
 * This class stores the constant value.
 *
 * @author Team APCSA 2019
 * @since 2019-05-22 12:59
 */
@SuppressWarnings("WeakerAccess")
public class Constants
{
    /** Number of columns (or keys) */
    public static final int NUM_COLS = 4;

    /** Where does the first col start? */
    public static final int GRAPHIC_COL_OFFSET = 150;

    /** How long is between the start of first col and end of last col? */
    public static final int GRAPHIC_TOTAL_LENGTH = 400;

    /** How high is the keys? */
    public static final int GRAPHIC_KEY_HEIGHT = 200;

    /** Represents vertical translation: y = m ( x - h ) + k */
    public static final int GRAPHIC_KEY_K = -20;

    /** Keys (in 4 columns) */
    public static final String[] KEYS = {"D", "G", "J", "L"};
}
