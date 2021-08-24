package calendar;

public class Time implements Comparable<Time> {

    private long ms;
    private static final long START_OF_CALENDAR = 0L;

    public Time() {
        this.ms = START_OF_CALENDAR;
    }

    public long getMs() {
        return ms;
    }

    public void setMs(long ms) {
        this.ms = ms;
    }

    @Override
    public int compareTo(Time time) {
        if (this.ms > time.ms) return 1;
        else if (this.ms < time.ms) return -1;
        return 0;
    }
}
