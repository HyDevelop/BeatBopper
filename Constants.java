import java.io.File;

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

    /** Width of the world */
    public static final int WIDTH = 1200;

    /** Height of the world */
    public static final int HEIGHT = 800;

    /** How many millis is from spawning to hitting the notes? */
    public static final int GAME_SPEED_MS = 1200;

    /** How many millis is from the supposed start of music to the actual start? */
    public static final int GAME_MUSIC_OFFSET = -258;

    /** Where does the first col start? */
    public static final int GRAPHIC_COL_OFFSET = 150;

    /** How long is between the start of first col and end of last col? */
    public static final int GRAPHIC_TOTAL_LENGTH = 400;

    /** Landing point of the notes. */
    public static final int GRAPHIC_NOTE_LANDING = HEIGHT - 150;

    /** Height of the notes */
    public static final int GRAPHIC_NOTE_HEIGHT = 20;

    /** How high is the keys? */
    public static final int GRAPHIC_KEY_HEIGHT = 220; // 140

    /** Represents vertical translation: y = m ( x - h ) + k */
    public static final int GRAPHIC_KEY_K = 0; // -10

    /** Darken the wallpaper */
    public static final int GRAPHIC_WALLPAPER_DARKEN = (int) (255 * 0.7);

    /** Song selection menu: how many songs are displayed each row? */
    public static final int GRAPHIC_SELECTION_ROWS = 5;

    /** Song selection menu: how many songs are displayed each col? */
    public static final int GRAPHIC_SELECTION_COLS = 3;

    /** Song selection menu: Minimum spacing between two icons. */
    public static final int GRAPHIC_SELECTION_MIN_SPACING = 20;

    /** Keys (in 4 columns) */
    public static final String[] KEYS = {"D", "G", "J", "L"};

    /** Directory to the beatmaps' sub-directories */
    public static final File BEATMAP_DIRECTORY = new File("./beatmaps/");
}
