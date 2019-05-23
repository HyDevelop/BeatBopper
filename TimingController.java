import java.util.ArrayList;
import java.util.List;

/**
 * The objects of this class are used to control timing with System time.
 *
 * @author Team APCSA 2019
 * @author Yijie Gui
 * @since 2019-05-23 16:41
 */
@SuppressWarnings("WeakerAccess")
public class TimingController
{
    /** List of timing events */
    private List<TimingEvent> events = new ArrayList<>();

    /**
     * @return Is running or not.
     */
    public boolean isRunning()
    {
        return events.get(events.size() - 1).start;
    }

    /**
     * Start the timer.
     */
    public void start()
    {
        if (isRunning())
        {
            throw new RuntimeException("You can't start an already started timer.");
        }

        events.add(new TimingEvent(System.currentTimeMillis(), true));
    }

    /**
     * Stop the timer.
     */
    public void stop()
    {
        if (!isRunning())
        {
            throw new RuntimeException("You can't stop an already stopped timer.");
        }

        events.add(new TimingEvent(System.currentTimeMillis(), false));
    }

    /**
     * Calculate the total duration.
     *
     * @return The total duration (ignoring pauses)
     */
    public long totalDuration()
    {
        long total = 0;

        // Count total
        for (TimingEvent event : events)
        {
            total += event.timestamp * (event.start ? 1 : -1);
        }

        // If the last one is not STOP, assume it stops now.
        if (isRunning())
        {
            total -= System.currentTimeMillis();
        }

        return total;
    }

    /**
     * A timing event records one change in the timer. For example, if
     * you started the timer, that's a timing event.
     */
    private class TimingEvent
    {
        /** Timestamp of this event. */
        private final long timestamp;

        /** Is this a start even or an end event */
        private final boolean start;

        /**
         * Construct a timing event
         *
         * @param timestamp Timestamp
         * @param start True = start, False = end
         */
        private TimingEvent(long timestamp, boolean start)
        {
            this.timestamp = timestamp;
            this.start = start;
        }
    }
}