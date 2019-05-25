import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A class to read beatmaps.
 *
 * @author Team APCSA 2019
 * @author Yijie Gui
 * @author Andy Ge
 * @since 2019-05-24 08:58
 */
@SuppressWarnings("WeakerAccess")
public class BeatmapReader
{
    /**
     * Deserialize beatmap object from a .osu file.
     *
     * TODO: Read the music
     *
     * @param file The .osu file.
     * @return Beatmap
     */
    public static Beatmap read(File file)
    {
        Beatmap beatmap = new Beatmap();

        try
        {
            // File reader stuff
            BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = buf.readLine();
            boolean startReading = false;

            // Read properties to a map.
            Map<String, String> properties = new HashMap<>();

            while(line != null)
            {
                if (!startReading)
                {
                    // Start of HitObjects section
                    if (line.equals("[HitObjects]")) startReading = true;

                    // Read properties
                    line = line.replace(": ", ":");
                    if (line.contains(":"))
                    {
                        String[] split = line.split(":");
                        if (split.length == 2)
                        {
                            properties.put(split[0], split[1]);
                        }
                    }
                }
                else
                {
                    String[] split = line.split(",");

                    // Deal with the weird format to get column
                    int col = -1;
                    switch (split[0])
                    {
                        case "64": col = 0; break;
                        case "192": col = 1; break;
                        case "320": col = 2; break;
                        case "448": col = 3; break;
                    }

                    // Exception: not composed correctly
                    if (col == -1)
                    {
                        throw new RuntimeException("Error: Beatmap format wrong.");
                    }

                    // Add note to beatmap.
                    beatmap.getFuture(col).add(new NoteInformation(Integer.parseInt(split[2]), col));
                }

                // Read next line.
                line = buf.readLine();
            }

            // Validate properties.
            validateProperties(properties);
            beatmap.setProperties(properties);

            // TODO: beatmap.setMusic();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("File not found");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return beatmap;
    }

    /**
     * Validate the properties of a beatmap.
     *
     * @param properties The properties.
     */
    private static void validateProperties(Map<String, String> properties)
    {
        // Make sure mode is mania.
        if (!properties.get("Mode").equals("3"))
            throw new RuntimeException("Error: This beatmap is not mania.");

        // Make sure it's the right key count. (CircleSize in mode 3 means key count)
        if (!properties.get("CircleSize").equals("" + Constants.KEYS.length))
            throw new RuntimeException("Error: This beatmap is not " + Constants.KEYS.length + " keys.");
    }

    /**
     * List all beatmaps under directory.
     *
     * @return All beatmaps' sub-directories.
     */
    public static ArrayList<File> listBeatmapSets()
    {
        ArrayList<File> result = new ArrayList<>();

        File[] files = Constants.BEATMAP_DIRECTORY.listFiles();
        if (files == null) throw new RuntimeException("Error: Failed to get file list. (Maybe there are no files?)");

        // Loop through all files under the dir.
        for (File file : files)
        {
            // Only directories could be beatmaps
            if (!file.isDirectory()) continue;

            // Search sub directory to find: background.jpg or .png and .osu files.
            File[] subFiles = file.listFiles();
            if (subFiles == null) continue;

            boolean containsBackground = false;
            boolean containsOsu = false;

            for (File subFile : subFiles)
            {
                String fileName = subFile.getName().toLowerCase();
                if (fileName.contains("background") && (fileName.contains("jpg") || fileName.contains("png")))
                {
                    containsBackground = true;
                }

                if (fileName.endsWith(".osu"))
                {
                    containsOsu = true;
                }
            }

            if (containsBackground && containsOsu)
            {
                result.add(file);
            }
        }

        return result;
    }

    /**
     * Find beatmap by beatmap id.
     *
     * @param id Beatmap id.
     * @return Beatmap set's sub-directory. (Null if not found)
     */
    public static File findBeatmapSetById(String id)
    {
        File[] files = Constants.BEATMAP_DIRECTORY.listFiles();
        if (files == null) throw new RuntimeException("Error: Failed to get file list. (Maybe there are no files?)");

        for (File file : files)
        {
            if (file.isDirectory() && file.getName().split(" ")[0].equals(id))
            {
                return file;
            }
        }

        return null;
    }

    /**
     * List the difficulties of a beatmap set
     *
     * @param beatmapSet Beatmap's sub-directory
     * @return All difficulties' names
     */
    public static ArrayList<String> listDifficulties(File beatmapSet)
    {
        ArrayList<String> results = new ArrayList<>();

        File[] files = beatmapSet.listFiles();
        if (files == null) throw new RuntimeException("Error: Failed to get file list. (Maybe there are no files?)");

        for (File file : files)
        {
            if (file.isFile() && file.getName().endsWith(".osu"))
            {
                // Find the last set of "[]".
                String[] split = file.getName().split("\\[");
                String diff = split[split.length - 1].split("\\]")[0];
                results.add(diff);
            }
        }

        return results;
    }

    /**
     * Find a beatmap inside a beatmap set with specified difficulty.
     *
     * @param beatmapSet Beatmap's sub-directory
     * @param difficulty Difficulty name
     * @return Beatmap file. (Null if not found)
     */
    public static File findBeatmapByDifficulty(File beatmapSet, String difficulty)
    {
        File[] files = beatmapSet.listFiles();
        if (files == null) throw new RuntimeException("Error: Failed to get file list. (Maybe there are no files?)");

        for (File file : files)
        {
            if (file.isFile() && file.getName().endsWith(".osu"))
            {
                // Find the last set of "[]".
                String[] split = file.getName().split("\\[");
                String diff = split[split.length - 1].split("\\]")[0];
                if (diff.equalsIgnoreCase(difficulty))
                {
                    return file;
                }
            }
        }

        return null;
    }
}
