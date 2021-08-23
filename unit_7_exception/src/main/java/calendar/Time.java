package calendar;

public class Time {

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
}
