import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BooleanSupplier;

/**
 * A Counter
 */
public class Counter {
    
    private long start;
    private static ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * Instantiates a new Counter
     */
    public Counter() {
        this.start = System.currentTimeMillis();
    }

    /**
     * Returns the number of milliseconds elapsed since
     * the start time.
     *
     * @return The elapsed time in milliseconds.
     */
    public long getElapsed() {
        return (System.currentTimeMillis() - start);
    }

    /**
     * Restarts this counter using its period.
     */
    public void reset() {
        this.start = System.currentTimeMillis();
    }

    /**
     * Returns a formatted String of the time elapsed.
     *
     * @return The elapsed time formatted hh:mm:ss.
     */
    public String toElapsedString() {
        return format(getElapsed());
    }

    public static long timeFromMark(long ms) {
        return new AtomicLong(System.currentTimeMillis()).addAndGet((-1 * ms));
    }

    /**
     * Allows a condition to be passed to check and a timeout for the condition to pass
     * Continuously checks the condition in a while loop on an executor thread
     * @param condition
     * @param timeout
     * @return
     */
    public static boolean waitCondition(BooleanSupplier condition, long timeout) {
        long start = System.currentTimeMillis();
        long end = start + timeout;

        Callable<Boolean> future = () -> {
            while (!condition.getAsBoolean()) {
                if (System.currentTimeMillis() > end) {
                    return false;
                }
            }
            return true;
        };
        try {
            return executor.submit(future).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Converts milliseconds to a String in the format
     * hh:mm:ss.
     *
     * @param time The number of milliseconds.
     * @return The formatted String.
     */
    public static String format(long time) {
        StringBuilder t = new StringBuilder();
        long total_secs = time / 1000;
        long total_mins = total_secs / 60;
        long total_hrs = total_mins / 60;
        int secs = (int) total_secs % 60;
        int mins = (int) total_mins % 60;
        int hrs = (int) total_hrs % 60;
        if (hrs < 10) {
            t.append("0");
        }
        t.append(hrs);
        t.append(":");
        if (mins < 10) {
            t.append("0");
        }
        t.append(mins);
        t.append(":");
        if (secs < 10) {
            t.append("0");
        }
        t.append(secs);
        return t.toString();
    }
}