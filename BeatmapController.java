import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

import java.util.ArrayList;

/**
 * A BeatmapController is an actor that controls the beatmap, spawns the
 * notes in the correct timing, etc.
 *
 * @author Team APCSA 2019
 * @author Yijie Gui
 * @since 2019-05-25 06:37
 */
@SuppressWarnings("WeakerAccess")
public class BeatmapController extends Actor
{
    /** Timing controller. */
    private final TimingController timer;

    /** Beatmap. */
    private final Beatmap beatmap;

    /** Judgement calculator */
    private final JudgementCalculator judgementCalculator;

    /** Score counter. */
    private final ScoreCounter scoreCounter;

    /** Key hit score */
    private final KeyHitScore keyHitDisplayer;

    /** Key hit animation displayers, in a row */
    private final KeyHitAnimation[] keyHitAnimations;

    /**
     * Create a beatmap controller.
     *
     * @param beatmap The beatmap
     * @param scoreCounter Score counter
     */
    public BeatmapController(Beatmap beatmap, ScoreCounter scoreCounter)
    {
        this.timer = new TimingController();
        this.beatmap = beatmap;
        this.judgementCalculator = new JudgementCalculator(beatmap);
        this.scoreCounter = scoreCounter;

        // Create key hit score object
        keyHitDisplayer = new KeyHitScore();

        // Create key hit animation array
        keyHitAnimations = new KeyHitAnimation[Constants.NUM_COLS];

        setImage((GreenfootImage) null);
    }

    /**
     * Initialize position. This method is called in KeypressHandler when
     * the key score object is created.
     */
    public void init()
    {
        // Put the key hit score displayer in the world
        getWorld().addObject(keyHitDisplayer, 0, 0);
        keyHitDisplayer.init();

        for (int i = 0; i < keyHitAnimations.length; i++)
        {
            getWorld().addObject(keyHitAnimations[i] = new KeyHitAnimation(i), 0, 0);
            keyHitAnimations[i].init();
        }
    }

    /**
     * Start the timer and music. ( = Start the game)
     */
    public void start()
    {
        // Use async execution to deal with offsets.
        new Thread(() ->
        {
            try
            {
                int baseDelay = Constants.GAME_START_OFFSET + Constants.GAME_SPEED_MS;
                int musicDelay = baseDelay + Constants.GAME_MUSIC_OFFSET;

                // Get who's bigger. This is necessary because a thread can't
                // sleep negative seconds.
                if (baseDelay > musicDelay)
                {
                    timer.start(baseDelay);
                    Thread.sleep(musicDelay);
                    beatmap.getMusic().play();
                }
                else
                {
                    Thread.sleep(baseDelay);
                    timer.start();
                    Thread.sleep(musicDelay - baseDelay);
                    beatmap.getMusic().play();
                }
            }
            catch (InterruptedException ignored) {}

        }).start();
    }

    /**
     * Act: Detect the notes that are about to fall down, and spawn them.
     */
    public void act()
    {
        if (!timer.isRunning()) return;

        // Get time
        int gameTime = timer.getTotalDuration();

        // Spawn notes.
        for (ArrayList<NoteInformation> col : beatmap.getFuture())
        {
            // Used this to bypass java.util.ConcurrentModificationException
            for (int i = 0; i < col.size(); i++)
            {
                NoteInformation noteInfo = col.get(i);

                // Within the speed range.
                if (noteInfo.getTime() + Constants.GAME_SPAWNING_OFFSET - gameTime < Constants.GAME_SPEED_MS)
                {
                    // Spawn the note to the top.
                    spawnNote(noteInfo);
                    i--;
                }
            }
        }

        // Register non-hit notes as missed.
        for (ArrayList<Note> col : beatmap.getPresent())
        {
            // Used this to bypass java.util.ConcurrentModificationException
            for (int i = 0; i < col.size(); i++)
            {
                Note note = col.get(i);

                // Note is missed
                if (judgementCalculator.isMissed(note.getHitTime(), gameTime))
                {
                    // Register a missed note.
                    registerHitAndRemoveNote(note, 5);
                    i--;
                }
            }
        }

        // Check if done.
        if (beatmap.isDone())
        {
            // Stop timer
            timer.stop();

            /* Output JSON object for debug.
            try
            {
                System.out.println(new Gson().toJson(scoreCounter));
                new Gson().toJson(scoreCounter, new FileWriter("./scores/score-counter-object-" + System.currentTimeMillis() + ".json"));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }*/

            // Remove score objects
            getWorld().removeObject(scoreCounter.getAccuracyDisplayer());
            getWorld().removeObject(scoreCounter.getBonusDisplayer());
            getWorld().removeObject(scoreCounter.getTotalDisplayer());
            
            // Make combo display max combo
            scoreCounter.getComboDisplayer().update(scoreCounter.getMaxCombo());

            // Create score report.
            ScoreReport report = new ScoreReport(scoreCounter);
            getWorld().addObject(report, Constants.WIDTH / 2, Constants.HEIGHT / 2);
            report.draw();
        }
    }

    /**
     * Spawn a note.
     *
     * @param noteInfo Information of the note.
     */
    private void spawnNote(NoteInformation noteInfo)
    {
        // Spawn the note.
        Note note = new Note(noteInfo);
        getWorld().addObject(note, 0, 0);
        note.init();

        // Put in present and remove from future.
        beatmap.getPresent(noteInfo.getColumn()).add(note);
        beatmap.getFuture(noteInfo.getColumn()).remove(noteInfo);
    }

    /**
     * Remove a present note.
     *
     * @param note Present note actor.
     * @param hitScore Hit score (From 0 to 5)
     */
    private void registerHitAndRemoveNote(Note note, int hitScore)
    {
        // Hit
        scoreCounter.hit(hitScore);

        // Remove note
        note.getWorld().removeObject(note);

        // Move from present to past
        beatmap.getPresent(note.getColumn()).remove(note);
        beatmap.getPast(note.getColumn()).add(new NoteInformation(note));

        // Show hit image
        keyHitDisplayer.hit(hitScore);

        // Show hit animation if not missed.
        if (hitScore < 5)
        {
            keyHitAnimations[note.getColumn()].resetIndex();
        }
    }

    /**
     * Register a hit.
     *
     * @param col Column number
     */
    public void hit(int col)
    {
        // Get time
        int gameTime = timer.getTotalDuration();

        // Check if there are any present notes
        if (beatmap.getPresent(col).size() == 0) return;

        // Get note
        Note note = beatmap.getPresent(col).get(0);

        // Calculate hit score
        int hit = judgementCalculator.calculateHitValue(note.getHitTime(), gameTime);
        
        // Player didn't hit anything in range.
        if (hit == -1) return;

        // Show debug line
        if (Constants.DEBUG_MODE)
        {
            ((BeatmapWorld) getWorld()).drawOffsetLine(gameTime - note.getHitTime(), Color.PINK, 1);
        }

        // Register hit
        registerHitAndRemoveNote(note, hit);
    }

    // ###################
    // Getters and Setters
    // ###################

    public TimingController getTimer()
    {
        return timer;
    }

    public Beatmap getBeatmap()
    {
        return beatmap;
    }

    public JudgementCalculator getJudgementCalculator()
    {
        return judgementCalculator;
    }

    public ScoreCounter getScoreCounter()
    {
        return scoreCounter;
    }
}
