import java.io.*;
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
                        String[] split = line.split(": ");
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
                    beatmap.getNotPressed(col).add(new NoteInformation(Integer.parseInt(split[2]), col));
                }

                // Read next line.
                line = buf.readLine();
            }

            // Validate properties.

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
}