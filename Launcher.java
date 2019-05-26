import greenfoot.Greenfoot;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Temporary launcher for debugging
 *
 * @since 2019-05-24 21:02
 */
public class Launcher
{
    /**
     * Show a dialog to select song.
     * Note: I did this because I'm too lazy
     * TODO: Remove this after MainMenu is implemented
     */
    @Deprecated
    public static void launchSongSelect()
    {
        // Optimization: Use a different thread.
        new Thread(() ->
        {
            // List beatmap sets.
            ArrayList<File> beatmapSets = BeatmapReader.listBeatmapSets();
            StringBuilder text = new StringBuilder("Please choose a beatmap.\nAvailable Beatmaps:\n");

            for (File beatmapSet : beatmapSets)
            {
                text.append("- ").append(beatmapSet.getName()).append("\n");
            }

            text.append("Please input the beatmap ID:");

            // Read user input
            String beatmapId = JOptionPane.showInputDialog(text);

            // Verify input
            File beatmapSet = BeatmapReader.findBeatmapSetById(beatmapId);
            if (beatmapSet == null)
            {
                JOptionPane.showMessageDialog(null, "Error: Beatmap not found.");
                throw new RuntimeException("Error: Beatmap not found.");
            }

            // List difficulties
            ArrayList<String> diffs = BeatmapReader.listDifficulties(beatmapSet);
            text = new StringBuilder("Please choose a difficulty:\n");

            for (String diff : diffs)
            {
                text.append("- ").append(diff).append("\n");
            }

            text.append("Please input the difficulty of your choice:");

            // Read user input
            String difficulty = JOptionPane.showInputDialog(text);

            // Make world
            BeatmapWorld beatmapWorld = new BeatmapWorld(beatmapSet, difficulty);
            Greenfoot.setWorld(beatmapWorld);

        }).start();
    }

    /**
     * Launch the world as fast as possible.
     */
    public static void launchTest()
    {
        Greenfoot.setWorld(new BeatmapWorld("398366", "Normal"));
    }
}
